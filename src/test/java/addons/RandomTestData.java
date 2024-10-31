package addons;

import com.github.javafaker.Faker;
import models.user.UserData;
import java.util.Random;

public class RandomTestData {

    private static final Faker faker = new Faker();

    public static UserData getRandomUser() {
        return UserData.builder()
                .login(faker.zelda().character() + System.currentTimeMillis())
                .pass(faker.internet().password())
                .build();
    }
}
