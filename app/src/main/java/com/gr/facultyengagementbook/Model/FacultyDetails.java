package com.gr.facultyengagementbook.Model;

public class FacultyDetails {

    private String name;
    private String mobileNo;
    private String email;
    private String password;

    public FacultyDetails() {
        name = "";
        mobileNo = "";
        email = "";
    }

    public FacultyDetails(String name, String mobileNo, String email, String password) {
        this.email = email;
        this.mobileNo = mobileNo;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

}
