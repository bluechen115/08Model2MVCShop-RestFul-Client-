package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.ProductBoard;

public class RestHttpClientApp02 {

	public static void main(String[] args) throws Exception{
		
		System.out.println("\n====================================\n");
		// 1.1 Http Get 방식 Request : JsonSimple lib 사용
		RestHttpClientApp02.addProductTestPost_SimpleJson();
		
	}
	
	public static void addProductTestPost_SimpleJson() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		/*Product product = new Product();
		ProductBoard productBoard = new ProductBoard();
		
		product.setManuDate("15-05-05");
		product.setPrice(1000);
		product.setProdName("레스트");
		
		productBoard.setBoardDetail("통신 팔려고함");
		productBoard.setQuantity(2);
		productBoard.setTitle("레스트팔아용");*/
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("manuDate", "15-05-05");
		jsonObject.put("price", 1000);
		jsonObject.put("prodName", "레스트");
		jsonObject.put("boardDetail", "통신 팔려고함");
		jsonObject.put("quantity", 2);
		jsonObject.put("title", "레스트 팔아용");
		
		HttpEntity httpEntity01 = new StringEntity(jsonObject.toJSONString(), "utf-8");
		
		httpPost.setEntity(httpEntity01);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity httpEntity = httpResponse.getEntity();
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
		
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonObj);
	}

}
