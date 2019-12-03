package vn.addix.utils;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public abstract class Selenium {
    private WebDriver seleniumDriver;
    private String browserDriverPath = "";    
    /**
     * Constructor to create the Selenium
     * @param chromePath 
     */
    public Selenium(String chromePath) {
        this.browserDriverPath = chromePath;        
    }
    /**
     * Set driver for chrome browser
     */
    public void setChromeDriver(){
        System.setProperty("webdriver.chrome.driver", browserDriverPath);
        seleniumDriver = new ChromeDriver();
    }
    /**
     * Connect to website by url
     * @param url to target website
     */
    public void connect(String url){
        seleniumDriver.get(url);
    }
    /**
     * Get the current access Url
     * @return 
     */
    public String getCurrentUrl(){
        return seleniumDriver.getCurrentUrl();
    }
    /**
     * Get ulr title
     * @return 
     */
    public String getTitle(){
        return seleniumDriver.getTitle();
    }
    /**
     * Click to the button with Class name
     * @param className 
     */
    public void click(String className){
        seleniumDriver.findElement(By.className(className)).click();
    }
    /**
     * 
     * @param className
     * @param pattern 
     */
    public void search(String className, String pattern) {
        WebElement element = seleniumDriver.findElement(By.className(className));
        element.sendKeys(pattern);
        element.sendKeys(Keys.ENTER);
    }
    /**
     * Auto select name of selector
     * @param className
     * @param name of selector
     */
    public void select(String className, String name){
        Select dropSelect = new Select(seleniumDriver.findElement(By.className(className)));
        dropSelect.selectByVisibleText(name);
    }
    
    /**
     * Close connection to  url
     */
    public void close() {
        seleniumDriver.close();
    }
    /**
     * Go to number page of website
     * @param xPathCustom include Xpath + page number
     */
    public void goToPage(String xPathCustom) {        
        seleniumDriver.findElement(By.xpath(xPathCustom)).click();
    }    
}
