package services.test.RestAssuredTest;

import static org.hamcrest.Matchers.*;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
@RunWith(JUnit4.class)
public class ExtendedTest extends BaseTest {
	

    /**
     * get Customer Test 
     * 
     * Check if empty params has output with firstName=Jason 
     * 
     */
    @org.junit.Test
    public void personBaseTest()
    {
    	reqHeaders.
        get("/person").
         then()
                 .body("firstName", equalTo("Jason"))
                ;
    }
    
   
   
}
