package com.github.kadehar.steps;

import com.github.kadehar.model.User;
import com.github.kadehar.model.UserData;
import com.github.kadehar.spec.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Steps {
    public int registerNewUser() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");
        // @formatter:off
        return  given()
                    .spec(Request.spec())
                    .body(user)
                    .log().uri()
                    .log().body()
                .when()
                    .post("/register")
                .then()
                    .log().body()
                    .extract()
                    .path("id");
        // @formatter:on
    }

    public UserData getUserById(final int id) {
        // @formatter:off
        return  given()
                    .spec(Request.spec())
                    .log().uri()
                .when()
                    .get("/users/{id}", id)
                .then()
                    .log().body()
                    .extract()
                    .as(UserData.class);
        // @formatter:on
    }

    public void checkUserWithGroovyMagic() {
        // @formatter:off
        given()
            .spec(Request.spec())
            .log().uri()
        .when()
            .get("/users")
        .then()
            .log().body()
            .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                    hasItem("eve.holt@reqres.in"));
        // @formatter:on
    }

    public void deleteUser(final int id) {
        // @formatter:off
        given()
            .spec(Request.spec())
            .log().uri()
        .when()
            .delete("/users/{id}", id)
         .then()
            .statusCode(204);
        // @formatter:on
    }

    public void successfulLogin() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");
        // @formatter:off
        given()
            .spec(Request.spec())
            .body(user)
            .log().uri()
            .log().body()
        .when()
            .post("/login")
        .then()
            .log().body()
            .body("token", not(emptyOrNullString()));
        // @formatter:on
    }
}
