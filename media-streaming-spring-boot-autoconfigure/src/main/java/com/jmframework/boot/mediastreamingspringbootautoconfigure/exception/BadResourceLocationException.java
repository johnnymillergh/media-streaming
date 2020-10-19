package com.jmframework.boot.mediastreamingspringbootautoconfigure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * Description: BadResourceLocationException, Exception thrown when the video location is inaccessible or does not
 * exist.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * date 10/19/2020 4:55 PM
 **/
@Slf4j
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BadResourceLocationException extends RuntimeException {
    public BadResourceLocationException(String msg) {
        super(msg);
        log.error(msg);
    }

    public BadResourceLocationException(String msg, IOException ex) {
        super(msg, ex);
    }
}
