package services;

import assertions.AssertableResponse;
import io.restassured.http.ContentType;
import models.games.GameData;
import tests.GameTest;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class GameService {

    public AssertableResponse addGame(String token, GameData game) {
        return new AssertableResponse(given().auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(game)
                .post("user/games")
                .then());
    }
}
