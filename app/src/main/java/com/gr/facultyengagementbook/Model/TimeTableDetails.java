package com.gr.facultyengagementbook.Model;


public class TimeTableDetails {
    private int period;
    private String branch;
    private String semester;

    public TimeTableDetails(int period, String branch, String semester) {
        this.branch = branch;
        this.period = period;
        this.semester = semester;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

}
