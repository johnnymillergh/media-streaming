package com.jmframework.boot.mediastreamingspringbootautoconfigure.api;

import com.jmframework.boot.mediastreamingspringbootautoconfigure.handler.VideoRouteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Description: VideoRoutes, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/19/2020 4:38 PM
 **/
public class VideoRoutes {
    @Bean
    RouterFunction<ServerResponse> videoEndPoint(VideoRouteHandler videoRouteHandler) {
        return route()
                .nest(path("/videos"), builder -> builder.GET("", videoRouteHandler::listVideos)
                        .nest(path("/{name}"), videoBuilder -> videoBuilder.GET("", param("partial"),
                                videoRouteHandler::getPartialContent).GET("", videoRouteHandler::getFullContent)
                        )
                ).build();
    }

    @SuppressWarnings("SameParameterValue")
    private static RequestPredicate param(String parameter) {
        return RequestPredicates.all().and(request -> request.queryParam(parameter).isPresent());
    }
}
