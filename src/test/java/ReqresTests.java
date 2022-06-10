import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ReqresTests {

    String baseURL = "https://reqres.in";

    @Test
    void checkUserNameAndLastName() {

        int userId = 2;

        given()
                .log().uri()
                .when()
                .get(baseURL + "/api/users/" + userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    void checkUserNotFound() {
        int userId = 23;

        given()
                .log().uri()
                .when()
                .get(baseURL + "/api/users/" + userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void userShouldBeCreated() {

        String userData = "{\"name\": \"Beth\", \"job\": \"Manager\"}";

        given()
                .log().uri()
                .log().body()
                .body(userData)
                .contentType(ContentType.JSON)
                .when()
                .post(baseURL + "/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Beth"))
                .body("job", is("Manager"));
    }

    @Test
    void userJobShouldBeUpdated() {

        int userId = 162;
        String userJob = "{\"job\": \"QA\"}";

        given()
                .log().uri()
                .log().body()
                .body(userJob)
                .contentType(ContentType.JSON)
                .when()
                .patch(baseURL + "/api/users/" + userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("job", is("QA"));
    }

    @Test
    void userShouldBeDeleted() {

        int userId = 2;

        given()
                .log().uri()
                .log().body()
                .contentType(ContentType.JSON)
                .when()
                .delete(baseURL + "/api/users/" + userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
