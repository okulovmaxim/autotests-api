package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static assertions.Conditions.*;

public class GameTest extends BaseApiTest {

    @Test
    public void positiveAddGameTest() {
        userService.register(randomUser);
        String token = userService.auth(randomUser).asJwt();

        gameService.addGame(token, randomGame)
                .should(hasStatusCode(201))
                .should(hasMessage("Game created"))
                .should(hasStatus("success"));
    }

    @Test
    public void positiveGetGameTest() {
        userService.register(randomUser);
        String token = userService.auth(randomUser).asJwt();
        gameService.addGame(token, randomGame);
        Response response = gameService.getGame(token)
                .should(hasStatusCode(200)).asResponse();

        Assert.assertNotNull(response);
    }

}
