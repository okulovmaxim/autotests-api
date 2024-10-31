package tests;

import assertions.Conditions;
import io.qameta.allure.Attachment;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static assertions.Conditions.*;

public class FileTests extends BaseApiTest {

    @Attachment(value = "downloaded", type = "image/png")
    private byte [] attachFile(byte [] bytes) {
        return bytes;
    }

    @Test
    public void positiveDownloadFileTest() {
        byte [] file = fileService.downloadBaseImage()
                .asResponse().asByteArray();

        attachFile(file);
        File expectedFile = new File("src/test/resources/threadqa.jpeg");

        Assert.assertEquals(expectedFile.length(), file.length);
    }

    @Test
    public void positiveUploadFileTest() {
        File expectedFile = new File("src/test/resources/threadqa.jpeg");
        fileService.uploadFile(expectedFile)
                .should(hasStatusCode(200))
                .should(hasMessage("file uploaded to server"))
                .should(hasStatus("success"));

        byte [] actualFile = fileService.downloadLastFile().asResponse().asByteArray();

        Assert.assertTrue(actualFile.length != 0);
        Assert.assertEquals(expectedFile.length(), actualFile.length);
    }
}
