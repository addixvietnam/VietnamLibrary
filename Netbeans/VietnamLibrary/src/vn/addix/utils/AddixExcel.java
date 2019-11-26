package vn.addix.utils;

import java.io.FileInputStream;
import java.net.URLDecoder;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

/**
 * 
 * Read and write excel file with Addix format
 */
public abstract class AddixExcel {    
    private String fileName = "";

    public AddixExcel(String fileName) {
        this.fileName = fileName;
    }
        
    /**
     * Notes: please delete empty column and row which we don't use
     * @param fileName is full path to excel name file
     * @param sheetName is name of sheet excel
     * @param isReadAll have 2 cases. If is "true" function will read all excel file, else the program will read all column in array
     * @param numSkipRow is a number of row which we will skip when we read 
     * @param columnArray is an array which storage column indexes
     * @return string data array 2 dimension
     * @throws Exception 
     */
    public String[][] readExcelFile(String sheetName, 
            boolean isReadAll, int[] columnArray) throws Exception{
        try{
            String strPathInFile = URLDecoder.decode(fileName, "UTF-8");
            FileInputStream fileInputStream = new FileInputStream(fileName);
           
            String [][] arrData = null;
            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();
            
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet mySheet = myWorkBook.getSheet(sheetName);
            Iterator<Row> rowIterator = mySheet.iterator();
            int colDataSize = 0;
            if(isReadAll){//read all data from excel file
                colDataSize = (rowIterator.next()).getLastCellNum();
            }else{//read array column data from excel file
                colDataSize = columnArray.length;
            }            
            int rowDataSize = mySheet.getLastRowNum();
            arrData = new String[rowDataSize][colDataSize];            
            //Assign data to array            
            int i = 0;
            while(rowIterator.hasNext()){                
                //Skip row (title row)                                   
                Row row = rowIterator.next();                    
                
                if(row.getRowNum() > 0){                    
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int j = 0;
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        String cellValue = "";
                        if(isReadAll){
                            cellValue = dataFormatter.formatCellValue(cell);                                 
                            arrData[i][j] = cellValue;                           
                            j++;
                        }else{
                            for(int iter = 0; iter < columnArray.length; iter++){
                                if(cell.getColumnIndex() == (columnArray[iter]-1)){
                                    cellValue = dataFormatter.formatCellValue(cell);                                         
                                    arrData[i][j] = cellValue;                                    
                                    j++;
                                }
                            }                                                        
                        }
                    }
                    i++;
                }
            }                
            myWorkBook.close();
            fileInputStream.close();
            return arrData;
        }catch(Exception ex){
            throw ex;
        }
    }    

    public abstract boolean writeExcelFile(String sheetName, String[][] data);
    public abstract boolean writeExcelFile(String sheetName, int beginRow, int beginCol, String[][] data);
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
