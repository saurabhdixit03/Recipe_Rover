package controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpiceDropdown {

    // Replace 'YOUR_API_KEY' with your OpenAI API key
    private static final String API_KEY = "#your_apiKey";

    public static void main(String[] args) {
        JSONArray choicesArray = getChoicesFromAPI();

        // Extract and print the text values of spices
        if (choicesArray.length() > 0) {
            extractAndPrintSpices(choicesArray);
        } else {
            System.out.println("No choices found.");
        }
    }

    public static JSONArray getChoicesFromAPI() {
        JSONArray choices = new JSONArray();

        try {
            URL url = new URL("https://api.openai.com/v1/engines/text-davinci-003/completions");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String data = "{\"prompt\": \"Retrieve list of spices and write indian names of spices into bracket:\", \"max_tokens\": 500}";
            conn.getOutputStream().write(data.getBytes());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            String responseData = response.toString();

            // Parse the JSON response to extract the 'choices' object
            JSONObject responseObject = new JSONObject(responseData);
            choices = responseObject.getJSONArray("choices");

            reader.close();
            conn.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return choices;
    }

    public static void extractAndPrintSpices(JSONArray choicesArray) {
        try {
            // Extract and print the text values of spices
            JSONObject firstChoice = choicesArray.getJSONObject(0); // Assuming only one choice
            String text = firstChoice.getString("text");

            // Split the text into individual spices
            String[] spiceArray = text.split("\\n\\d+\\.\\s");
            for (int i = 1; i < spiceArray.length; i++) {
                System.out.println(spiceArray[i].trim());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
