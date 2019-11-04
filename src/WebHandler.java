import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

class WebHandler {

    static void openURI(String URLParam) throws IOException, URISyntaxException {
        // throws IOException, MalformedURLException, URISyntaxException
        URL url = new URL(URLParam);
        URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
        Desktop.getDesktop().browse(uri);
    }

}
