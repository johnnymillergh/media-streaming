package com.jmframework.boot.mediastreamingspringbootautoconfigure.services.impl;

import com.jmframework.boot.mediastreamingspringbootautoconfigure.configuration.MediaStreamingProperties;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.services.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Description: FileServiceImpl, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/19/2020 5:26 PM
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final MediaStreamingProperties mediaStreamingProperties;

    @Override
    public Flux<Path> getAllFiles() {
        return fromPath(Paths.get(mediaStreamingProperties.getVideoDirectoryOnFileSystem()));
    }
}
