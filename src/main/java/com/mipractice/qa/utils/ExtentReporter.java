package com.mipractice.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ExtentReporter {
    public static ExtentReports generateExtentReport() throws IOException {
        ExtentReports extentReport = new ExtentReports();
        //path where to generate the extent report
        File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
        ExtentSparkReporter sparkreport = new ExtentSparkReporter(extentReportFile);

        sparkreport.config().setTheme(Theme.DARK);
        sparkreport.config().setReportName("MIT practice automation");
        sparkreport.config().setDocumentTitle("MIT automation Report");
        sparkreport.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extentReport.attachReporter(sparkreport);
        Properties  configprop = new Properties();
        File configpropfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\mipractice\\qa\\config\\config.properties");
        FileInputStream fisConfigprop = new FileInputStream(configpropfile);
        configprop.load(fisConfigprop);

        extentReport.setSystemInfo("Application URL",configprop.getProperty("url"));
        extentReport.setSystemInfo("BrowserName", configprop.getProperty("browser"));
        extentReport.setSystemInfo("Email", configprop.getProperty("validEmail"));
        extentReport.setSystemInfo("password", configprop.getProperty("validpassword"));
        extentReport.setSystemInfo("operating system",System.getProperty("os.name"));
        extentReport.setSystemInfo("username",System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));

        return extentReport;


    }
}
