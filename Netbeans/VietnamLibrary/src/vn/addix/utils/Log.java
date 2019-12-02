/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.addix.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * Log only run with Latin character
 */
public class Log {
    private int widthSize = 0;
    private String strCharHeader = "*";
    private String strLineError = "=";    
    private int numErrorSpace = 0;    

   /**
    * Constructor Log class with parameter get from Config.md file
    */
    public Log(int widthSize, int numErrorSpace, String strCharHeader, String strLineError) {        
        this.widthSize = widthSize;
        this.numErrorSpace = numErrorSpace;
        this.strCharHeader = strCharHeader;
        this.strLineError = strLineError;
    }
    /**
     * formatStringHeaderAndFooter(): Set True for Header content, False for Footer content
     * @param isHeader : true/false 
     * @return 
     */
    public String formatStringHeaderAndFooter(boolean isHeader){
        String myStr = "";
        String strStar = "";
        String myFormat = "";
        String outDate = "";
        String strText = "";

        //using join, collection.nCopies for character header and footer
        strStar = String.join("", Collections.nCopies(this.widthSize, this.strCharHeader));
        int lengthDate = 0;
        //set Hearder and Footer: length of Start/End with Date occur
        if(isHeader){//Header
            strText = "Start time:";
            lengthDate = (int)Math.floor(this.widthSize/2);
            // myFormat: Caculate of content.lenght, not over size
            myFormat = "%s" +  strText + "%"+ lengthDate +"s%" + 
                    (this.widthSize - strText.length() - lengthDate - 1) + "s";
            //outDate: print date with format from getDateTime
            outDate = String.format(myFormat, strCharHeader, 
                    Utilities.getDateTime(MyVars.LIB_DATETIME_FORMAT, MyVars.LIB_TIMEZONE), strCharHeader);
        }else{//Footer
            strText = "End time:";
            lengthDate = (int)(Math.floor(this.widthSize/2)+2);
            myFormat = "%s" + strText + "%" + lengthDate + "s%" + (this.widthSize - strText.length() - lengthDate - 1) + "s";
            outDate = String.format(myFormat, strCharHeader, 
                    Utilities.getDateTime(MyVars.LIB_DATETIME_FORMAT, MyVars.LIB_TIMEZONE), strCharHeader);
        }
        myStr =  strStar + "\n";
        myStr = myStr + outDate + "\n";
        myStr = myStr + strStar  + "\n";

        return myStr;
    }
    
