package com.jmframework.boot.mediastreamingspringbootautoconfigure.repository.impl;

import com.jmframework.boot.mediastreamingspringbootautoconfigure.exception.VideoNotFoundException;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.model.Video;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.repository.VideoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: InMemoryVideoRepository, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * date 10/19/2020 5:16 PM
 **/
@Repository
public class InMemoryVideoRepository implements VideoRepository {

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
        synchronized (videoCache) {
            return Flux.fromIterable(videoCache.values());
        }
    }

    @Override
    public Mono<Video> addVideo(Video video) {
        synchronized (videoCache) {
            return Mono.fromCallable(() -> videoCache.put(video.getName(), video));
        }
    }
}
