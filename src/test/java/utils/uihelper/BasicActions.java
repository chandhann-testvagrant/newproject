package utils.uihelper;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasicActions
{
    private WebDriver driver;
    protected BasicActions(WebDriver driver)
    {
        this.driver=driver;
    }

    protected void clickByXpath(String xpath)
    {
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                driver.findElement(By.xpath(xpath)).click();
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }
    }

    protected void clickByID(String id)
    {
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.id(id)));
                driver.findElement(By.id(id)).click();
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }
    }

    protected void typeByXpath(String xpath,String value)
    {
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                driver.findElement(By.xpath(xpath)).sendKeys(value);
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }
    }

    protected List<WebElement> findElementsByXpath(String xpath)
    {
        List<WebElement> ele_s=new ArrayList<WebElement>();
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                ele_s= driver.findElements(By.xpath(xpath));
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }

        return ele_s;
    }

    protected List<WebElement> findElementsByTagName(String tagName)
    {
        List<WebElement> ele_s=new ArrayList<WebElement>();
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                ele_s= driver.findElements(By.tagName(tagName));
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }

        return ele_s;
    }

    protected List<WebElement> findElementsByClassName(String className)
    {
        List<WebElement> ele_s=new ArrayList<WebElement>();
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                ele_s= driver.findElements(By.className(className));
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }

        return ele_s;
    }

    protected String getTextByXpath(String xpath)
    {
        String text="";
        int attempts = 0;
        while(attempts < 5)
        {
            try
            {
                new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
               text= driver.findElement(By.xpath(xpath)).getText();
                break;
            } catch(StaleElementReferenceException e){}

            attempts++;
        }
        return text;
    }

    protected void waitForAlertAndAccept(long timeoutInSec)
    {
        new WebDriverWait(driver,timeoutInSec).until(ExpectedConditions.alertIsPresent()).accept();
    }

}
