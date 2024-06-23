package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

/**
 * Servlet implementation class SearchServlet
 */
//@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Replace 'YOUR_API_KEY' with your OpenAI API key
	private static final String API_KEY = "#your_apiKey";

	 

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String choicesArray = "AAAA";
		String trimOutput = null ;
		System.out.println("strarting serchservlet ....");
		 
		// String userInput = request.getParameter("allIngredentsArray");

		String value = (String) request.getAttribute("allIngredentsArray");
		 
		//System.out.println("userInput in searchservlet=" + value);
		// System.out.println("Before getChoicesFromAPI function call="+choicesArray);
		try {
			choicesArray = getChoicesFromAPI(value);
			//send choiceArray for trimming
			trimOutput = extractTextFromChoices(choicesArray);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("getChoicesFromAPI calling cought exception");
			e.printStackTrace();
		}
		System.out.println("extractTextFromChoices trimOutput::"+trimOutput);
		//System.out.println("Output from getChoicesFromAPI function call=" + choicesArray);
		// Extract and print the text values of spices

		request.setAttribute("recipeOutput", trimOutput);

		// Forward the request to the JSP page
		 RequestDispatcher dispatcher = request.getRequestDispatcher("recipeDetails.jsp");
		dispatcher.forward(request, response);

	}

	public String getChoicesFromAPI(String value) throws InterruptedException {
		
	    int maxRetries = 3; // Maximum number of retries
        int retryDelayMillis = 1000; // Initial delay in milliseconds
        int retryCount = 0;
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
			 
			
			//
			  try (OutputStream os = conn.getOutputStream()) {
		            byte[] input = data.getBytes("utf-8");
		            os.write(input, 0, input.length);
		        }
			  //
			int responseCode = conn.getResponseCode(); 
			System.out.println("responseCode is===" + responseCode);
			
            if (responseCode == HttpURLConnection.HTTP_OK) {
 
                // Read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                
                String responseData = response.toString();
                //System.out.println("responseData ==="+responseData);
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
    				 // System.out.println("Textvalue ==="+textValue);
                in.close();

                return response.toString(); // Return response if successful
            }
            }
            else if (responseCode == 429) {
                // Retry after delay using exponential backoff
                System.out.println("Received 429 - Too Many Requests. Retrying after delay...");
                retryCount++;
                Thread.sleep(retryDelayMillis);
                retryDelayMillis *= 2; // Exponential backoff: increase delay for next retry
            } 
            else {
            	 throw new IOException("HTTP error code: " + responseCode + ", " + conn.getResponseMessage());
            }
            
			//conn.getOutputStream().write(data.getBytes());

            //	BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            //	StringBuilder response = new StringBuilder();
            //	String line;

            //	while ((line = reader.readLine()) != null) {
            //		response.append(line);
            //	}

			

				// Output the text value
				//	System.out.println("textValue==="+textValue);

			
            
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return textValue;

	}
	
	public static String extractTextFromChoices(String jsonString) {
        try {
            // Parse the provided JSON string
            JSONObject jsonObject = new JSONObject(jsonString);

            // Get the "choices" array from the JSON object
            JSONArray choicesArray = jsonObject.getJSONArray("choices");

            if (choicesArray.length() > 0) {
                // Get the first object from the "choices" array
                JSONObject firstChoice = choicesArray.getJSONObject(0);

                // Extract the "text" attribute from the first choice
                String text = firstChoice.getString("text");

                return text; // Return the extracted text
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No choices found"; // Return an empty string if extraction fails or choicesArray is empty
    }
	 
	 
	
}
