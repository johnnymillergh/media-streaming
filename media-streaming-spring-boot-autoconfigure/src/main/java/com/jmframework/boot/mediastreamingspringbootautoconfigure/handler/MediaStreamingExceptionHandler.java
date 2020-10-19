package com.jmframework.boot.mediastreamingspringbootautoconfigure.handler;

import com.jmframework.boot.mediastreamingspringbootautoconfigure.model.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.handler.WebFluxResponseStatusExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.channels.ClosedChannelException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Description: MediaStreamingExceptionHandler, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * date 10/19/2020 4:38 PM
 **/
@Slf4j
@Order(-2)
@Component
public class MediaStreamingExceptionHandler extends WebFluxResponseStatusExceptionHandler {
    final SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public MediaStreamingExceptionHandler() {
        log.info("Initialized MediaStreamingExceptionHandler");
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex.getCause() instanceof ClosedChannelException) {
            log.error("CLOSED CHANNEL ERROR OBSERVED! Message: {}", ex.getMessage());
            // ignore after log
            return Mono.empty();
        }

        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();

        HttpHeaders responseHeaders = response.getHeaders();

        HttpStatus status = super.determineStatus(ex);

        // TODO: do not use simpleDate
        Error error = Error.builder()
                .timestamp(simpleDate.format(new Date()))
                .status(status != null ? status.value() : 0)
                .message(ex.getMessage())
                .path(exchange.getRequest().getURI().toString())
                .build();

        if (status != null) {
            if (status == HttpStatus.NOT_FOUND) {
                error.setErr("Not Found");
                log.warn(buildResponse(exchange, ex));
            } else if (status == HttpStatus.BAD_REQUEST) {
                error.setErr("Bad Request");
                // bad routes are warn level
                log.warn(buildResponse(exchange, ex));
            } else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
                error.setErr("The Server Encountered an error");
                // internal server errors are debug level
                log.error(buildResponse(exchange, ex), ex);
            }
        } else {
            // TODO(Refactor)
            if (ex instanceof NumberFormatException) {
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                error.setErr("The value passed is not an integer");
                error.setStatus(HttpStatus.BAD_REQUEST.value());

                MultiValueMap<String, String> queryParams = request.getQueryParams();

                String partial = "partial";
                if (!queryParams.isEmpty() && queryParams.containsKey(partial)) {
                    error.setMessage("param " + queryParams.getFirst(partial) + " is not an integer. " +
                            "please provide a number between 1 and 5");
                }
            }
        }

        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        responseHeaders.setCacheControl(CacheControl.noCache());
        response.setRawStatusCode(error.getStatus());

        byte[] bytes = error.toString().getBytes();
        DataBuffer buffer = response.bufferFactory().wrap(bytes);

        return response
                .writeWith(Mono.just(buffer));
    }

    private String buildResponse(ServerWebExchange exchange, Throwable ex) {
        return ("Failed to handle error in request [" + exchange.getRequest().getMethod() + " " + exchange.getRequest().getURI() + "]: " + ex.getMessage());
    }
}
