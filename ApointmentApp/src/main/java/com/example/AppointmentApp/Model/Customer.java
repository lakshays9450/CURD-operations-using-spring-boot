package com.example.AppointmentApp.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Scanner;

@Entity
@Table(name = "customers")
public class Customer {
    private long id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last-name")
    private String lastName;
    @Column(name = "appointment")
    private Appointment appointment;

    public Customer(long id, String username, String firstName, String lastName, Appointment appointment){
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.appointment = appointment;
    }
    Customer(){}
    public void setId(long id) {
        this.id = id;
    }
    public long getId(){
        return this.id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setAppointment(Appointment appointment){
        this.appointment = appointment;
    }
    public void printInformation(){
        System.out.println("Name = " + firstName + " " + lastName);
        System.out.println("Username = " + username);
        System.out.println("Appointment = from" + appointment.getStartTime()+
        " to " + appointment.getEndTime());
    }
}
