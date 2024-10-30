package services;

import assertions.AssertableResponse;
import com.beust.ah.A;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import models.user.AuthData;
import models.user.UserData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserService {
    public AssertableResponse register(UserData user) {
        return new AssertableResponse(given()
                .contentType(ContentType.JSON)
                .body(user)
                .post("/signup")
                .then());
    }

    public AssertableResponse auth(UserData user) {
        AuthData authData = new AuthData(user.getPass(), user.getLogin());
        return new AssertableResponse(given()
                .contentType(ContentType.JSON)
                .body(authData)
                .post("/login")
                .then());
    }

    public AssertableResponse auth() {
        Map<String, String> admin = new HashMap<String, String>() {{
            put("username", "admin");
            put("password", "admin");
        }};
        return new AssertableResponse(given()
                .contentType(ContentType.JSON)
                .body(admin)
                .post("/login")
                .then());
    }

    public AssertableResponse getUser(String token) {
        return new AssertableResponse(given()
                .auth().oauth2(token)
                .get("/user")
                .then());
    }

    public AssertableResponse getUser() {
        return new AssertableResponse(given()
                .get("/user")
                .then());
    }

    public AssertableResponse changePass(String token, String newPass) {
        Map<String, String> password = new HashMap<String, String>() {{
            put("password", newPass);
        }};

        return new AssertableResponse((given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(password)
                .put("/user")
                .then()));
    }

    public AssertableResponse deleteUser(String token) {
        return new AssertableResponse(given()
                .auth().oauth2(token)
                .delete("/user")
                .then());
    }

    public AssertableResponse getUsers() {
        return new AssertableResponse(given()
                .get("/users")
                .then());
    }

}
