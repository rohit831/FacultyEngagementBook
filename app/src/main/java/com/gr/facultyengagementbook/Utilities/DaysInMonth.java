package com.gr.facultyengagementbook.Utilities;

public class DaysInMonth
{
    public static int getNoOfDays(String monthName, String year)
    {
        int noOfDays = 0;
        switch (monthName)
        {
            case "Jan":
                noOfDays = 31;
                break;
            case "Feb":
                if(Integer.parseInt(year)%4==0 )
                    noOfDays = 29;
                else
                    noOfDays = 28;
                break;
            case "Mar":
                noOfDays = 31;
                break;
            case "Apr":
                noOfDays = 30;
                break;
            case "May":
                noOfDays = 31;
                break;
            case "Jun":
                noOfDays = 30;
                break;
            case "Jul":
                noOfDays = 31;
                break;
            case "Aug":
                noOfDays = 31;
                break;
            case "Sep":
                noOfDays = 30;
                break;
            case "Oct":
                noOfDays = 31;
                break;
            case "Nov":
                noOfDays = 30;
                break;
            case "Dec":
                noOfDays = 31;
                break;
        }

        return noOfDays;
    }
}
