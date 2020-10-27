package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.controller;

import com.drew.metadata.Directory;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.model.Video;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services.MediaInfoService;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

/**
 * Description: MediaStreamingReactiveController, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/26/2020 4:17 PM
 **/
@RestController
@RequiredArgsConstructor
public class MediaStreamingReactiveController {
    private final VideoService videoService;
    private final MediaInfoService mediaInfoService;

    @GetMapping(value = "/video-annotation", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<List<Video>> getVideo() {
        return Flux.interval(Duration.ofSeconds(2)).map(aLong -> videoService.getAllVideoList());
    }

    @GetMapping(value = "/media-info/{name}")
    public Flux<Directory> mediaInfo(@PathVariable String name) {
        return mediaInfoService.getMediaInfo(name);
    }
}
