package com.gr.facultyengagementbook.Model;


public class FacultyRecordDetails {

    private String date;
    private int period;
    private String engagedFacultyName;

    public FacultyRecordDetails(String date,int period,  String engagedFacultyName
    ) {
        this.date = date;
        this.engagedFacultyName = engagedFacultyName;
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEngagedFacultyName() {
        return engagedFacultyName;
    }

    public void setEngagedFacultyName(String engagedFacultyName) {
        this.engagedFacultyName = engagedFacultyName;
    }

}

