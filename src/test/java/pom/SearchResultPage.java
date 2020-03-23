package pom;

import org.json.JSONObject;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.uihelper.BasicActions;

public class SearchResultPage extends BasicActions
{
    public SearchResultPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public int getResultCount()
    {

        return findElementsByXpath("//div[@class='card']").size();
    }

    public JSONObject getResultValues()
    {
        int resultCount=getResultCount();
        JSONObject result= new JSONObject();
        for(int i=0;i<resultCount;i++)
        {
            JSONObject temp=new JSONObject();

            temp.put("name",findElementsByTagName("h2").get(i).getText());
            temp.put("tagline",findElementsByXpath("//p[@class='card__text card__text--gray']").get(i).getText());
            temp.put("first_brewed",findElementsByXpath("//p[@class='card__text card__text--sm']").get(i).getText());
            temp.put("description",findElementsByXpath("//div[@class='card']//p[2]").get(i).getText());
            result.put(findElementsByTagName("h2").get(i).getText(),temp);
        }

        return result;

    }







}
