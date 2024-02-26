package com.mipractice.qa.listeners;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mipractice.qa.utils.ExtentReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class MyListeners implements ITestListener {
    ExtentReports extentReport;
    ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {
        try {
            extentReport = ExtentReporter.generateExtentReport();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        extentTest = extentReport.createTest(testName);
        extentTest.log(Status.INFO, testName + "strt executing");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        extentTest.log(Status.PASS, testName + "got successfully exectuted");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            // Log the exception if unable to access the driver field
            e.printStackTrace();
        }
        if (driver != null) {
            // Take screenshot
            File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
            try {
                // Copy screenshot file
                FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
                // Add screenshot to extent report
                extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
            } catch (IOException e) {
                // Log the exception if unable to copy the screenshot file
                e.printStackTrace();
            }
        }
        // Log failure details and status
        extentTest.log(Status.FAIL, testName + " got failed");
        extentTest.log(Status.INFO, result.getThrowable());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, testName + "got skiipped");

    }

    @Override
    public void onFinish(ITestContext context) {
     extentReport.flush();
    }

}