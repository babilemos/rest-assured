import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

public class CountriesAPI_LanguageTest {

    String queryLanguage = "{\"query\":\"query {\\n  languages(filter:{code:{eq:\\\"en\\\"}}){\\n    code\\n    name\\n  }\\n}\",\"variables\":{}}";
    String queryAllLanguage = "{\"query\":\"query {\\n  languages(filter:{}){\\n    code\\n    name\\n  }\\n}\",\"variables\":{}}";

    @Test
    public void shouldReturnStatusCode200() {
        Response response = Queries.getResponse(queryLanguage);
        response.then().statusCode(Constants.STATUS_OK);
        response.then().body("data.languages[0].name", equalTo("French"));
    }

    @Test
    public void shouldReturnAllLanguages() {
        Response response = Queries.getResponse(queryAllLanguage);
        response.then().statusCode(Constants.STATUS_OK);
        response.then().body("data.languages.size", equalTo(114));
    }

}
