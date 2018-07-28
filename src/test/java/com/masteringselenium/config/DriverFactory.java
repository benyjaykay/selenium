package com.masteringselenium.config;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.masteringselenium.config.DriverType.FIREFOX;

public class DriverFactory {
    private RemoteWebDriver webDriver;
    private DriverType selectedDriverType;
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");

    public DriverFactory(){
        DriverType driverType =FIREFOX;
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        try{
            driverType = DriverType.valueOf(browser);
        }catch(IllegalArgumentException ignored){
            System.err.println("unknown driver defaulting to: " + driverType);
        }catch(NullPointerException ignored){
            System.err.println("null driver value defaulting to:" + driverType);
        }
        selectedDriverType = driverType;
    }

    public RemoteWebDriver getDriver(){
        if(null == webDriver){
            initWebDriver(selectedDriverType);
        }
        return webDriver;
    }
    public void quitDriver(){
        if(null != webDriver){
            webDriver.quit();
            webDriver = null;
        }
    }
    private void initWebDriver(DriverType driverType){
        System.out.println("OS" + operatingSystem + "\n Architecture: " + systemArchitecture + " \nSelectedBrowser: " + selectedDriverType + "\n");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        driverType.getWebDriverObject(desiredCapabilities);
    }
}
