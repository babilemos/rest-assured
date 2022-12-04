import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

public class CountriesAPI_CountryTest {

    String queryCountry = "{\"query\":\"query country {\\n\\tcountry(code:\\\"FR\\\"){\\n    name\\n    emoji\\n    continent{\\n      code\\n      name\\n      countries{\\n        name\\n        code\\n        emoji\\n      }\\n    }\\n  }\\n}\",\"variables\":{}}";

    @Test
    public void shouldReturnStatusCode200() {
        Response response = Queries.getResponse(queryCountry);
        response.then().statusCode(Constants.STATUS_OK);
        response.then().body("data.country.name", equalTo("France"));
    }

    @Test
    public void shouldReturnErrorWithoutHeader() {
        Response response = Queries.getResponseWhitoutHeader(queryCountry);
        response.then().statusCode(Constants.STATUS_BAD_REQUEST);
        response.then().body("html.body", equalTo("POST body missing, invalid Content-Type, or JSON object has no keys."));
    }

}
