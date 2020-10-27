package com.github.johnnymillergh.boot.mediastreamingsampleapp;

import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.handler.VideoRouteHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

/**
 * Description: MediaStreamingSampleAppApplicationTests, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/20/2020 1:04 PM
 **/
@Slf4j
@SpringBootTest
@AutoConfigureWebTestClient
class MediaStreamingSampleAppApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void videosTest() {
        EntityExchangeResult<List<VideoRouteHandler.VideoDetails>> returnResult = webTestClient
                .get()
                .uri("/videos")
                .accept(MediaType.ALL)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(VideoRouteHandler.VideoDetails.class)
                .returnResult();
        log.info("Video test: {}", returnResult.getResponseBody());
    }
}
