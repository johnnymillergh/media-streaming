package com.jmframework.boot.mediastreamingspringbootautoconfigure.configuration;

import com.jmframework.boot.mediastreamingspringbootautoconfigure.api.VideoRoutes;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.handler.MediaStreamingExceptionHandler;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.handler.VideoRouteHandler;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.repository.VideoRepository;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.repository.impl.InMemoryVideoRepository;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.services.FileService;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.services.VideoService;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.services.impl.FileServiceImpl;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.services.impl.VideoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Description: MediaStreamingAutoConfiguration, change description here.
 *
 * @author 钟俊, email: zhongjun@tuguide.cn, date: 10/19/2020 2:51 PM
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
    public Bootstrap bootstrap(VideoRepository videoRepository, FileService fileService) {
        return new Bootstrap(videoRepository, fileService);
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
        return new VideoRouteHandler(videoService, fileService);
    }

    @Bean
    @ConditionalOnMissingBean
    public VideoRoutes videoRoutes() {
        return new VideoRoutes();
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
        return new InMemoryVideoRepository();
    }
}
