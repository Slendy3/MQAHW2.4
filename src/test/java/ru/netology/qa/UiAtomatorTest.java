package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.Locator;

public class UiAtomatorTest {

    private AndroidDriver driver;
    private Locator locator;

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private final String textToSet = "testing";
    private final String textToSetEmpty = " ";

    @BeforeEach
    public void setUp() {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "myAndroid");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver(getUrl(), desiredCapabilities);

        locator = new Locator(driver);
    }

    @Test
    public void EmptyInput() {
        locator.userInput.sendKeys(textToSet);
        locator.buttonChange.click();
        locator.textToBeChanged.isDisplayed();
        locator.userInput.sendKeys(textToSetEmpty);
        locator.buttonChange.click();
        locator.textToBeChanged.isDisplayed();
        Assertions.assertEquals(textToSet, locator.textToBeChanged.getText());
    }

    @Test
    public void NewActivity() {
        locator.userInput.sendKeys(textToSet);
        locator.buttonActivity.click();
        locator.text.isDisplayed();
        Assertions.assertEquals(textToSet, locator.text.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

