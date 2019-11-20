/**
 * Created on Fri Nov 15 11:30:18 ICT 2019
 * HeartCore Robo Desktop v5.0.5 (Build No. 5.0.5-20190816.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;

public class GlobalVars extends DefaultJavaTestScript  {
    public static String WORK_DIRECTORY = "";
    public static int DEBUG = 0; //1: debug mode
    public static int MULTI_MACHINE = 0;
    public static int MULTI_THREAD = 0;
    public static String[] LIST_MACHINE;
    //Log information
    public static int LOG_WIDTH_SIZE = 0;
    public static String LOG_CHARACTER_HEADER = "*";
    public static String LOG_CHARACTER_ERROR = "=";
    public static int LOG_NUMBER_LIMIT_FILE = 10;
    public static int LOG_NUMBER_SPACE_ERROR = 3; 

    public static void setGlobalVariables(String[] array){
        WORK_DIRECTORY = array[0];
        DEBUG = Integer.parseInt(array[1]);
        MULTI_MACHINE = Integer.parseInt(array[2]);
        MULTI_THREAD = Integer.parseInt(array[3]);
        
        LOG_WIDTH_SIZE = Integer.parseInt(array[5]);
        LOG_CHARACTER_HEADER = array[6];
        LOG_CHARACTER_ERROR = array[7];
        LOG_NUMBER_SPACE_ERROR = Integer.parseInt(array[8]);
        LOG_NUMBER_LIMIT_FILE = Integer.parseInt(array[9]);
    }
    
	public void test() {
	}

	public static void main(String args[]) {
		GlobalVars script = new GlobalVars();
		ApplicationSupport robot = new ApplicationSupport();
		AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "GlobalVars@" + Integer.toHexString(script.hashCode()), args, System.out, false);
		new Thread(runnable).start();
	}
}
