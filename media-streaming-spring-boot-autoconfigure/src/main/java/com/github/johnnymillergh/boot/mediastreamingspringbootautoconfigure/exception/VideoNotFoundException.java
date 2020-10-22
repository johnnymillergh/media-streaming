package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;

/**
 * Description: BadResourceLocationException, Exception thrown when the video location is inaccessible or does not
 * exist.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * date 10/19/2020 4:56 PM
 **/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class VideoNotFoundException extends FileNotFoundException {
    public VideoNotFoundException() {
        super("video was not found");
    }
}
