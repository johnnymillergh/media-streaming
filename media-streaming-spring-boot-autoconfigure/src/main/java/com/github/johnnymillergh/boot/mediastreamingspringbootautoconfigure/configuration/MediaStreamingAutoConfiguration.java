package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.configuration;

import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.controller.VideoController;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.handler.MediaStreamingExceptionHandler;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.handler.VideoRouteHandler;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.repository.VideoRepository;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.repository.impl.InMemoryVideoOnFileSystemRepository;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services.FileService;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services.VideoService;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services.impl.FileServiceImpl;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services.impl.VideoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.annotation.PostConstruct;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Description: MediaStreamingAutoConfiguration, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/19/2020 2:51 PM
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MediaStreamingProperties.class)
public class MediaStreamingAutoConfiguration {
    private final MediaStreamingProperties mediaStreamingProperties;

    @PostConstruct
    public void afterInitialization() {
        log.debug("MediaStreamingAutoConfiguration initialization is done. {}", mediaStreamingProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public MediaStreamingBootstrap bootstrap(VideoRepository videoRepository, FileService fileService) {
        return new MediaStreamingBootstrap(videoRepository, fileService, mediaStreamingProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebFluxAutoConfiguration webFluxAutoConfiguration() {
        return new WebFluxAutoConfiguration();
    }

    @Bean
    @ConditionalOnMissingBean
    public MediaStreamingExceptionHandler mediaStreamingExceptionHandler() {
        return new MediaStreamingExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public VideoRouteHandler videoRouteHandler(VideoService videoService, FileService fileService) {
        return new VideoRouteHandler(videoService);
    }

    /**
     * Video end point router function.
     * <p>
     * TODO: solve Nullable Problems
     *
     * @param videoRouteHandler the video route handler
     * @return the router function
     */
    @Bean
    @Deprecated
    @SuppressWarnings("NullableProblems")
    public RouterFunction<ServerResponse> videoEndPoint(VideoRouteHandler videoRouteHandler) {
        log.debug("videoEndPoint");
        return route()
                .nest(path("/videos"), builder -> builder.GET("", videoRouteHandler::listVideos)
                        .nest(path("/{name}"), videoBuilder -> videoBuilder.GET("", param("partial"),
                                                                                videoRouteHandler::getPartialContent).GET(
                                "", videoRouteHandler::getFullContent)
                        )
                ).build();
    }

    @Bean
    public VideoController videoController(VideoService videoService) {
        return new VideoController(videoService);
    }

    @Bean
    @ConditionalOnMissingBean
    public VideoService videoService(VideoRepository videoRepository) {
        return new VideoServiceImpl(videoRepository);
    }

    @Bean
    @ConditionalOnMissingBean
    public FileService fileService() {
        return new FileServiceImpl(mediaStreamingProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public VideoRepository videoRepository() {
        return new InMemoryVideoOnFileSystemRepository();
    }

    @SuppressWarnings("SameParameterValue")
    private static RequestPredicate param(String parameter) {
        return RequestPredicates.all().and(request -> request.queryParam(parameter).isPresent());
    }
}
