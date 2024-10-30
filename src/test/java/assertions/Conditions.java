package assertions;

import assertions.conditions.MessageCondition;
import assertions.conditions.StatusCodeCondition;
import assertions.conditions.StatusCondition;

public class Conditions {
    public static MessageCondition hasMessage(String expectedMessage) {
        return new MessageCondition(expectedMessage);
    }

    public static StatusCondition hasStatus(String expectedStatus) {
        return new StatusCondition(expectedStatus);
    }
    public static StatusCodeCondition hasStatusCode(Integer expectedStatusCode) {
        return new StatusCodeCondition(expectedStatusCode);
    }
}
