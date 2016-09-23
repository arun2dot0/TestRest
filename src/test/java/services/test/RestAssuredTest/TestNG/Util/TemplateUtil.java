package services.test.RestAssuredTest.TestNG.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.gson.Gson;

import services.test.RestAssuredTest.TestNG.Template.DO.Person;
import services.test.RestAssuredTest.TestNG.Template.DO.Payload;

public class TemplateUtil{
	
	private static String ENV = "dev";// set this up in properties when implementing for project
	
	private String getTemplate(String template,Payload payload) throws IOException
	{
		 MustacheFactory mf = new DefaultMustacheFactory();
		 Mustache mustache =  mf.compile(template);
		 StringWriter sw = new StringWriter();
		    mustache.execute(sw, payload).flush();
		    System.out.println( sw.getBuffer().toString());
		    return sw.getBuffer().toString();
	}
	
	
	public String getPersonPayload(Person member) throws IOException
	{
		    return getTemplate("person.mustache", member);
		  
	}
	
	
	public <T> T getPayloadObjectMigrate(String jsonString, Class<T> type) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, type);
		
	}
	public <T> T getPayloadObject(String jsonFile, Class<T> type) {
		Gson gson = new Gson();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(ENV+"/payload/"+jsonFile).toURI()))) {
			return gson.fromJson(br, type);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	



}
