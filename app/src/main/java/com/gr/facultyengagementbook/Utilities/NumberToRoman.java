package com.gr.facultyengagementbook.Utilities;


public class NumberToRoman {

    public static String convertToRoman(int period){

        String roman = "";
        switch (period){

            case 1: roman =  "I";
                break;
            case 2: roman =  "II";
                break;
            case 3: roman =  "III";
                break;
            case 4: roman =  "IV";
                break;
            case 5: roman =  "V";
                break;
            case 6: roman =  "VI";
                break;
            case 7: roman =  "VII";
                break;
            case 8: roman =  "VIII";
                break;
        }
        return roman;
    }
}
