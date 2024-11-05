package tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import models.games.GameData;
import models.user.UserData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import services.FileService;
import services.GameService;
import services.UserService;

import static addons.RandomTestData.getRandomGame;
import static addons.RandomTestData.getRandomUser;

public class BaseApiTest {
    protected static UserService userService;
    protected static FileService fileService;
    protected static GameService gameService;
    protected UserData randomUser;
    protected GameData randomGame;

    @BeforeSuite
    public static void setUp() {
        RestAssured.baseURI = "http://85.192.34.140:8080/api";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        userService = new UserService();
        fileService = new FileService();
        gameService = new GameService();
    }
    @BeforeMethod
    public void initTestData() {
        randomUser = getRandomUser();
        randomGame = getRandomGame();
    }
}
