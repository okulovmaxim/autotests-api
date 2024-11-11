package assertions.conditions;

import assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import models.responses.Info;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;

@RequiredArgsConstructor
public class MessageCondition implements Condition {

    private final String expectedMessage;
    private final SoftAssertions softAssertions = new SoftAssertions();
    @Override
    public void check(ValidatableResponse response) {
        Info info = response.extract().jsonPath().getObject("info", Info.class);

        Assert.assertEquals(expectedMessage, info.getMessage());
        softAssertions.assertThat(info.getMessage()).as("Неверное сообщение об ошибке")
                .isEqualTo(expectedMessage);
    }
}
