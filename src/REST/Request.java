package REST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class Request {

    private ArrayList bgImages;
    private BufferedReader reader;
    private JSONObject JsonObject;
    private JSONArray results;

    public JSONArray getRequest() {
        try {
            URL url = new URL("http://api.themoviedb.org/3/discover/tv?sort_by=popularity.desc&api_key=9b77cc6f89f83a52b2bc2af8dbe50f58");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            reader = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuilder jsonData = new StringBuilder();
            String data = "";
            while ((data = reader.readLine()) != null){
                jsonData.append(data);
            }
            JsonObject = new JSONObject(jsonData.toString());
            results = (JSONArray) JsonObject.get("results");
//            for (int i = 0; i < results.length(); i++){
//                JSONObject object = (JSONObject) results.get(i);
//                System.out.println(object);
//            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        return results;
    }

}
