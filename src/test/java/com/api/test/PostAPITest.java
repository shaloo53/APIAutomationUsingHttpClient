package com.api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.api.base.Base;
import com.api.client.RestClient;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.qa.data.Users;

public class PostAPITest extends Base{
	Base base;
	RestClient restClient;
	String URL;
	String apiURL;
	String url;
	CloseableHttpResponse httpResponse;

	@BeforeMethod
	public void setUp() {
		base = new Base();
		URL = prop.getProperty("URL");
		apiURL = prop.getProperty("ServiceURL");
		url = URL + apiURL;

		System.out.println("before method");
	}
	
	@Test
	public void postAPITest() throws StreamWriteException, DatabindException, IOException {
		 restClient = new RestClient();
			// Header
			Map<String, String> HeaderMap = new HashMap<String, String>();
			HeaderMap.put("Content-Type","application/json");
		
			/*
			 * CloseableHttpResponse responseOfGET = restClient.get(url);
			 * System.out.println(responseOfGET.toString());
			 */
			//Will use jacksonAPI to convert java object to JSON which is called marshing 
			ObjectMapper mapper = new ObjectMapper();
			Users users = new Users("Shaloo");
			//object to json file
			mapper.writeValue(new File("G:/selenium learning/API_Automation/com.api/src/main/java/com/qa/data/UserJSONFile.json") , users);
			
			//jsonObject to jsonString
			String jsonString = mapper.writeValueAsString(users);
			
			//call post method
			CloseableHttpResponse httpResponse= restClient.post(url, jsonString, HeaderMap);
			
			//statusCode
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			Assert.assertEquals(statusCode, base.STATUS_CODE_201,"Status code is not 201.");
			
			//Response in java object
			String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			JSONObject responseJSON = new JSONObject(responseString);
			
			//json to java object -->unmarshing
			Users userObjectFromMapper = mapper.readValue(responseString, Users.class);
		//	System.out.println(userObjectFromMapper.getFirst_name() +" "+userObjectFromMapper.getId());
			System.out.println(userObjectFromMapper.getFirst_name());
			Assert.assertTrue(users.getFirst_name().equals(userObjectFromMapper.getFirst_name()));
			
	}
}
