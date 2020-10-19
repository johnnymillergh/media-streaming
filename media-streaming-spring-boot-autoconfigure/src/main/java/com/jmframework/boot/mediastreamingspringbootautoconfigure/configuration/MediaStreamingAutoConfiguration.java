package com.jmframework.boot.mediastreamingspringbootautoconfigure.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * Description: MediaStreamingAutoConfiguration, change description here.
 *
 * @author 钟俊, email: zhongjun@tuguide.cn
 * date 10/19/2020 2:51 PM
 **/
@Configuration
@EnableWebFlux
@EnableConfigurationProperties(MediaStreamingProperties.class)
public class MediaStreamingAutoConfiguration {
}
