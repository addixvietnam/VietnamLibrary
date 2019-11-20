package vn.addix.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class AddixConfig extends Config{
    /**
     * readConfig() read all data in config file
     * @param fileName name of file config
     * @return 
     */
    @Override
    public String[] readConfig(String fileName) {
//        String fullPath = new File("").getAbsoluteFile() + "/"  + fileName;              
        // This will reference one line at a time
        String line = null;
        ArrayList<String> strData = new ArrayList<>();
        try {
            File file = new File(fileName);
            if(file.exists()){
                // FileReader reads text files in the default encoding.
                FileReader fileReader = new FileReader(fileName);
                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while((line = bufferedReader.readLine()) != null) {
                     if(line.trim() != ""){
                          if(!line.substring(0, 1).equals("#")){
                               String[] strArr = line.split("=");
                               String tmpData = "";
                               if(strArr[0].toString().trim().equals("Character error")){
                                   tmpData = line.substring(17, line.length()).trim();
                               }else{
                                   tmpData = strArr[1].trim();
                               }
                               //Write data to arraylist
                               strData.add(tmpData);
                        }
                    }
                }
                // Always close files.
                bufferedReader.close();
           }
        }catch(FileNotFoundException ex) {
            System.err.println("FindNotFoundReadFile: " + ex.toString());
        }catch(Exception ex) {
            System.err.println("ReadFile: " + ex.toString());
        }
        return strData.toArray(new String[strData.size()]);
    }    

    @Override
    public void writeConfig(String fileName, String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteConfig(String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
