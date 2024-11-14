package tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import models.AuthData;
import models.ErrorInfo;
import models.Info;
import models.UserData;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class UserTests {

    private static Random random;

    @BeforeSuite
    public static void setUp() {
        RestAssured.baseURI = "http://85.192.34.140:8080/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        random = new Random();
    }

    private UserData createUser() {
        return UserData.builder()
                .login("apiTestUser" + System.currentTimeMillis())
                .pass("qwerty1234")
                .build();
    }

    @Test
    public void positiveRegisterUserTest() {
        UserData user = createUser();
        Info info = given().contentType(ContentType.JSON)
                .body(user)
                .post("/signup")
                .then().statusCode(201)
                .extract().jsonPath().getObject("info", Info.class);

        Assert.assertEquals(info.getStatus(), "success");
        Assert.assertEquals(info.getMessage(), "User created");
    }

    @Test
    public void negativeRegisterUserTest() {
        UserData user = createUser();
        ErrorInfo error = given().contentType(ContentType.JSON)
                .body(user.getLogin())
                .post("/signup")
                .then().statusCode(400)
                .extract().jsonPath().getObject("", ErrorInfo.class);

        Assert.assertEquals(error.getError(), "Bad Request");
        Assert.assertEquals(error.getStatus(), 400);
    }

    @Test
    public void alreadyExistRegisterUserTest() {
        UserData user = createUser();
        given().contentType(ContentType.JSON)
                .body(user)
                .post("/signup")
                .then();

        Info info = given().contentType(ContentType.JSON)
                .body(user)
                .post("/signup")
                .then().statusCode(400)
                .extract().jsonPath().getObject("info", Info.class);

        Assert.assertEquals(info.getStatus(), "fail");
        Assert.assertEquals(info.getMessage(), "Login already exist");
    }

    @Test
    public void invalidDataRegisterUSerTest() {
        Map<String, String> user = new HashMap<String, String>() {{
            put("unknownField", ("apiTestUser" + System.currentTimeMillis()));
            put("pass", "qwerty1234");
        }};

        Info info = given().contentType(ContentType.JSON)
                .body(user)
                .post("/signup")
                .then().statusCode(400)
                .extract().jsonPath().getObject("info", Info.class);

        Assert.assertEquals(info.getStatus(), "fail");
        Assert.assertEquals(info.getMessage(), "Missing login or password");
    }

    @Test
    public void positiveAuthUserTest() {
        UserData user = createUser();
        AuthData authData = AuthData.builder()
                .username(user.getLogin())
                .password(user.getPass())
                .build();

        given().contentType(ContentType.JSON)
                .body(user)
                .post("/signup")
                .then().statusCode(201);

        String token = given().contentType(ContentType.JSON)
                .body(authData)
                .post("/login")
                .then().statusCode(200)
                .extract().jsonPath().getString("token");

        Assert.assertNotNull(token);
    }

    @Test
    public void negativeAuthUserTest() {
        AuthData authData = AuthData.builder()
                .username("unknownUser")
                .password("unknownPass")
                .build();

        ErrorInfo error = given().contentType(ContentType.JSON)
                .body(authData)
                .post("/login")
                .then().statusCode(401)
                .extract().jsonPath().getObject("", ErrorInfo.class);

        Assert.assertEquals(error.getStatus(), 401);
        Assert.assertEquals(error.getError(), "Unauthorized");
    }

    @Test
    public void positiveGetUserTest() {
        UserData user = createUser();
        AuthData authData = AuthData.builder()
                .username(user.getLogin())
                .password(user.getPass())
                .build();

        given().contentType(ContentType.JSON)
                .body(user)
                .post("/signup")
                .then().statusCode(201);

        String token = given().contentType(ContentType.JSON)
                .body(authData)
                .post("/login")
                .then().statusCode(200)
                .extract().jsonPath().getString("token");

        Map<String, String> info = given().auth().oauth2(token)
                .get("/user")
                .then().statusCode(200)
                .extract().jsonPath().getMap("");

        Assert.assertTrue(info.get("login").startsWith("apiTestUser"));
    }

    @Test
    public void negativeGetUserTest() {
        String token = "invalidJWT";
        given().auth().oauth2(token)
                .get("/user")
                .then().statusCode(401);
    }

    @Test
    public void positiveChangeUserPassTest() {
        UserData user = createUser();
        AuthData authData = AuthData.builder()
                .username(user.getLogin())
                .password(user.getPass())
                .build();

        given().contentType(ContentType.JSON)
                .body(user)
                .post("/signup")
                .then().statusCode(201);

        String token = given().contentType(ContentType.JSON)
                .body(authData)
                .post("/login")
                .then().statusCode(200)
                .extract().jsonPath().getString("token");

        AuthData newPass = AuthData.builder()
                .password("4321ytrewq")
                .build();

        Info info = given().auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(newPass)
                .put("/user")
                .then().statusCode(200)
                .extract().jsonPath().getObject("info", Info.class);

        Assert.assertEquals(info.getMessage(), "User password successfully changed");
        Assert.assertEquals(info.getStatus(), "success");
    }

    @Test
    public void negativeChangeUserPassTest() {
        UserData user = createUser();
        AuthData authData = AuthData.builder()
                .username(user.getLogin())
                .password(user.getPass())
                .build();

        given().contentType(ContentType.JSON)
                .body(user)
                .post("/signup")
                .then().statusCode(201);

        String token = given().contentType(ContentType.JSON)
                .body(authData)
                .post("/login")
                .then().statusCode(200)
                .extract().jsonPath().getString("token");

        AuthData newPass = AuthData.builder()
                .password("4321ytrewq")
                .build();

        given().auth().oauth2(token.replaceAll("\\d+", "1"))
                .contentType(ContentType.JSON)
                .body(newPass)
                .put("/user")
                .then().statusCode(401);

    }

    @Test
    public void positiveDeleteUserTest() {
        UserData user = createUser();
        AuthData authData = AuthData.builder()
                .username(user.getLogin())
                .password(user.getPass())
                .build();

        given().contentType(ContentType.JSON)
                .body(user)
                .post("/signup")
                .then().statusCode(201);

        String token = given().contentType(ContentType.JSON)
                .body(authData)
                .post("/login")
                .then().statusCode(200)
                .extract().jsonPath().getString("token");

        Info info = given().auth().oauth2(token)
                .delete("/user")
                .then().statusCode(200)
                .extract().jsonPath().getObject("info", Info.class);

        Assert.assertEquals(info.getMessage(), "User successfully deleted");
        Assert.assertEquals(info.getStatus(), "success");
    }

    @Test
    public void deleteDefaultUserTest() {
        Map<String, String> adminUser = new HashMap<String, String>() {{
            put("username", "admin");
            put("password", "admin");
        }};

        AuthData authData = AuthData.builder()
                .username(adminUser.get("username"))
                .password(adminUser.get("password"))
                .build();

        String token = given().contentType(ContentType.JSON)
                .body(authData)
                .post("/login")
                .then().statusCode(200)
                .extract().jsonPath().getString("token");

        Info info = given().auth().oauth2(token)
                .delete("/user")
                .then().statusCode(400)
                .extract().jsonPath().getObject("info", Info.class);

        Assert.assertEquals(info.getMessage(), "Cant delete base users");
        Assert.assertEquals(info.getStatus(), "fail");

    }

    @Test
    public void negativeDeleteUserTest() {
        UserData user = createUser();
        AuthData authData = AuthData.builder()
                .username(user.getLogin())
                .password(user.getPass())
                .build();

        given().contentType(ContentType.JSON)
                .body(user)
                .post("/signup")
                .then().statusCode(201);

        String token = given().contentType(ContentType.JSON)
                .body(authData)
                .post("/login")
                .then().statusCode(200)
                .extract().jsonPath().getString("token");

        given().auth().oauth2(token.replaceAll("\\d+", "1"))
                .delete("/user")
                .then().statusCode(401);
    }

    @Test
    public void getAllUsersTest() {
        List<String> users = given().get("/users")
                .then().statusCode(200)
                .extract().jsonPath().getList("", String.class);

        Assert.assertTrue(users.size() >= 3);
    }

}
