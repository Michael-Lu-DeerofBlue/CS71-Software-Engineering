/*
 * This class represents the Client that communicates with the RESTful API.
 * 
 * Implement the get() method according to the specification. 
 * You may add other methods and instance variables as needed.
 */
import java.util.*;
import java.lang.reflect.Array;
import java.io.FileReader;
import java.lang.Exception;
import java.net.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Client {
	
	private String host;
	private int port;
	private Map<String, String> productCache = new HashMap<>();
	
	public Client() {
		// use Node Express defaults
		host = "localhost";
		port = 3000;
	}
	
	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}



	/* IMPLEMENT THIS METHOD! */
	public Set<Product> get(String[] ids) {
		//Initializing
		Set<Product> productsSet = new HashSet<>();
		if (ids == null){
			throw new IllegalArgumentException("The String array input argument to .get() method is null.");
		}
		if (ids.length == 0){
			return productsSet;
		}
		//Generating URL
		String queryString = "";
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == null){
				throw new IllegalArgumentException("The String array input argument to .get() method contains a null String.");
			}
			if (ids[i].equals("")){
				throw new IllegalArgumentException("The String array input argument to .get() method contains an empty String.");
			}
			queryString = queryString + "id=" + ids[i];
			if (i != ids.length-1){
				queryString = queryString + "&";
			}
		}
		String urlString = "http://" + this.host + ":" + this.port + "/status?" + queryString;
		//Retrieve Data
		Scanner in = null;
		try {
		    URL url = new URL(urlString);
		    HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
		    conn.setRequestMethod("GET"); 
		    conn.connect(); 
		    int responsecode = conn.getResponseCode();
		    if (responsecode != 200) {
		    	System.out.println("Unexpected status code: " + responsecode);
		    }
		    else {
				in = new Scanner(url.openStream());
				JSONArray productsArray = null;
				while (in.hasNext()) {
				    String line = in.nextLine();
				    JSONParser parser = new JSONParser();
					Object obj = parser.parse(line);
					productsArray = (JSONArray) obj;
				    for (Object item : productsArray) {
						JSONObject JSONItem = (JSONObject) item;
						String productId = (String) JSONItem.get("id");
						String productStatus = (String) JSONItem.get("status");
						Product product = new Product(productId, productStatus);
						productCache.put(productId, productStatus);
						productsSet.add(product);
					}
				}
		    }
	
		}
		catch (ParseException e){
			throw new IllegalStateException("An exception occurs while processing the data read from the server");
		}
		catch (Exception e){
			//Caching
			for (int i = 0; i < ids.length; i++){
				String productStatus = "";
				if (productCache != null){
					if (productCache.containsKey(ids[i])){
						productStatus = productCache.get(ids[i]);
					}
					else{
						productStatus = "unknown";
					}
				}
				else{
					productStatus = "unknown";
				}
				Product product = new Product(ids[i], productStatus);
				productsSet.add(product);
			}
		}		
		finally {
			try { in.close(); }
			catch (Exception e) { }
		}
		return productsSet;
	}
}
