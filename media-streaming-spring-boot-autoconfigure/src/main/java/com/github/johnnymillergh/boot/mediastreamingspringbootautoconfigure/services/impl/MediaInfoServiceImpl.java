package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services.impl;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services.FileService;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services.MediaInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * Description: MediaInfoServiceImpl, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/27/2020 2:47 PM
 **/
@Slf4j
@RequiredArgsConstructor
public class MediaInfoServiceImpl implements MediaInfoService {
    private final FileService fileService;

    @Override
    public Flux<Directory> getMediaInfo(String name) {
        val fileByFileName = fileService.getFileByFileName(name);
        Mono<Iterable<Directory>> iterableMono = fileByFileName.map(path -> {
            Iterable<Directory> directories = null;
            try {
                Metadata metadata = ImageMetadataReader.readMetadata(path.toFile());
                directories = metadata.getDirectories();
            } catch (ImageProcessingException | IOException e) {
                log.error("Cannot read file metadata!", e);
            }
            return directories;
        });
        return iterableMono.flatMapMany(Flux::fromIterable);
    }
}
