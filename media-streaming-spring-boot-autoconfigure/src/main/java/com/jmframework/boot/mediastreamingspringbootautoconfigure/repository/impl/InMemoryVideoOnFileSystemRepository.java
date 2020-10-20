package com.jmframework.boot.mediastreamingspringbootautoconfigure.repository.impl;

import com.jmframework.boot.mediastreamingspringbootautoconfigure.exception.VideoNotFoundException;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.model.Video;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.repository.VideoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: InMemoryVideoOnFileSystemRepository, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * date 10/19/2020 5:16 PM
 **/
public class InMemoryVideoOnFileSystemRepository implements VideoRepository {
    private final Map<String, Video> videoCache = new ConcurrentHashMap<>();

    @Override
    public Mono<Video> getVideoByName(String name) {
        return Mono.create(videoMonoSink -> {
            Video video = videoCache.get(name);
            if (video != null) {
                videoMonoSink.success(video);
            } else {
                videoMonoSink.error(new VideoNotFoundException());
            }
        });
    }

    @Override
    public Flux<Video> getAllVideos() {
        return Flux.fromIterable(videoCache.values());
    }

    @Override
    public Mono<Video> addVideo(Video video) {
        return Mono.fromCallable(() -> videoCache.put(video.getName(), video));
    }

    @Override
    public Mono<Video> deleteVideoByName(String name) {
        return Mono.fromCallable(() -> videoCache.remove(name));
    }
}
