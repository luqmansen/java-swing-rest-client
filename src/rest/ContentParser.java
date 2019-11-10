/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author binatangkesusahan
 */
public class ContentParser {
    
    private APIRequest r = new APIRequest();
    private JSONArray arr = r.getRequest();
    private final String imageBaseUrl = "http://image.tmdb.org/t/p/w154";
    private final String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.85 Safari/537.36";

    public ContentParser() {


    }

    public BufferedImage getImage(String imageURLString) throws IOException {
        // throws MalformedURLException, IOException
        URL imageURL = new URL(imageURLString);
        HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
        BufferedImage image;

        connection.setRequestProperty("User-Agent", this.userAgent);
        image = ImageIO.read(connection.getInputStream());
        return image;
    }

    public BufferedImage getFactImage() throws IOException {
        String imgURL = imageBaseUrl + arr.getJSONObject(1).get("poster_path").toString();
        return getImage(imgURL);
    }

    public String getFactTitle() {
        String title = arr.getJSONObject(1).get("original_name").toString();
        return title;
    }

    public String getFactContent() {
        String overview = arr.getJSONObject(1).get("overview").toString();
        return overview;
    }

}
