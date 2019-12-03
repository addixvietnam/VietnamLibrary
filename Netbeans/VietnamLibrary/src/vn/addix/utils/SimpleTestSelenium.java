package vn.addix.utils;

public class SimpleTestSelenium {
    public static void main(String[] args){
        Selenium mySelenium = new Selenium("D:\\MyProjects\\libs\\chromedriver.exe") {
        };
        mySelenium.setChromeDriver();
        mySelenium.connect("https://ascii.jp/");
        //Search with keyword
        mySelenium.search("txtBoxSize", "AI");
        //Select dropbox
        mySelenium.click("gsc-option-selector");
        mySelenium.click("gsc-option-menu");
        mySelenium.click("gsc-option-menu-item gsc-option-menu-item-highlighted");
//        mySelenium.close();

    }
}
