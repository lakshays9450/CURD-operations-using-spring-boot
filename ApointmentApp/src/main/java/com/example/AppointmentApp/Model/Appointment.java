package com.example.AppointmentApp.Model;

public class Appointment {
    private String startTime;
    //             dd/mm hh/mm
    private String endTime;
    private int startNumber;
    private int endNumber;
    private int maxDays[] = {31,28,31,30,31,30,31,31,30,31,30,31};
    private int cummulativeDays[] ={0,31,59,90,120,151,181,212,243,273,304,334};
    public boolean isTimeValid(String time){
        if(time.length() != 11) return false;
        for(int index=0; index <time.length(); index++){
            if(index == 2 || index == 5 || index == 8) continue;
            if(Character.isDigit(time.charAt(index)) == false) return false;
        }
        int date = Integer.valueOf(time.substring(0,2));
        int month = Integer.valueOf(time.substring(3,5));
        int hour = Integer.valueOf(time.substring(5,7));
        int minute = Integer.valueOf(time.substring(9,11));
        if(month <= 0 || month > 12) return false;
        if(date <= 0 || date > maxDays[month-1]) return false;
        if(hour < 0 || hour >= 24) return false;
        if(minute < 0 || minute >= 60) return false;
        return true;
    }
    private int getTimeToNumber(String time){
        int date = Integer.valueOf(time.substring(0,2));
        int month = Integer.valueOf(time.substring(3,5));
        int hour = Integer.valueOf(time.substring(5,7));
        int minute = Integer.valueOf(time.substring(9,11));
        int num = (date+cummulativeDays[month-1])*1440 + hour*60 + minute;
        return num;
    }
    private boolean isAppointmentValid(){
        if(isTimeValid(startTime) == false) return false;
        if(isTimeValid(endTime) == false) return false;
        int s_num = getTimeToNumber(startTime);
        int e_num = getTimeToNumber(endTime);
        if(e_num <= s_num) return false;
        this.setStartNumber(s_num);
        this.setEndNumber(e_num);
        return true;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
    public String getStartTime(){
        return this.startTime;
    }
    public String getEndTime(){
        return this.endTime;
    }
    public void setStartNumber(int startNumber){
        this.startNumber = startNumber;
    }
    public void setEndNumber(int endNumber){
        this.endNumber = endNumber;
    }
    public int getStartNumber(){
        return this.startNumber;
    }
    public int getEndNumber(){
        return this.endNumber;
    }
}
