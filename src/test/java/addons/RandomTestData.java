package addons;

import com.github.javafaker.Faker;
import models.DlcsItem;
import models.GameData;
import models.Requirements;
import models.SimilarDlc;
import models.UserData;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RandomTestData {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public static UserData getRandomUser() {
        return UserData.builder()
                .login(faker.zelda().character() + System.currentTimeMillis())
                .pass(faker.internet().password())
                .build();
    }

    public static GameData getRandomGame() {
        SimilarDlc similarDlc = SimilarDlc.builder()
                .dlcNameFromAnotherGame(faker.funnyName().name())
                .isFree(false)
                .build();

        DlcsItem dlcsItem = DlcsItem.builder()
                .description(faker.funnyName().name())
                .dlcName(faker.zelda().character())
                .isDlcFree(false)
                .price(random.nextInt(1, 500))
                .rating(random.nextInt(10))
                .similarDlc(similarDlc)
                .build();

        Requirements requirements = Requirements.builder()
            .hardDrive(random.nextInt(30, 70))
            .osName("Windows")
            .ramGb(random.nextInt(4, 16))
            .videoCard("NVIDEA")
            .build();

        return GameData.builder()
                .company(faker.company().name())
                .description(faker.funnyName().name())
                .dlcs(Collections.singletonList(dlcsItem))
                .gameId(random.nextInt())
                .genre(faker.book().genre())
                .isFree(false)
                .price(random.nextInt(400))
                .publishDate(LocalDateTime.now().toString())
                .rating(random.nextInt(10))
                .requiredAge(random.nextBoolean())
                .requirements(requirements)
                .tags(Arrays.asList("shooter","quests"))
                .title(faker.beer().name())
                .build();
    }
}
