package services;

import assertions.AssertableResponse;
import io.restassured.http.ContentType;
import models.games.DlcsItem;
import models.games.GameData;
import static io.restassured.RestAssured.given;

public class GameService {

    public AssertableResponse addGame(String token, GameData game) {
        return new AssertableResponse(given().auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(game)
                .post("user/games")
                .then());
    }

    public AssertableResponse getGame(String token) {
        return new AssertableResponse(given().auth().oauth2(token)
                .get("user/games")
                .then());
    }

    public AssertableResponse updateGameDlcInfo(String token, DlcsItem dlcsItem) {
        return new AssertableResponse(given().auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body()
                .put("user/games/{gamesId}")
                .then());
    }
}
