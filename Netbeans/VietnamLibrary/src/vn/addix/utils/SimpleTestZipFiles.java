
package vn.addix.utils;

import java.io.File;


public class SimpleTestZipFiles {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Addix.02.DESKTOP-OTP7J5T\\MyAutomation\\logs\\log.info");
        String zipFileName = "D:/zipFile.zip";
        
        File dir = new File("C:\\Users\\Addix.02.DESKTOP-OTP7J5T\\MyAutomation\\logs");
        String zipDirName = "D:/zipFolder.zip";
        
        //ZipFiles myZip = new ZipFiles();
        ZipFiles.zipSingleFile(file, zipFileName);
        
        ZipFiles.zipDirectory(dir, zipDirName);                
    }
}
