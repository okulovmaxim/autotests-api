package assertions.conditions;

import assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import models.responses.Info;
import org.assertj.core.api.SoftAssertions;
import org.testng.Assert;

@RequiredArgsConstructor
public class StatusCondition implements Condition {

    private final String expectedStatus;
    private final SoftAssertions softAssertions = new SoftAssertions();
    @Override
    public void check(ValidatableResponse response) {
        Info info = response.extract().jsonPath().getObject("info", Info.class);

        Assert.assertEquals(expectedStatus, info.getStatus());
        softAssertions.assertThat(info.getStatus()).as("Неверный статус")
                .isEqualTo(expectedStatus);
    }
}
