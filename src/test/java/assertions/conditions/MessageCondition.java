package assertions.conditions;

import assertions.AssertableResponse;
import assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import models.responses.Info;
import org.testng.Assert;

@RequiredArgsConstructor
public class MessageCondition implements Condition {
    private final String expectedMessage;
    @Override
    public void check(ValidatableResponse response) {
        Info info = response.extract().jsonPath().getObject("info", Info.class);
        Assert.assertEquals(expectedMessage, info.getMessage());
    }
}
