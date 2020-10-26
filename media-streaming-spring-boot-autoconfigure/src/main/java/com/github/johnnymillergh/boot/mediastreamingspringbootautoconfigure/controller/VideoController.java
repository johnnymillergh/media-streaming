package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.controller;

import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.model.Video;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

/**
 * Description: VideoController, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/26/2020 4:17 PM
 **/
@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoRepository videoRepository;

    @GetMapping(value = "/video-annotation", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<List<Video>> getVideo() {
        return Flux.interval(Duration.ofSeconds(2)).map(aLong -> videoRepository.getAllVideoList());
    }
}