   /**
    * writeContent(): Get content limited length for content 
    * @param content: text of content
    * @return 
    */
    public String formatStringContent(String content){
        int strLength = content.length();
        String myStr = "";
        
        String[] arrContent = content.split("\n");
        for(int i = 0; i < arrContent.length; i++){
            myStr += myFormatContent(arrContent[i]) + "\n";
        }
        
        return myStr + "\n";
    }
    private String myFormatContent(String content){
        int strLength = content.length();
        boolean isFirst = true;
        String myStr = "";
        if(strLength > this.widthSize){//lenght content over size default
            String strTemp = "";
            int postStart = 0, postEnd = this.widthSize - 1 , iter = 0;
            char myChar;
            //While : search ' ' text, then Enter content 
            while(postEnd <= strLength){
                strTemp = content.substring(postStart, postEnd);                
                iter = postEnd + 1;
                myChar = content.charAt(iter);                
                while(myChar != ' '){
                    iter --;                
                    myChar = content.charAt(iter);
                }               
                postEnd = iter;                
                strTemp = content.substring(postStart, postEnd);                
                if(isFirst){
                    myStr = myStr + strTemp;
                    isFirst = false;
                }else{
                    myStr = myStr + "\n" + strTemp;
                }
                postStart = postEnd + 1;
                postEnd += this.widthSize - 1;
                if(postEnd > strLength){
                    myStr = myStr + "\n" + content.substring(postStart, strLength);
                }                
            }
        }else{//lenght content smaller size default
            myStr += content;
        }
        return myStr;
    }
 /**
  * writeError: Get content error
  * @param nameError: name of Error
  * @param errorContent: text of Error
  * @return 
  */  
    public String formatStringError(String nameError, String errorContent)
    {       
        String myStr = "";
        String myErrorLine = "";
        String strSpace = " ";
        String mySpace = "";
        String headerError = "";

        myErrorLine = String.join("", Collections.nCopies(this.widthSize, strLineError));
        myStr = myErrorLine + "\n";
        headerError = nameError;
        String[] arrHeaderError = headerError.split("\n");
        for(int i = 0; i < arrHeaderError.length; i++){            
            myStr += formatErrorLogString("", arrHeaderError[i]);
        }        
        myStr += "\nDate: " + Utilities.getDateTime(MyVars.LIB_DATETIME_FORMAT, MyVars.LIB_TIMEZONE) ;
        mySpace = String.join("", Collections.nCopies(this.numErrorSpace, strSpace));
        //format error log
        String[] arrErrorContent = errorContent.split("\n");
        for(int i = 0; i < arrErrorContent.length; i++){
            myStr += formatErrorLogString(mySpace, arrErrorContent[i]);
        }        
        myStr +=  "\n" + myErrorLine + "\n";
        return myStr;       
    }   
    private String formatErrorLogString(String mySpace, String errorContent){
        String strResult = "";
        int strLength = 0;
        //The same content beside, but change length of Date,text Error
        strLength = errorContent.length();
        if((strLength + this.numErrorSpace) > this.widthSize){
            String strTemp = "";
            int postStart = 0, postEnd = this.widthSize - 1 - this.numErrorSpace , iter = 0;
            char myChar;
            while(postEnd <= strLength){
               strTemp = errorContent.substring(postStart, postEnd);
               iter = postEnd + 1;
               myChar = errorContent.charAt(iter);
               while((myChar != ' ') && (iter > postStart)){
                   iter --;
                   myChar = errorContent.charAt(iter);
               }               
               if(iter <= postStart){
                   strResult = strResult + "\n" + mySpace + errorContent.substring(postStart, strLength);
                   postEnd = strLength + 1;
               }else{
                   postEnd = iter;
                    strTemp = mySpace + errorContent.substring(postStart, postEnd);
                    strResult = strResult + "\n" + strTemp;
                    postStart = postEnd + 1;
                    postEnd += this.widthSize - 1 - this.numErrorSpace;
                    if(postEnd > strLength){
                        strResult = strResult + "\n" + mySpace + errorContent.substring(postStart, strLength);
                    }
               }               
            }
        }else{
            strResult += "\n" + mySpace + errorContent + "\n";
        }
        return strResult;
    }
    /**
     * writeLog() write content to log file
     * @param fileName full name (name + path) of log file
     * @param content 
     */
    public void writeLog(String fileName, String content) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), StandardCharsets.UTF_8));
            //Write data.
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            // Always close files.
            bufferedWriter.close();
        }catch(Exception ex) {
            System.err.println(ex.toString());
        }
    }
    /**
     * getListFile() get all list file in folder
     * @param folderPath is full path to folder store files
     * @return 
     */
    private String[] getListFile(String folderPath){
        // If folder created, show file list        
        File[] listFile = new File(folderPath).listFiles();            
        String[] arrFileName = new String[listFile.length];
        int i = 0;
        for(File file: listFile){
            if(file.isFile()){      
                arrFileName[i] =  file.getName();
                i++;                
            }
        } 
        return arrFileName;
    }
    
    public void createLogFile(String folderPath, int noLimitLogFile){
        String logFile = "";
        String [] strArr = getListFile(folderPath);
        Utilities.sortStringArrayASC(strArr);
        int lengthArr = strArr.length;
        
        String[] strTmp = new String[lengthArr];
        
        for(int i = lengthArr - 1; i >= 0; i --){
            String str = strArr[i].substring(3, 5);            
            if(Utilities.isNumber(str)){
                int no = Integer.parseInt(str);
                if(no < noLimitLogFile){
                    //delete & rename file no++                    
                    try{                        
                        File delFile, renameFile;
                        int num = no + 1;
                        if(num < 10){
                            delFile = new File(folderPath + "/log0" + num + ".info");
                        }else{
                            delFile = new File(folderPath + "/log" + num + ".info");
                        }
                        delFile.delete();
                        
                        //rename file
                        renameFile = new File(folderPath + "/" + strArr[i]);
                        renameFile.renameTo(delFile);
                    }catch(Exception ex){
                        System.err.println("Delete file error: " + ex.toString());
                    }                    
                }
            }else{
                try{                    
                    File firstFile = new File(folderPath + "/log01.info");    
                    if(firstFile.exists()){
                        firstFile.delete();
                    }
                    
                    File presentFile = new File(folderPath + "/log.info");
                    presentFile.renameTo(firstFile);
                }catch(Exception ex){
                    System.err.println("" + ex.toString());
                }
            }
        } 
        try{
            //Create new log file
            logFile = folderPath + "/log.info";
            File newFile = new File(logFile);
            newFile.createNewFile();            
        }catch(IOException ex){
            System.err.println("Error create new log file: " + ex.toString());
        }        
    }
}
