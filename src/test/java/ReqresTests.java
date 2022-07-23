import io.restassured.http.ContentType;
import models.UserData;
import models.UsersCreation;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresTests {

    @Test
    void checkUserNameAndLastName() {

        int userId = 2;

        UserData data = given()
                .spec(Specs.request)
                .when()
                .get("/users/" + userId)
                .then()
                .spec(Specs.response)
                .extract().as(UserData.class);
        assertEquals("Janet", data.getUser().getFirstName());
        assertEquals("Weaver", data.getUser().getLastName());
    }

    @Test
    void checkUserNotFound() {
        int userId = 23;

        given()
                .spec(Specs.request)
                .when()
                .get("/users/" + userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void userShouldBeCreated() {

        UsersCreation user = UsersCreation.builder().name("Beth").job("Manager").build();

        UsersCreation createdUser = given()
                .spec(Specs.request)
                .body(user)
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(UsersCreation.class);
        assertEquals("Beth", createdUser.getName());
        assertEquals("Manager", createdUser.getJob());
    }

    @Test
    void userJobShouldBeUpdated() {

        int userId = 162;
        UsersCreation user = UsersCreation.builder().name("Berth").id("162").job("QA").build();

        UsersCreation createdUser = given()
                .spec(Specs.request)
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .patch("/users/" + userId)
                .then()
                .spec(Specs.response)
                .extract().as(UsersCreation.class);
        assertEquals("QA", createdUser.getJob());
    }

    @Test
    void userShouldBeDeleted() {

        int userId = 2;

        given()
                .spec(Specs.request)
                .when()
                .delete("/users/" + userId)
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
