package com.jmsoftware.boot.mediastreamingsampleapp;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.Instant;
import java.util.TimeZone;

/**
 * Description: MediaStreamingSampleAppApplication, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * date 10/16/2020 2:58 PM
 **/
@Slf4j
@SpringBootApplication
public class MediaStreamingSampleAppApplication {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static void main(String[] args) {
        val startInstant = Instant.now();
        SpringApplication.run(MediaStreamingSampleAppApplication.class, args);
        val endInstant = Instant.now();
        val duration = Duration.between(startInstant, endInstant);
        log.info("🥳 Congratulations! 🎉");
        log.info("🖥 {} started!", MediaStreamingSampleAppApplication.class.getSimpleName());
        log.info("⏳ Deployment duration: {} seconds ({} ms)", duration.getSeconds(), duration.toMillis());
        log.info("⏰ App started at {} (timezone - {})", endInstant, TimeZone.getDefault().getDisplayName());
        log.info("{}  App running at{}  - Local:   http://localhost:8080/", LINE_SEPARATOR, LINE_SEPARATOR);
    }
}
