package com.jmframework.boot.mediastreamingspringbootautoconfigure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * Description: Test, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * date 10/19/2020 2:33 PM
 **/
@Configuration
@EnableWebFlux
public class WebFluxAutoConfiguration implements WebFluxConfigurer {
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        // for resource region
        configurer.customCodecs().register(new ResourceRegionMessageWriter());
    }
}
