package core.app;

import java.net.MalformedURLException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import net.jhavar.http.HttpGet;

//import java.util.regex.Pattern;
//import java.util.regex.Matcher;

/**
 * 
 * An object that links the program with a shoe search API.
 * Designed specially with adidas.ca's API in mind.
 * 
 * Thread-safe.
 * 
 * @author Raghav Jhavar
 */
public class ShoeAPI {
	
	
	/**
	 * Adidas shoe search api endpoint.
	 */
	private static final String endpoint = "https://www.adidas.ca/api/search/suggestions/#query#?sitePath=en";
	private static Gson gson = new Gson();
	
	private static Shoe[] shoes;
	/**
	 * @param query The name of the shoe being searched for
	 * @return an array of all the shoes found by the given query.
	 * @throws MalformedURLException
	 */
	public static String getShoesFromSearchQuery(String query) throws MalformedURLException {
		
		HttpGet hg = new HttpGet(endpoint.replace("#query#", query), false);
		//Parse the json to get the array
		JsonParser parser = new JsonParser();
		JsonArray productArray = parser.parse(hg.get())
										.getAsJsonObject()
										.get("products")
										.getAsJsonArray();
		
		//Convert the json array to java objects
		Shoe[] shoesFound = gson.fromJson(productArray, Shoe[].class);
		shoes = shoesFound;
		ChatSession.setShoesPesented(shoes);
		return getShoes(5);
	}
	
	private static String getShoes(int numShoes) {
		StringBuilder result = new StringBuilder();
		
		
		//Make sure numShoes does not exceed shoes.length.
		numShoes = numShoes > shoes.length ? shoes.length : numShoes;
		for(int i = 0; i < numShoes; i++) {
			result.append(String.format(i+1 + ") Shoe: %s, Price: %d\n", shoes[i].getDisplayName(), shoes[i].getPrice()));
			//String hyperlink = "<a href='" + shoes[i].getProductUrl() + "'>" +"https://www.adidas.ca/en" + "</a>"; //changed
			//result.append("URL: https://www.adidas.ca/en "+ hyperlink + "\n"); //changed
			result.append("URL: https://www.adidas.ca/en "+ shoes[i].getProductUrl() + "\n");
		}
		
		return result.toString();
	}
//	public static void main(String[] args) throws MalformedURLException {
//		
//		String searchQuery = "soccer shoes";
//		
//		System.out.println(Arrays.toString(getShoesFromSearchQuery(searchQuery)));
//	}
}
