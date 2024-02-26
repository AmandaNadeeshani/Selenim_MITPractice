package com.mipractice.qa.base;

import com.mipractice.qa.utils.utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {

    WebDriver driver;
    public Properties prop;
    public Properties dataprop;


    public void loadPropertiesFile() throws IOException {
        prop= new Properties();
        File propfile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\mipractice\\qa\\config\\config.properties");
        FileInputStream fis = new FileInputStream(propfile);
        prop.load(fis);

        dataprop= new Properties();
        File datapropfile = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\mipractice\\qa\\testdata\\testdata.properties");
        FileInputStream dataP = new FileInputStream(datapropfile);
        dataprop.load(dataP);

    }
    public WebDriver initializeBrwoser(String browserName){

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }
        //  driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(utilities.Imlicit_WaitTime));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(utilities.Page_waitTime));
        driver.get(prop.getProperty("url"));
        return driver;
    }

}
