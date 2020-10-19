package com.jmframework.boot.mediastreamingspringbootautoconfigure.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Description: MediaStreamingAutoConfiguration, change description here.
 *
 * @author 钟俊, email: zhongjun@tuguide.cn
 * date 10/19/2020 2:51 PM
 **/
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MediaStreamingProperties.class)
public class MediaStreamingAutoConfiguration {
    private final MediaStreamingProperties mediaStreamingProperties;

    @PostConstruct
    public void afterInitialization() {
        log.info("afterInitialization: {}", mediaStreamingProperties);
    }
}
