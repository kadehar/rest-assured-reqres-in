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

    /*
    {
        "page": 2,
        "per_page": 6,
        "total": 12,
        "total_pages": 2,
        "data": [
            {
                "id": 7,
                "email": "michael.lawson@reqres.in",
                "first_name": "Michael",
                "last_name": "Lawson",
                "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg"
            },
            {
                "id": 8,
                "email": "lindsay.ferguson@reqres.in",
                "first_name": "Lindsay",
                "last_name": "Ferguson",
                "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/araa3185/128.jpg"
            }
         ]
     }   
     
     -> without .flatten()
     [["michael.lawson@reqres.in"], ["lindsay.ferguson@reqres.in"]]
     
     -> with .flatten()
     ["michael.lawson@reqres.in", "lindsay.ferguson@reqres.in"]
     */
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
