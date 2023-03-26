package com.library.step_definitions;

import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before("@ui")
    public void setUp(){

        System.out.println("this is coming from BEFORE");
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));


    }

    @After
    public void tearDown(Scenario scenario){

        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }

        Driver.closeDriver();

    }
    @Before("@db")
    public void setupDB(){
        System.out.println("Connecting to database...");
        DB_Util.createConnection();
    }

    @After("@db")
    public void closeDB(){
        System.out.println("Closing DB connection...");
        DB_Util.destroy();
    }

}
