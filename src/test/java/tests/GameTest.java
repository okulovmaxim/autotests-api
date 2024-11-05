package tests;

import org.testng.annotations.Test;
import static assertions.Conditions.*;

public class GameTest extends BaseApiTest {

    @Test
    public void addGame() {
        userService.register(randomUser);
        String token = userService.auth(randomUser).asJwt();

        gameService.addGame(token, randomGame)
                .should(hasStatusCode(201))
                .should(hasMessage("Game created"))
                .should(hasStatus("success"));
    }
}
