import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        try {
            JSONArray jsonResponse = getJsonArray();

            for (int i = 0; i < jsonResponse.length(); i++) {
                JSONObject user = jsonResponse.getJSONObject(i);
                System.out.println("User " + (i + 1) + ": ");
                System.out.println("ID: " + user.getInt("id"));
                System.out.println("Name: " + user.getString("name"));
                System.out.println("Email: " + user.getString("email"));
                System.out.println("Phone: " + user.getString("phone"));
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONArray getJsonArray() throws IOException {
        String url = "https://fake-json-api.mock.beeceptor.com/users";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new JSONArray(response.toString());
    }
}
