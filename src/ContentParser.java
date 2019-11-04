import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import REST.Request;
import com.github.cliftonlabs.json_simple.JsonArray;
import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;

class ContentParser {

    private Request r = new Request();
    private JSONArray arr = r.getRequest();
    private final String imageBaseUrl = "http://image.tmdb.org/t/p/w500";
    private final String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.85 Safari/537.36";

    ContentParser() {


    }

    private BufferedImage getImage(String imageURLString) throws IOException {
        // throws MalformedURLException, IOException
        URL imageURL = new URL(imageURLString);
        HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
        BufferedImage image;

        connection.setRequestProperty("User-Agent", this.userAgent);
        image = ImageIO.read(connection.getInputStream());
        return image;
    }

    BufferedImage getFactImage() throws IOException {
        String imgURL = imageBaseUrl + arr.getJSONObject(1).get("backdrop_path").toString();
        return getImage(imgURL);
    }

    String getFactTitle() {
        String title = arr.getJSONObject(1).get("original_name").toString();
        return title;
    }

    String getFactContent() {
        String overview = arr.getJSONObject(1).get("overview").toString();
        return overview;
    }

}