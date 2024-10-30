package addons;

import models.user.UserData;

import java.util.Random;

public class RandomTestData {
    private static final Random random = new Random();

    public static UserData getRandomUser() {
        int randomNumber = Math.abs(random.nextInt());
        return UserData.builder()
                .login("userTestAPI" + System.currentTimeMillis())
                .pass("qwerty1234")
                .build();
    }
}
