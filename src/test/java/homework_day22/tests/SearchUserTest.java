package homework_day22.tests;

import classwork_day21.Search;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import homework_day22.objects.Response;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileNotFoundException;
import java.io.FileReader;

@RunWith(JUnit4.class)
public class SearchUserTest {

    RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("http://178.124.206.46:8001/app/ws/")
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    Gson gson = new Gson();

    Response expectedResponseForSearchUserBerta;

    {
        try {
            expectedResponseForSearchUserBerta = gson.fromJson(new JsonReader(new FileReader("src/test/resources/homework_day22/partialNameResplonse.json")), Response.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No test file was found");
        }
    }

    @Test
    public void shouldReturnUserByPartialName() throws FileNotFoundException {

        Search searchBody = gson.fromJson(new JsonReader(new FileReader("src/test/resources/homework_day22/partilalNameSearchBody.json")), Search.class);

        Response actualResponse = getResponse(searchBody);

        Assert.assertEquals(expectedResponseForSearchUserBerta, actualResponse);

    }


    @Test
    public void shouldReturnUserByFullName() throws FileNotFoundException {

        Search searchBody = gson.fromJson(new JsonReader(new FileReader("src/test/resources/homework_day22/fullNameSearchBody.json")), Search.class);

        Response actualResponse = getResponse(searchBody);

        Assert.assertEquals(expectedResponseForSearchUserBerta, actualResponse);

    }

    @Test
    public void shouldReturnUserByPartialRealname() throws FileNotFoundException {

        Search searchBody = gson.fromJson(new JsonReader(new FileReader("src/test/resources/homework_day22/fullNameSearchBody.json")), Search.class);

        Response actualResponse = getResponse(searchBody);

        Assert.assertEquals(expectedResponseForSearchUserBerta, actualResponse);
    }

    @Test
    public void shouldReturnAllUsers() {

        Response response = getResponse(new Search("", false));

        Assert.assertTrue(response.getData().size() == 6);

    }

    private Response getResponse(Search searchBody) {
        return RestAssured
                .given()
                .spec(spec)
                .body(searchBody)
                .when()
                .post()
                .then()
                .extract().body().as(Response.class);
    }
}
