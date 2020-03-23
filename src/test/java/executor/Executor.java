package executor;

import generic.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pom.SearchResultPage;
import pom.WelcomePage;
import utils.apihelper.HttpMethods;
import utils.BaseTest;

public class Executor extends BaseTest implements Constants
{
    @Test
    public void validatingUiWithAPI()
    {
        String beerName="blonde";
        WelcomePage wp=new WelcomePage(driver);
        int resultsUICount=0;

        boolean flag=wp.searchBeer(beerName);
        SearchResultPage sp=new SearchResultPage(driver);
        if(flag)
            resultsUICount = sp.getResultCount();

        JSONObject uiResults = sp.getResultValues();
        HttpMethods hp=new HttpMethods(apiUri);
        hp.setEndPoint("beers");
        hp.addParameter("beer_name",beerName);
        String body= hp.get200();

        JSONArray apiResult= new JSONArray(body);
        int resultItemsCount=apiResult.length();

        Assert.assertEquals(resultItemsCount,resultsUICount);
        SoftAssert sa=new SoftAssert();
        for(int i=0;i<apiResult.length();i++)
        {
            String name=apiResult.getJSONObject(i).getString("name");
            sa.assertEquals(name,uiResults.getJSONObject(name).getString("name"),"asserting the name of the api and ui");
            sa.assertEquals(apiResult.getJSONObject(i).getString("tagline"),uiResults.getJSONObject(name).getString("tagline"),"asserting the tagline of the api and ui");
            sa.assertEquals("First brewed: "+apiResult.getJSONObject(i).getString("first_brewed"),uiResults.getJSONObject(name).getString("first_brewed"),"asserting the first_brewed of the api and ui");
            sa.assertEquals(apiResult.getJSONObject(i).getString("description"),uiResults.getJSONObject(name).getString("description"),"asserting the description of the api and ui");
        }
        sa.assertAll();

    }

}

