package pom;

import org.json.JSONObject;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import utils.uihelper.BasicActions;

public class WelcomePage extends BasicActions
{
    public WelcomePage(WebDriver driver)
    {
        super(driver);
    }

    public boolean searchBeer(String beer)
    {
        boolean result=true;
        typeByXpath("//input",beer);
        clickByXpath("//button");
        try{waitForAlertAndAccept(5);result=false;}catch (TimeoutException e){}

        return result;

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
