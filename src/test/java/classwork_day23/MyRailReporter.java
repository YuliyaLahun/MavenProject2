package classwork_day23;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Base64;

public class MyRailReporter {

    private static final Logger LOGGER = LogManager.getLogger(MyRailReporter.class);

    static String url = "http://178.124.206.46:8000/index.php?/api/v2/get_cases/7&suite_id=1290";


    public static void main(String[] args) throws URISyntaxException, IOException {
        //new MyRailReporter().sendGetRequest(url);
        new MyRailReporter().report("147411", 1, "comment");
    }

    private static void sendPostRequest(String url, String body) {

        try {
            HttpClient client = HttpClientBuilder.create().build();
            URIBuilder builder = null;
            builder = new URIBuilder(url);
            HttpPost request = new HttpPost(builder.build());
            request.setEntity(new StringEntity(body));

            String authText = "";
            String encoded = "Basic " + Base64.getEncoder().encodeToString(authText.getBytes());
            request.addHeader("Authorization", encoded);

            request.addHeader("Content-Type", "application/json");
            HttpResponse httpResponse = client.execute(request);
            System.out.println(EntityUtils.toString(httpResponse.getEntity()));

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void report(String caseId, int status, String comment) {

        sendPostRequest(String.format("http://178.124.206.46:8000/index.php?/api/v2/add_result_for_case/2092/%s", caseId),
                String.format("{\"status_id\":\"%d\", \"comment\":\"%s\"}", status, comment));

    }

    public void sendGetRequest(String url) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder(url);
        HttpGet request = new HttpGet(builder.build());

        String authText = "fakemail@tech.co:Abc123";
        String encoded = "Basic " + Base64.getEncoder().encodeToString(authText.getBytes());
        request.addHeader("Authorization", encoded);

        request.addHeader("Content-Type", "application/json");
        HttpResponse httpResponse = client.execute(request);
        System.out.println(EntityUtils.toString(httpResponse.getEntity()));

    }
}
