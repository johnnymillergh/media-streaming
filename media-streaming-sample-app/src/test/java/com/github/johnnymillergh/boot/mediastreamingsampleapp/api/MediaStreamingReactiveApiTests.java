package com.github.johnnymillergh.boot.mediastreamingsampleapp.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

/**
 * Description: ApiTests, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/27/2020 4:36 PM
 **/
@Slf4j
@SpringBootTest
@AutoConfigureWebTestClient
public class MediaStreamingReactiveApiTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getVideoAnnotationBased() {
        webTestClient
                .get()
                .uri("/video-annotation")
                .accept(MediaType.ALL)
                .exchange()
                .expectStatus()
                .isOk();
        log.info("getVideoAnnotationBased passed");
    }

    @Test
    void mediaInfo() {
        webTestClient
                .get()
                .uri(String.format("/media-info/%s.mp4", UUID.randomUUID()))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is4xxClientError();
        log.info("mediaInfo passed.");
    }
}
