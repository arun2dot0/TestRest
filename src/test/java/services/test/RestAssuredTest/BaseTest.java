package services.test.RestAssuredTest;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;



import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit test for Rest Services
 */
public class BaseTest 
{
	
	RequestSpecification reqHeaders =null;
    @Before
    public void init()
    {
    	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    	
        RestAssured.baseURI = "http://localhost:8080";
        
        /*
         * you can add the http headers here 
         * 
         * sample app doesnt require any headers though
         */
		 reqHeaders =given().header("VERSION", "0.1.0")
		.header("ENV", "dev");
    }

   
  
}
