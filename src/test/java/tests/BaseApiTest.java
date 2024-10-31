package tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import models.user.UserData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import services.FileService;
import services.UserService;

import static addons.RandomTestData.getRandomUser;

public class BaseApiTest {
    protected static UserService userService;
    protected static FileService fileService;
    protected UserData randomUser;

    @BeforeSuite
    public static void setUp() {
        RestAssured.baseURI = "http://85.192.34.140:8080/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        userService = new UserService();
        fileService = new FileService();
    }
    @BeforeMethod
    public void initTestUser() {
        randomUser = getRandomUser();
    }
}
