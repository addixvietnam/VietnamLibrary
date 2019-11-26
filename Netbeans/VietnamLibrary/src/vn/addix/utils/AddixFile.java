package vn.addix.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AddixFile {
    /**
     * Copy file from source file to dest folder. 
     * @param srcFile
     * @param destFolder
     * @param newNameFile If don't rename file, set by ""
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     * @throws IOException 
     */    
    public void coppyExcelFile(String srcFile, String destFolder, String newNameFile) 
            throws UnsupportedEncodingException, FileNotFoundException, IOException{
        try{
            // Convert path to UTF-8
            srcFile = URLDecoder.decode(srcFile, "UTF-8");
            destFolder = URLDecoder.decode(destFolder, "UTF-8");         
            //Get path dest to copy
            String strOutFile = "";
            if(newNameFile.equals("")){
                String[] path = srcFile.split("[\\/]");
                strOutFile = destFolder + "/" + path[path.length -1];
            }else{
                strOutFile = destFolder + "/" + newNameFile;            
            }        
            // Copy excel file.   
            FileInputStream fileInputStream = new FileInputStream(srcFile);
            OutputStream fileOutputStream = new FileOutputStream(strOutFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }        
            // Close after completed change file.
            fileInputStream.close();
            fileOutputStream.close();
          
        }catch(Exception ex){
            throw ex;               
        }        
    }
    /**
     * delete file if it exist
     * @param fileName
     * @return 
     */
    public boolean deleteFile(String fileName){
        File myFile = new File(fileName);
        if(myFile.exists()){
            boolean success = myFile.delete();
            return true;
        }
        return false;
    }
}
