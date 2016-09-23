package services.test.RestAssuredTest.TestNG;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.*;

import com.jayway.restassured.response.Response;

import services.test.RestAssuredTest.TestNG.Template.DO.Person;
import services.test.RestAssuredTest.TestNG.Util.TemplateUtil;


public class ExtendedTest extends BaseTestNG {
	
	 @Test(groups = { GET })
	 public void personSimpleTest()
	 {
		 httpGetSimpleTest("/person");
	 }

	 
	 
	 /*
     * Example test for input attribute
     */
    @Test(groups = { GET  },dependsOnMethods = { "personSimpleTest" })
    public void personTest1()
    {
    	httpGetSimpleTest("/person?firstname=Arun")
    			 .and()
                 .body("firstName",equalTo("Arun"))
                 .and()
                 .body("email",containsString("@"));
                 
    }

   
  

  /*
   * Test using template to drive the POST payload 
   * this way work on different  payloads easily 
   * 
   * Checks if Person sent in payload is the same that
   * is in the response 
   */
    @Test(groups = { POST  })
    public void personTest2() throws IOException
    {
    	TemplateUtil templateUtil = new TemplateUtil();
    	Person member = templateUtil.getPayloadObject("person.json", Person.class);
    	String payload = templateUtil.getPersonPayload(member);
    	
    	httpPostSimpleTest("/person"
   			 ,payload)
    		.and()
    		 .body("lastName",equalTo(member.getLastName()))
    	
    	;
    }
}
