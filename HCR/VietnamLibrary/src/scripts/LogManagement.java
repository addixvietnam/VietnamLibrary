/**
 * Created on Fri Nov 15 11:30:01 ICT 2019
 * HeartCore Robo Desktop v5.0.5 (Build No. 5.0.5-20190816.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;
import vn.addix.utils.*;

public class LogManagement extends DefaultJavaTestScript  {
    public void initAddixVietnamLibrary(){
        AddixConfig myConfig = new AddixConfig();
        String [] arrConfig = myConfig.readConfig(GlobalVars.WORK_DIRECTORY + "\\Config.md");
        GlobalVars.setGlobalVariables(arrConfig);
    }
    
    public void testLog(){
        String myStr = "Để đơn giản hóa thủ tục hành chính, giảm bớt những yêu cầu về giấy tờ cho người dân, " + 
            "Bộ Công an đang đẩy nhanh việc áp dụng công nghệ trong hoạt động đăng ký, cấp biển số phương tiện. " + 
            "Cụ thể, với xe làm thủ tục sang tên, chủ mới của xe chỉ cần đến cơ quan đăng ký nơi mình cư trú để làm thủ tục, " + 
            "không phải rút hồ sơ gốc. Cơ quan tiếp nhận hồ sơ xe đối chiếu và in giấy chứng nhận đăng ký xe. Khi đăng ký lại, " + 
            "kể cả xe đã chuyển quyền sở hữu nhiều lần, chủ mới của xe không cần đưa xe đến kiểm tra, chỉ cần ghi mã số định " + 
            "danh cá nhân, số hóa đơn điện tử, tên doanh nghiệp bán xe vào giấy khai sang tên. Cán bộ sẽ kiểm tra thông tin trên " + 
            "cơ sở dữ liệu quốc gia về dân cư, trên Cổng thông tin điện tử của Tổng cục thuế và hoàn thiện hồ sơ.";
            
        String myErr = "An Error “indicates serious problems that a reasonable application should not try to catch.”" +
            "Both Errors and Exceptions are the subclasses of java.lang.Throwable class. Errors are the conditions" + 
            "which cannot get recovered by any handling techniques. It surely cause termination of the program abnormally." + 
            "Errors belong to unchecked type and mostly occur at runtime. Some of the examples of errors are Out of memory" + 
            " error or a System crash error.";
            
        Log myLog = new Log(GlobalVars.LOG_WIDTH_SIZE, GlobalVars.LOG_NUMBER_SPACE_ERROR, GlobalVars.LOG_CHARACTER_HEADER, GlobalVars.LOG_CHARACTER_ERROR);
        String folderPath = GlobalVars.WORK_DIRECTORY + "\\logs";
        //Check present log file to backup and create new log file
        myLog.createLogFile(folderPath, GlobalVars.LOG_NUMBER_LIMIT_FILE);  
        String logFile = folderPath + "\\log.info";
        //Write to header log file
        myLog.writeLog(logFile, myLog.formatStringHeaderAndFooter(true));
        //Write content to log file
        myLog.writeLog(logFile, myLog.formatStringContent(myStr));
        //Write error to log file
        myLog.writeLog(logFile, myLog.formatStringError("Exceptions In Java", myErr));
        //Write to footer log file
        myLog.writeLog(logFile, myLog.formatStringHeaderAndFooter(false));        
    }
    
    public void test() {
        try {
            GlobalVars.WORK_DIRECTORY = "D:/MyProjects/Sources/ADDIX-Library/HCR/VietnamLibrary";
            //Init program
            this.initAddixVietnamLibrary();
            getContext().setVariable("WORK_DIRECTORY",  GlobalVars.WORK_DIRECTORY);  
            getContext().setVariable("DEBUG", GlobalVars.DEBUG);  
            getContext().setVariable("MULTI_MACHINE", GlobalVars.MULTI_MACHINE);  
            getContext().setVariable("MULTI_THREAD", GlobalVars.MULTI_THREAD);  
            getContext().setVariable("LOG_WIDTH_SIZE", GlobalVars.LOG_WIDTH_SIZE);  
            getContext().setVariable("LOG_CHARACTER_HEADER", GlobalVars.LOG_CHARACTER_HEADER);               
            getContext().setVariable("LOG_CHARACTER_ERROR", GlobalVars.LOG_CHARACTER_ERROR);  
            getContext().setVariable("LOG_NUMBER_LIMIT_FILE", GlobalVars.LOG_NUMBER_SPACE_ERROR);  
            getContext().setVariable("LOG_NUMBER_SPACE_ERROR", GlobalVars.LOG_NUMBER_LIMIT_FILE);  

            //Write Log example
            this.testLog();

            //Zip test
            Zip myZip = new Zip();
            myZip.testZip();
            
        } catch (StopRequestException ex) {
            throw ex;
        }
    }
   
    public static void main(String args[]) {
        LogManagement script = new LogManagement();
        ApplicationSupport robot = new ApplicationSupport();
        AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "LogManagement@" + Integer.toHexString(script.hashCode()), args, System.out, false);
        new Thread(runnable).start();
    }
}
