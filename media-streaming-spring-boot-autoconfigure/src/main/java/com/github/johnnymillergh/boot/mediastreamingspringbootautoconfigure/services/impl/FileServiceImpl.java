package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services.impl;

import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.configuration.MediaStreamingProperties;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.model.Video;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.repository.VideoRepository;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Description: FileServiceImpl, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/19/2020 5:26 PM
 */
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final MediaStreamingProperties mediaStreamingProperties;
    private final VideoRepository videoRepository;

    @Override
    public Flux<Path> getAllFiles() {
        return fromPath(Paths.get(mediaStreamingProperties.getVideoDirectoryOnFileSystem()));
    }

    @Override
    public Mono<Path> getFileByFileName(String fileName) {
        return Mono.from(videoRepository.getVideoByName(fileName).map(Video::getLocation));
    }
}
