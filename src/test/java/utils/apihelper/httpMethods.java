package utils.apihelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class httpMethods
{
    public httpMethods(String uri)
    {
        RestAssured.baseURI=uri;
    }

    public void overRideUri(String uri)
    {
        RestAssured.baseURI=uri;
    }

    private String url="";
    public void setEndPoint(String endPointName)
    {
        url="/"+endPointName+"?";
    }

    int parameterCount;
    public void addParameter(String key,String value)
    {
        if(parameterCount>0)
        {
            url=url+"&"+key+"="+value;
        }else {
            url=url+key+"="+value;
        }
        parameterCount++;

    }

    public void cleanObject()
    {
        parameterCount=0;
        url="";
    }

    public String get200()
    {
        RequestSpecification request=RestAssured.given();
        Response rs=request.get(url);
        Assert.assertEquals(200,rs.statusCode());

        return rs.getBody().asString();

    }
}
