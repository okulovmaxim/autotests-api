package assertions.conditions;

import assertions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import org.testng.Assert;

@RequiredArgsConstructor
public class StatusCodeCondition implements Condition {

    private final Integer statusCode;
    @Override
    public void check(ValidatableResponse response) {
        int actualStatusCode = response.extract().statusCode();

        Assert.assertEquals(statusCode, actualStatusCode);
    }

}
