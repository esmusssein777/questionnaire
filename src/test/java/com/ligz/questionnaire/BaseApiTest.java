package com.ligz.questionnaire;

import io.restassured.config.EncoderConfig;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BaseApiTest {

    @Autowired
    protected WebApplicationContext context;

    @BeforeEach
    public void init() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }


    @AfterEach
    public void reset() {
        RestAssuredMockMvc.reset();
    }


    protected MockMvcRequestSpecification given() {
        return RestAssuredMockMvc.given()
                                 .config(RestAssuredMockMvcConfig.config().encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                                 .config(RestAssuredMockMvcConfig.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                                 .header("Accept", ContentType.JSON.withCharset("UTF-8"))
                                 .header("Content-Type", ContentType.JSON.withCharset("UTF-8"));
    }
}
