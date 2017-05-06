package com.gr.facultyengagementbook.Model;


public class RecordDetails {

    private String facultyMobileNo;
    private String engagedFacultyMobileNo;
    private String date;
    private String facultyName;
    private String branch;
    private String semester;
    private int period;
    private String engagedFacultyName;

    public RecordDetails(String facultyMobileNo, String engagedFacultyMobileNo, String date, String facultyName,
                         String branch, String semester,int period, String engagedFacultyName) {
        this.branch = branch;
        this.date = date;
        this.engagedFacultyMobileNo = engagedFacultyMobileNo;
        this.engagedFacultyName = engagedFacultyName;
        this.facultyMobileNo = facultyMobileNo;
        this.facultyName = facultyName;
        this.semester = semester;
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEngagedFacultyMobileNo() {
        return engagedFacultyMobileNo;
    }

    public void setEngagedFacultyMobileNo(String engagedFacultyMobileNo) {
        this.engagedFacultyMobileNo = engagedFacultyMobileNo;
    }

    public String getEngagedFacultyName() {
        return engagedFacultyName;
    }

    public void setEngagedFacultyName(String engagedFacultyName) {
        this.engagedFacultyName = engagedFacultyName;
    }

    public String getFacultyMobileNo() {
        return facultyMobileNo;
    }

    public void setFacultyMobileNo(String facultyMobileNo) {
        this.facultyMobileNo = facultyMobileNo;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
