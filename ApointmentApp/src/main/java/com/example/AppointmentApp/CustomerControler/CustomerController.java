package com.example.AppointmentApp.CustomerControler;

import com.example.AppointmentApp.Exception.ResourceNotFoundException;
import com.example.AppointmentApp.Model.Customer;
import com.example.AppointmentApp.CustomerRepository.CustomerRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:5432")
@RestController
@RequestMapping("/api/v1/")
public class CustomerController {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @PostMapping("/customers")
    public Customer createEmployee(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Customer> getEmployeeById(@PathVariable Long id) {
        Customer employee = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Customer> updateEmployee(@PathVariable Long id, @RequestBody Customer employeeDetails){
        Customer employee = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with username :" + id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setUsername(employeeDetails.getUsername());

        Customer updatedEmployee = customerRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Customer employee = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        customerRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
