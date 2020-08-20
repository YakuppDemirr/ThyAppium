package com.testinium.thy;

import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import com.thoughtworks.gauge.Logger;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;


public class BaseTest {

    public static AppiumDriver appDriver;
    public static WebDriverWait wait;
    public static final Logger logger = new Logger();
    public static int elementSize;
    public static Point value = null;
    public static int x;
    public static int y;
    public static int y1;

    @BeforeSuite
    public void initializeDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.0");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.turkishairlines.mobile");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.turkishairlines.mobile.ui.ACSplash");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities
                .setCapability(MobileCapabilityType.NO_RESET, true);
        desiredCapabilities
                .setCapability(MobileCapabilityType.FULL_RESET, false);
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
        desiredCapabilities.setCapability("unicodeKeyboard", true);
        desiredCapabilities.setCapability("resetKeyboard", true);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        this.appDriver = new AndroidDriver(url, desiredCapabilities);
        this.wait=new WebDriverWait(appDriver,20);
    }

    public void clickById(String id){
        WebElement element = new WebDriverWait(appDriver, 30).until(ExpectedConditions.elementToBeClickable(By.id(id)));
        appDriver.findElement(By.id(id)).click();
    }

    public void clickByXpath(String xpath){
        WebElement element = new WebDriverWait(appDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        appDriver.findElement(By.xpath(xpath)).click();
    }

    public void setById(String id, String text){
        WebElement element = new WebDriverWait(appDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        element.clear();
        element.sendKeys(text);
    }

    public void setByXpath(String xpath, String text){
        WebElement element = new WebDriverWait(appDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        //element.clear();
        element.sendKeys(text);
        element.sendKeys(text);
    }

    public void clictToElement(WebElement element) {
        element.click();
    }

    public void optionClick(String key,Integer number){
        List<MobileElement> element =  (List<MobileElement>) appDriver.findElementsByClassName(key);
        clictToElement(element.get(number));
    }

    public int optionLength(String key){
        List<MobileElement> element = appDriver.findElementsByClassName(key);
        elementSize = element.size();
        return elementSize;
    }

    public static void swipe(int fromX,int fromY,int toX,int toY) {

        TouchAction action = new TouchAction(appDriver);
        action.press(PointOption.point(fromX,fromY))
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500))) //you can change wait durations as per your requirement
                .moveTo(PointOption.point(toX, toY))
                .release()
                .perform();
    }

    public void swipeDown(int pixelsToSwipe,String key) {

        try {
            value = appDriver.findElement(By.id(key)).getLocation();
            x = value.x;
            y = value.y;
            y1 = value.y+pixelsToSwipe;

            swipe(x, y1, x, y);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Step("<key> saniye bekle")
    public void waitBySeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void close(){
        appDriver.quit();
    }
}
