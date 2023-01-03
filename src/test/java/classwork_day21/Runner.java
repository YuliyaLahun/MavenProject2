package classwork_day21;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class Runner {

    public static void main(String[] args) throws IOException, URISyntaxException {
        MyParser parser = new MyParser();

        MyHttpClient myHttpClient = new MyHttpClient();
        //myHttpClient.HttpGet();
        myHttpClient.HttpPost();

        //parser.parseGSON();
        //parser.toJson();
    }
}
