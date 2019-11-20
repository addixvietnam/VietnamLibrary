/**
 * Created on Fri Nov 15 11:30:44 ICT 2019
 * HeartCore Robo Desktop v5.0.5 (Build No. 5.0.5-20190816.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;
import java.io.File;
import vn.addix.utils.*;

public class Zip extends DefaultJavaTestScript  {
    
	public void testZip(){
		File file = new File(GlobalVars.WORK_DIRECTORY +  "\\logs\\log.info");
		String zipFileName = GlobalVars.WORK_DIRECTORY + "\\zipFile.zip";

		File dir = new File(GlobalVars.WORK_DIRECTORY + "\\logs");
		String zipDirName = GlobalVars.WORK_DIRECTORY + "\\zipFolder.zip";

		ZipFiles.zipSingleFile(file, zipFileName);

		ZipFiles.zipDirectory(dir, zipDirName);
	}
    
   public void test() {
   }
   
   public static void main(String args[]) {
      Zip script = new Zip();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "Zip@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
