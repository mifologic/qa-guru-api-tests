import io.qameta.allure.AllureId;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import models.UserData;
import models.UsersCreation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresTests {

    @Test
    @AllureId("11686")
    @DisplayName("Проверка имени и фамилии пользователя")
    @Owner("allure8")
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
    @AllureId("11687")
    @DisplayName("Пользователь не найден")
    @Owner("allure8")
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
    @AllureId("11688")
    @DisplayName("Создание пользователя")
    @Owner("allure8")
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
    @AllureId("11689")
    @DisplayName("Редактирование пользователя")
    @Owner("allure8")
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
    @AllureId("11690")
    @DisplayName("Удаление пользователя")
    @Owner("allure8")
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
