package pom;

import org.json.JSONObject;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.uihelper.BasicActions;

public class WelcomePage extends BasicActions
{
    public WelcomePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    @FindBy(tagName = "input")  WebElement searchField;
    @FindBy(tagName = "button") WebElement searchButton;

    public boolean searchBeer(String beer)
    {
        boolean result=true;
        type(searchField,beer);
        click(searchButton);
        try{waitForAlertAndAccept(1);result=false;}catch (TimeoutException e){}

        return result;

    }








}
