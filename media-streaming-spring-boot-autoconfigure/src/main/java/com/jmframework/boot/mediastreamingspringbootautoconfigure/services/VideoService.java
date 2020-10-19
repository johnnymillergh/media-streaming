package com.jmframework.boot.mediastreamingspringbootautoconfigure.services;

import com.jmframework.boot.mediastreamingspringbootautoconfigure.model.Video;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Description: VideoService, change description here.
 *
 * @author 钟俊, email: zhongjun@tuguide.cn date 10/19/2020 5:23 PM
 */
public interface VideoService {
    /**
     * Gets region.
     *
     * @param name    the name
     * @param request the request
     * @return the region
     */
    Mono<ResourceRegion> getRegion(String name, ServerRequest request);

    /**
     * Gets resource by name.
     *
     * @param name the name
     * @return the resource by name
     */
    Mono<UrlResource> getResourceByName(String name);

    /**
     * Gets all videos.
     *
     * @return the all videos
     */
    Flux<Video> getAllVideos();

    /**
     * Length of long.
     *
     * @param urlResource the url resource
     * @return the long
     */
    long lengthOf(UrlResource urlResource);
}
