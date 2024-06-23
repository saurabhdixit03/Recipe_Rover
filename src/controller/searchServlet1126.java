package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.io.StringReader;

/**
 * Servlet implementation class SearchServlet
 * This is copy of working copy of search servlet its a backup copy .
 */
//@WebServlet("/SearchServlet")
public class searchServlet1126 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Replace 'YOUR_API_KEY' with your OpenAI API key
	private static final String API_KEY = "#your_apiKey";

	static String userInput = "";
	static String recipeDes = "";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String choicesArray  = "AAAA";
		System.out.println("strarting serchservlet ....");
		// TODO Auto-generated method stub
		// String userInput = request.getParameter("allIngredentsArray");

		String value = (String) request.getAttribute("allIngredentsArray");
		// Example: Output a simple response
		//System.out.println("userInput in searchservlet=" + value);
		// System.out.println("Before getChoicesFromAPI function call="+choicesArray);
		 choicesArray = getChoicesFromAPI(value);
		 System.out.println("Output from getChoicesFromAPI function call="+choicesArray);
		// Extract and print the text values of spices
	   
		 

	    request.setAttribute("recipeOutput", choicesArray);

				// Forward the request to the JSP page
		//RequestDispatcher dispatcher = request.getRequestDispatcher("recipeDetails.jsp");
		//dispatcher.forward(request, response);

	}

	

	public String getChoicesFromAPI(String value) {
		 
		String textValue = "";
		try {

			URL url = new URL("https://api.openai.com/v1/engines/text-davinci-003/completions");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			String data = "{\"prompt\": \"Retrieve recipe of given ingredients " + value + ":\", \"max_tokens\": 500}";
			System.out.println("MY PROMT is ===" + data);
			// String data = "{\"prompt\": \"Retrieve recipe of given ingrediants
			// eggs,butter,sugar,whole wheat flour:\", \"max_tokens\": 500}";
			
			conn.getOutputStream().write(data.getBytes());

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				response.append(line);
			}

			String responseData = response.toString();
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode is===" + responseCode);
			// Parse the JSON response to extract the 'choices' object
			// JSONObject responseObject = new JSONObject(responseData);
			// choices = responseObject.getJSONArray("choices");
			// System.out.println("CHOICES from JSON is==="+choices);

			String jsonResponse = responseData;

			// Create a JsonReader from the JSON response string
			try (JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse))) {
				JsonObject jsonObject = jsonReader.readObject();

				// Get the "choices" array
				JsonArray choicesArray = jsonObject.getJsonArray("choices");

				// Get the first element of the array
				JsonObject choiceObject = choicesArray.getJsonObject(0);

				// Extract the "text" attribute value
				 textValue = choiceObject.getString("text");

				// Output the text value
			//	System.out.println("textValue==="+textValue);

			}
			reader.close();
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return textValue;

	}

	/*
	 * public static void extractAndPrintSpices(JSONArray choicesArray) { try { //
	 * Extract and print the text values of spices JSONObject firstChoice =
	 * choicesArray.getJSONObject(0); // Assuming only one choice String text =
	 * firstChoice.getString("text");
	 * 
	 * // Split the text into individual spices String[] spiceArray =
	 * text.split("\\n\\d+\\.\\s"); for (int i = 1; i < spiceArray.length; i++) {
	 * //System.out.println(spiceArray[i].trim()); String recipeDes =
	 * spiceArray[i].trim(); System.out.println(recipeDes); } } catch (JSONException
	 * e) { e.printStackTrace(); } }
	 */
	// List return
	/*
	 * public static String[] extractAndPrintSpices(JSONArray choicesArray) { try {
	 * // Extract and print the text values of spices JSONObject firstChoice =
	 * choicesArray.getJSONObject(0); // Assuming only one choice String text =
	 * firstChoice.getString("text"); // Split the text into individual spices
	 * String[] spiceArray = text.split("\\n\\d+\\.\\s");
	 * 
	 * // Trim and collect all spices in an ArrayList List<String> spicesList = new
	 * ArrayList<>(); for (int i = 1; i < spiceArray.length; i++) {
	 * spicesList.add(spiceArray[i].trim()); }
	 * 
	 * // Convert the ArrayList to an array String[] allSpices =
	 * spicesList.toArray(new String[0]);
	 * 
	 * return allSpices; } catch (JSONException e) { e.printStackTrace(); } return
	 * new String[0]; }
	 */
	// return string
	public static String extractAndPrintSpices(JSONArray choicesArray) {
		try {
			// Extract and print the text values of spices
			JSONObject firstChoice = choicesArray.getJSONObject(0); // Assuming only one choice
			String text = firstChoice.getString("text");

			// Split the text into individual spices
			String[] spiceArray = text.split("\\n\\d+\\.\\s");
			for (int i = 1; i < spiceArray.length; i++) {
				System.out.println(spiceArray[i].trim());
				String recipeDes = spiceArray[i].trim();

			}
			return recipeDes;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return recipeDes;
	}
}
