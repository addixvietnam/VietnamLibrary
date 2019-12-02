package vn.addix.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class Utilities {
       
    /**
     * checking string is number or string
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
     * return datetime with format yyyy/MM/dd HH:mm:ss GMT
     * @param formatDateTime example is "yyyy/MM/dd HH:mm:ss z"
     * @param strTimeZone example is "GMT"
     * @return 
     */     
    public static String getDateTime(String formatDateTime, String strTimeZone)
    {
        Date date = new Date();
        DateFormat gmtFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
        TimeZone gmtTime = TimeZone.getTimeZone(strTimeZone);
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
    /**
     * Get total time to waiting finish the program
     * @param beginTime
     * @param endTime
     * @param formatTime example is "yyyy/MM/dd HH:mm:ss z"
     * @return 
     */
    public static String getTotalWaitTime(String beginTime, String endTime, String formatTime){
        String strTotalWaitTime = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatTime);
        LocalDateTime dateTimeBegin = LocalDateTime.parse(beginTime, formatter);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(endTime, formatter);
        long tmpTimeBegin = Duration.between(dateTimeBegin, LocalDateTime.now()).getSeconds();
        long tmpTimeEnd = Duration.between(dateTimeEnd, LocalDateTime.now()).getSeconds();
        long tmpTimeTotal = tmpTimeBegin - tmpTimeEnd;
        int hour = (int) (tmpTimeTotal / 3600);
        int minutes = (int) ((tmpTimeTotal%3600) / 60);
        int seconds = (int) (tmpTimeTotal%60);
        strTotalWaitTime = Integer.toString(hour) + " hours " + Integer.toString(minutes) + 
                " minutes " + Integer.toString(seconds) + " seconds ";
        return strTotalWaitTime;
    }
}
