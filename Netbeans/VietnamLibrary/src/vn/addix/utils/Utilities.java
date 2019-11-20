package vn.addix.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utilities {
    /**
     * isNumber() checking string is number or string
     * @param strNumber
     * @return 
     */
    public static boolean isNumber(String strNumber){
        try {  
            Double.parseDouble(strNumber);  
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }
    }    
    /**
     * getDateTime(): return datetime with format yyyy/MM/dd HH:mm:ss GMT
     * @return 
     */
    public static String getDateTime()
    {
        Date date = new Date();
        DateFormat gmtFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
        TimeZone gmtTime = TimeZone.getTimeZone("GMT");
        gmtFormat.setTimeZone(gmtTime);
        return gmtFormat.format(date);
    }
    /**
     * sort a string array ascending
     * @param strArr is an array of string
     */
    public static void sortStringArrayASC(String[] strArr){
        int size = strArr.length;
        for(int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if(strArr[i].compareTo(strArr[j])>0) {
                    String temp = strArr[i];
                    strArr[i] = strArr[j];
                    strArr[j] = temp;
                }
            }
        }
    }    
}
