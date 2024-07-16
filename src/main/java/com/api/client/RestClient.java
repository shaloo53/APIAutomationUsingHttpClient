package com.api.client;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
		// 1. GET method without Headers:
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpsClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse httpResponse=httpsClient.execute(httpGet);
		return httpResponse;
	}
	
		// 2. GET method with Headers:
		public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
			CloseableHttpClient httpsClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);
			for(Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
			
			CloseableHttpResponse httpResponse=httpsClient.execute(httpGet);
			return httpResponse;
		
	}
		
		// 3. POST method
		public CloseableHttpResponse post(String url , String entityString , Map<String, String> allHeaders) throws ClientProtocolException, IOException {
			CloseableHttpClient httpsClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new StringEntity(entityString)) ;
			httpPost.addHeader(null);for(Map.Entry<String, String> entry : allHeaders.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
			CloseableHttpResponse httpResponse = httpsClient.execute(httpPost);
			return httpResponse;
		}
}
