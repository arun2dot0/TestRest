package services.test.RestAssuredTest.TestNG;

import static com.jayway.restassured.RestAssured.given;

import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import org.testng.annotations.BeforeClass;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;

import services.test.RestAssuredTest.TestNG.Util.TemplateUtil;

public abstract class BaseTestNG {
	
	enum HTTP_METHOD_PAYLOAD {POST,PUT,DELETE};
	RequestSpecification reqHeaders =null;
	TemplateUtil templateUtil = new TemplateUtil();
	
	 static final String GET = "get";
	 static final String POST = "post";
	 static final String PUT = "put";
	
	 static final String DEFAULT_INPUT_PAYLOAD ="application/json";
	
	
    @BeforeClass(alwaysRun=true)
    public void init()
    {
    	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    	
       RestAssured.baseURI = "http://localhost:8080/";
       
       /*
        * Sample app doesnt need any headers adding just to show capability
        */
		 reqHeaders =given().header("VERSION", "0.1.0")
		.header("NAME", "RestService");
		 
		 
		
		 
    }
    
    private <T> RequestSpecification loadParams(RequestSpecification requestSpec,Optional<Map<String, T>> params)
    {
    	if (params.isPresent()) {
    		Map<String, T> inputParams = params.get();
    		for (String key : inputParams.keySet()) {
    			requestSpec = requestSpec.param(key, inputParams.get(key));
    		}
    	}
    	return requestSpec;
    }
    
    
    private <T> RequestSpecification loadReqHeaders(Optional<Map<String, T>> params)
    {
    	RequestSpecification requestSpec = reqHeaders.given();
		return loadParams(requestSpec,params);
    }
    

	
    private <T> ValidatableResponse httpGetSimpleTest(String url, Optional<Map<String, T>> params) {
		RequestSpecification requestSpec = loadReqHeaders(params);
		return requestSpec.get(url)
				.then()
				.statusCode(200)
				;
	}
	
 
    
	private <T> ValidatableResponse httpPostPutDeleteSimpleTest(HTTP_METHOD_PAYLOAD httpMethod, String url,
			Optional<Map<String, T>> params, Optional<String> payload) {
		RequestSpecification requestSpec = reqHeaders.given();
		Response response = null;
		if (params.isPresent()) {
			Map<String, T> inputParams = params.get();
			for (String key : inputParams.keySet()) {
				requestSpec = requestSpec.param(key, inputParams.get(key));
			}
		}

		if (payload.isPresent()) {
			requestSpec = requestSpec.given().contentType(DEFAULT_INPUT_PAYLOAD).with().body(payload.get());
		}

		switch (httpMethod) {
			case PUT: {
				response = requestSpec.put(url);
				break;
			}
			case POST: {
				response = requestSpec.post(url);
				break;
			}
			case DELETE: {
				response = requestSpec.delete(url);
				break;
			}
		}
		return response.then().statusCode(200);
	}
	 
	 public ValidatableResponse httpGetSimpleTest(String url)
	 {
		 return httpGetSimpleTest(url, Optional.ofNullable(null));
	 }
	

	 public <T> ValidatableResponse httpGetSimpleTest(String url,Map<String,T> params)
	 {
		return  httpGetSimpleTest(url, Optional.of(params));
	 }

	 public ValidatableResponse httpPutSimpleTest(String url,String jsonPayload)
	 {
		return httpPostPutDeleteSimpleTest(HTTP_METHOD_PAYLOAD.PUT,url,Optional.ofNullable(null),Optional.of(jsonPayload));
	 }
	 public ValidatableResponse httpPostSimpleTest(String url,String jsonPayload)
	 {
		return httpPostPutDeleteSimpleTest(HTTP_METHOD_PAYLOAD.POST,url,Optional.ofNullable(null),Optional.of(jsonPayload));
	 }
}
