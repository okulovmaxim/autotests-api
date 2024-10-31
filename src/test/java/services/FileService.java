package services;

import assertions.AssertableResponse;
import lombok.SneakyThrows;
import org.testng.Assert;

import java.io.File;
import java.nio.file.Files;

import static io.restassured.RestAssured.given;

public class FileService {
    public AssertableResponse downloadBaseImage() {
        return new AssertableResponse(given()
                .get("/files/download")
                .then());
    }

    public AssertableResponse downloadLastFile() {
        return new AssertableResponse(given()
                .get("/files/downloadLastUploaded")
                .then());
    }

    @SneakyThrows
    public AssertableResponse uploadFile(File file) {
        return new AssertableResponse(given()
                .multiPart("file", "defaultFile", Files.readAllBytes(file.toPath()))
                .post("/files/upload")
                .then());
    }
}
