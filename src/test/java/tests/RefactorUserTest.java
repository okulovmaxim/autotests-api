package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static assertions.Conditions.*;

public class RefactorUserTest extends BaseApiTest{

    @Test
    public void positiveRegisterUserTest() {
        userService.register(randomUser)
                .should(hasStatusCode(201))
                .should(hasStatus("success"))
                .should(hasMessage("User created"));
    }

    @Test
    public void negativeRegisterUserTest() {
        randomUser.setPass(null);
        userService.register(randomUser)
                .should(hasStatusCode(400))
                .should(hasStatus("fail"))
                .should(hasMessage("Missing login or password"));
    }

    @Test
    public void alreadyExistRegisterUserTest() {
        userService.register(randomUser);
        userService.register(randomUser)
                .should(hasStatusCode(400))
                .should(hasStatus("fail"))
                .should(hasMessage("Login already exist"));
    }

    @Test
    public void positiveAuthUserTest() {
        userService.register(randomUser);
        String token = userService.auth(randomUser)
                .should(hasStatusCode(200))
                .asJwt();
        Assert.assertNotNull(token);
    }

    @Test
    public void negativeAuthUserTest() {
        userService.register(randomUser);
        randomUser.setLogin("unknownLogin");
        userService.auth(randomUser)
                .should(hasStatusCode(401));
    }

    @Test
    public void positiveGetUserTest() {
        userService.register(randomUser);
        String token = userService.auth(randomUser).asJwt();
        Response response = userService.getUser(token)
                .should(hasStatusCode(200))
                .asResponse();
        Assert.assertNotNull(response.jsonPath().getString("id"));
    }

    @Test
    public void negativeGetUserTest() {
       userService.getUser("invalidJWT")
               .should(hasStatusCode(401));
    }

    @Test
    public void getUserWithoutTokenTest() {
        userService.getUser()
                .should(hasStatusCode(401));
    }

    @Test
    public void positiveUserPassChangeTest() {
        userService.register(randomUser);
        String token = userService.auth(randomUser).asJwt();
        userService.changePass(token, "defaultPassword")
                .should(hasStatusCode(200))
                .should(hasMessage("User password successfully changed"))
                .should(hasStatus("success"));
    }

    @Test
    public void negativeUserPassChangeTest() {
        userService.register(randomUser);
        String token = userService.auth(randomUser).asJwt().replaceAll("\\d+", "1");
        userService.changePass(token, "newPassword")
                .should(hasStatusCode(401));
    }

    @Test
    public void positiveDeleteUserTest() {
        userService.register(randomUser);
        String token = userService.auth(randomUser).asJwt();
        userService.deleteUser(token)
                .should(hasStatusCode(200))
                .should(hasMessage("User successfully deleted"))
                .should(hasStatus("success"));
    }

    @Test
    public void deleteDefaultUserTest() {
        userService.register(randomUser);
        String token = userService.auth().asJwt();
        userService.deleteUser(token)
                .should(hasStatusCode(400))
                .should(hasMessage("Cant delete base users"))
                .should(hasStatus("fail"));
    }

    @Test
    public void negativeDeleteUserTest() {
        userService.register(randomUser);
        userService.deleteUser("invalidJWT")
                .should(hasStatusCode(401));
    }

    @Test
    public void getAllUsersTest() {
       List <String> users = userService.getUsers().should(hasStatusCode(200))
               .asList(String.class);

       Assert.assertTrue(users.size() >= 5);
    }

}
