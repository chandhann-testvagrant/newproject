package executor;

import generic.constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pom.welcomePage;
import utils.apihelper.httpMethods;
import utils.baseTest;

public class executor extends baseTest implements constants
{
    @Test
    public void ValidatingUiWithAPI()
    {
        String beerName="blonde";
        welcomePage wp=new welcomePage(driver);
        int resultsUICount=0;
        if(wp.searchBeer(beerName))
            resultsUICount = wp.getResultCount();

        JSONObject uiResults = wp.getResultValues();
        httpMethods hp=new httpMethods(APIuri);
        hp.setEndPoint("beers");
        hp.addParameter("beer_name",beerName);
        String body= hp.get200();

        JSONArray APIresult= new JSONArray(body);
        int resultItemsCount=APIresult.length();

        Assert.assertEquals(resultItemsCount,resultsUICount);
        SoftAssert sa=new SoftAssert();
        for(int i=0;i<APIresult.length();i++)
        {
            String name=APIresult.getJSONObject(i).getString("name");
            sa.assertEquals(name,uiResults.getJSONObject(name).getString("name"),"asserting the name of the api and ui");
            sa.assertEquals(APIresult.getJSONObject(i).getString("tagline"),uiResults.getJSONObject(name).getString("tagline"),"asserting the tagline of the api and ui");
            sa.assertEquals("First brewed: "+APIresult.getJSONObject(i).getString("first_brewed"),uiResults.getJSONObject(name).getString("first_brewed"),"asserting the first_brewed of the api and ui");
            sa.assertEquals(APIresult.getJSONObject(i).getString("description"),uiResults.getJSONObject(name).getString("description"),"asserting the description of the api and ui");
        }
        sa.assertAll();

    }

}

