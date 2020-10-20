package com.jmsoftware.boot.mediastreamingsampleapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Description: MediaStreamingSampleAppApplicationTests, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/20/2020 1:04 PM
 **/
@Slf4j
@WebFluxTest
class MediaStreamingSampleAppApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void videosTest() {
        webTestClient.get().uri("/videos").exchange().expectStatus().isOk();
    }
}
