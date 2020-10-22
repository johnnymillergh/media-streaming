package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.repository;

import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.model.Video;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

/**
 * Description: VideoRepository, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com date 10/19/2020 5:16 PM
 */
public interface VideoRepository {
    /**
     * Gets video by name.
     *
     * @param name the name
     * @return the video by name
     */
    Mono<Video> getVideoByName(String name);

    /**
     * Gets all videos.
     *
     * @return the all videos
     */
    Flux<Video> getAllVideos();

    /**
     * Add video mono.
     *
     * @param video the video
     * @return the mono
     */
    Mono<Video> addVideo(Video video);

    /**
     * Delete video by path mono.
     *
     * @param path the path
     * @return the mono
     */
    Mono<Video> deleteVideoByPath(Path path);

    /**
     * Delete video by name mono.
     *
     * @param name the name
     * @return the mono
     */
    Mono<Video> deleteVideoByName(String name);
}
