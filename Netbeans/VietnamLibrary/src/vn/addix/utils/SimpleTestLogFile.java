package vn.addix.utils;


public class SimpleTestLogFile{
    
    public void initAddixVietnamLibrary(){
        AddixConfig myConfig = new AddixConfig();
        String [] arrConfig = myConfig.readConfig("Config.md");
        MyVars.setGlobalVariables(arrConfig);        
    }
      
    public static void main(String[] args){
        MyVars.WORK_DIRECTORY = "D:\\MyProjects\\Sources\\ADDIX-Library\\VietnamLibrary\\Netbeans\\VietnamLibrary";
        SimpleTestLogFile myTestLogFile = new SimpleTestLogFile();
        //Init program
        myTestLogFile.initAddixVietnamLibrary();
        //Print all variables what read from config file
        myTestLogFile.printGlobalVariables();
        //Write Log example
        myTestLogFile.testLog();
    }
    
    public void testLog(){
        String myStr = "Để đơn\n giản hóa thủ tục hành chính, giảm bớt những yêu cầu về giấy tờ cho người dân, \n" + 
                "Bộ Công an đang đẩy nhanh việc áp dụng công nghệ trong hoạt động đăng ký, cấp biển số phương tiện. " + 
                "Cụ thể, với xe làm thủ tục sang tên, chủ mới của xe chỉ cần đến cơ quan đăng ký nơi mình cư trú để làm thủ tục, " + 
                "không phải rút hồ sơ gốc. Cơ quan tiếp nhận hồ sơ xe đối chiếu và in giấy chứng nhận đăng ký xe. Khi đăng ký lại, " + 
                "kể cả xe đã chuyển quyền sở hữu nhiều lần, chủ mới của xe không cần đưa xe đến kiểm tra, chỉ cần ghi mã số định " + 
                "danh cá nhân, số hóa đơn điện tử, tên doanh nghiệp bán xe vào giấy khai sang tên. Cán bộ sẽ kiểm tra thông tin trên " + 
                "cơ sở dữ liệu quốc gia về dân cư, trên Cổng thông tin điện tử của Tổng cục thuế và hoàn thiện hồ sơ.";
        String myErr = "An Error “indicates serious problems that a reasonable application should not try to catch.”" +
"Both Errors and Exceptions are the subclasses of java.lang.Throwable class. Errors are the conditions which cannot get recovered by any handling techniques. It surely cause termination of the program abnormally. Errors belong to unchecked type and mostly occur at runtime. Some of the examples of errors are Out of memory error or a System crash error.";
        Log myLog = new Log(MyVars.LOG_WIDTH_SIZE, MyVars.LOG_NUMBER_SPACE_ERROR, MyVars.LOG_CHARACTER_HEADER, MyVars.LOG_CHARACTER_ERROR);
        String folderPath = MyVars.WORK_DIRECTORY + "\\logs";
        //Check present log file to backup and create new log file
        myLog.createLogFile(folderPath, MyVars.LOG_NUMBER_LIMIT_FILE);  
        String logFile = folderPath + "\\log.info";
        //Write to header log file
        myLog.writeLog(logFile, myLog.formatStringHeaderAndFooter(true));
//        System.out.println(myLog.formatStringHeaderAndFooter(true));
        //Write content to log file
        myLog.writeLog(logFile, myLog.formatStringContent(myStr));
//        System.out.println(myLog.formatStringContent(myStr));
        //Write error to log file
        myLog.writeLog(logFile, myLog.formatStringError("Exceptions In Java", myErr));
        
        myLog.writeLog(logFile, myLog.formatStringError("Error Write Output To Excel File D:\\RPA\\ANA\\ANA_2020\\ANA-VIETNAM\\Netbeans\\ANA-ADDIX/output/data_page_source/HTML/wirelesswire/20191128_wirelesswire.xlsx",  
                " java.lang.StringIndexOutOfBoundsException: String index out of range: 147"));
//        System.out.println(myLog.formatStringError("Exceptions In Java", myErr));
        //Write to footer log file
        myLog.writeLog(logFile, myLog.formatStringHeaderAndFooter(false));        
//        System.out.println(myLog.formatStringHeaderAndFooter(false));
    }
    /**
     * Print all global variables to screen
     */
    public void printGlobalVariables(){
        System.out.println("WORK_DIRECTORY = " + MyVars.WORK_DIRECTORY);
        System.out.println("DEBUG MODE = " + MyVars.DEBUG);
        System.out.println("LOG_WIDTH_SIZE = " + MyVars.LOG_WIDTH_SIZE);
        System.out.println("LOG_CHARACTER_HEADER = " + MyVars.LOG_CHARACTER_HEADER);
        System.out.println("LOG_CHARACTER_ERROR = " + MyVars.LOG_CHARACTER_ERROR);
        System.out.println("LOG_NUMBER_SPACE_ERROR = " + MyVars.LOG_NUMBER_SPACE_ERROR);
        System.out.println("LOG_NUMBER_LIMIT_FILE = " + MyVars.LOG_NUMBER_LIMIT_FILE);
    }
}
