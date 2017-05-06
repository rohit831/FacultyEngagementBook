package com.gr.facultyengagementbook.Model;


public class AvailableFacultyDetails {

    private String availableFacultyName;
    private String availableFacultyMobileNo;

    public AvailableFacultyDetails(String availableFacultyName, String availableFacultyMobileNo) {
        this.availableFacultyName = availableFacultyName;
        this.availableFacultyMobileNo = availableFacultyMobileNo;
    }

    public String getAvailableFacultyName() {
        return availableFacultyName;
    }

    public void setAvailableFacultyName(String availableFacultyName) {
        this.availableFacultyName = availableFacultyName;
    }

    public String getAvailableFacultyMobileNo() {
        return availableFacultyMobileNo;
    }

    public void setAvailableFacultyMobileNo(String availableFacultyMobileNo) {
        this.availableFacultyMobileNo = availableFacultyMobileNo;
    }
}
