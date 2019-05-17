package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Discount;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.ProductBoard;

public class RestHttpClientApp02 {

	public static void main(String[] args) throws Exception{
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http POST 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp02.addProductTestPost_SimpleJson();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http POST 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp02.getProductTestGet_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 3.1 Http POST 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp02.getListProductPost_CodeHaus();
		
		System.out.println("\n====================================\n");
		// 4.1 Http POST 방식 Request : JsonSimple lib 사용
		RestHttpClientApp02.updateProductTestPost_Codehaus();
		
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
	
	public static void getProductTestGet_JsonSimple() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/getProduct/13/search";
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity httpEntity = httpResponse.getEntity();
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
		
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br);
		
		System.out.println(jsonObj);
		
	}
	
	public static void getListProductPost_CodeHaus() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/listProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		Search search = new Search();
		search.setCurrentPage(1);
		
		Page page = new Page();
		page.setPageSize(3);
		page.setCurrentPage(1);
		page.setPageUnit(3);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("page", page);
		jsonObject.put("search", search);
		
		HttpEntity httpEntity01 = new StringEntity(jsonObject.toJSONString(), "utf-8");
		httpPost.setEntity(httpEntity01);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity httpEntity = httpResponse.getEntity();
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
		
		JSONObject jsonObj = (JSONObject)JSONValue.parse(br);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		System.out.println(jsonObj);
	}
	
	public static void updateProductTestPost_Codehaus() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/updateProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		ProductBoard productBoard = new ProductBoard();
		Product product = new Product();
		
		productBoard.setBoardDetail("레스트디테일 수정");
		productBoard.setBoardNo(14);
		productBoard.setTitle("레스트제목 수정");
		
		product.setBoardNo(14);
		product.setPrice(15050);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("boardDetail", "레스트디테일 수정");
		jsonObject.put("boardNo", 14);
		jsonObject.put("title", "레스트제목 수정");
		jsonObject.put("price", 15000);
		
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
