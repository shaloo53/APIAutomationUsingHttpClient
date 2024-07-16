package com.api.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.base.Base;
import com.api.client.RestClient;
import com.api.util.TestUtil;

public class GetAPITest extends Base {
	Base base;
	RestClient restClient;
	String URL;
	String apiURL;
	String url;


	@BeforeMethod
	public void setUp() {
		base = new Base();
		URL = prop.getProperty("URL");
		apiURL = prop.getProperty("ServiceURL");
		url = URL + apiURL;

		System.out.println("before method");
	}

	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		CloseableHttpResponse httpResponse = restClient.get(url);
		System.out.println("test 1");
		// status code
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code is " + statusCode);
		Assert.assertEquals(statusCode,STATUS_CODE_200,"Status code is not 200.");
		
		// Payload body
		HttpEntity entity = httpResponse.getEntity();
		String entityResponse = EntityUtils.toString(entity, "UTF-8");

		JSONObject jsonObject = new JSONObject(entityResponse);
		System.out.println("JsonObject is " + jsonObject);

		// Header
		Header[] headersArray = httpResponse.getAllHeaders();
		Map<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("All headers are : " + allHeaders);
		
		//Fetch perPage Value
		String totalValue = TestUtil.getValueByJPath(jsonObject, "/per_page");
		System.out.println("Value of per page is "+totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 6);
		
		//Fetch 1st data
		String LastNameOf1stIndex = TestUtil.getValueByJPath(jsonObject, "/data[0]/last_name");
		int idOf1stIndex = Integer.parseInt(TestUtil.getValueByJPath(jsonObject, "/data[0]/id"));
		System.out.println(LastNameOf1stIndex + " "+idOf1stIndex);
		
	}



}
