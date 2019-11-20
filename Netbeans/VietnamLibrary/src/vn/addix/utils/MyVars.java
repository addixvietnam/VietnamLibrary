package vn.addix.utils;

public class MyVars {
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
}
