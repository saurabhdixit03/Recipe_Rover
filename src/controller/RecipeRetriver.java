package controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
//OPEN AI API 
public class RecipeRetriver {

    public static void main(String[] args) {
        // Replace with your actual API key
        String apiKey = "#your_apiKey";


        List<String> ingredients = new ArrayList<>();
        ingredients.add("potato");
        //ingredients.add("rice");
        // Add more ingredients as needed
        
        String prompt = generatePrompt(ingredients);

        try {
            String recipe = getRecipe(apiKey, prompt);
            String recipeText = extractRecipeText(recipe);
            System.out.println("Generated Recipe:\n" + recipeText);
        } catch (IOException e) {
            System.out.println("Error fetching recipe: " + e.getMessage());
        }
    }

    private static String generatePrompt(List<String> ingredients) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("Given the ingredients: ");
        for (String ingredient : ingredients) {
            promptBuilder.append(ingredient).append(", ");
        }
        promptBuilder.delete(promptBuilder.length() - 2, promptBuilder.length()); // Remove the extra comma
        promptBuilder.append(", please give step by step indian recipe using these ingredients.");
        return promptBuilder.toString();
    }

    private static String getRecipe(String apiKey, String prompt) throws IOException {
        String openAIEndpoint = "https://api.openai.com/v1/engines/davinci/completions";

        URL url = new URL(openAIEndpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        String postData = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 200}";
        byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);

        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.write(postDataBytes);
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        return response.toString();
    }

    private static String extractRecipeText(String jsonResponse) {
        String textKey = "\"choices\": \"";
        int startIndex = jsonResponse.indexOf(textKey);
        if (startIndex != -1) {
            startIndex += textKey.length();
            int endIndex = jsonResponse.indexOf("\"", startIndex);
            if (endIndex != -1) {
                return jsonResponse.substring(startIndex, endIndex);
            }
        }
        return "Recipe text not found";
    }
}
