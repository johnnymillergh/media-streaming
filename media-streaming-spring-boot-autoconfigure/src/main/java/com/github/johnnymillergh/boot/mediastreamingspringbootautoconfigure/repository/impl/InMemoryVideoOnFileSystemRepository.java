package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.repository.impl;

import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.exception.VideoNotFoundException;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.model.Video;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: InMemoryVideoOnFileSystemRepository, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * date 10/19/2020 5:16 PM
 **/
@Slf4j
public class InMemoryVideoOnFileSystemRepository implements VideoRepository {
    private static final Map<String, Video> VIDEO_CACHE = new ConcurrentHashMap<>();

    @Override
    public Mono<Video> getVideoByName(String name) {
        return Mono.create(videoMonoSink -> {
            Video video = VIDEO_CACHE.get(name);
            if (video != null) {
                videoMonoSink.success(video);
            } else {
                videoMonoSink.error(new VideoNotFoundException());
            }
        });
    }

    @Override
    public Flux<Video> getAllVideos() {
        return Flux.fromIterable(VIDEO_CACHE.values());
    }

    @Override
    public List<Video> getAllVideoList() {
        return new LinkedList<>(VIDEO_CACHE.values());
    }

    @Override
    public Mono<Video> addVideo(Video video) {
        return Mono.fromCallable(() -> VIDEO_CACHE.put(video.getName(), video));
    }

    @Override
    public Mono<Video> deleteVideoByPath(Path path) {
        return this.deleteVideoByName(path.getFileName().toString());
    }

    @Override
    public Mono<Video> deleteVideoByName(String name) {
        return Mono.fromCallable(() -> VIDEO_CACHE.remove(name));
    }
}
