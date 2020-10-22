package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services;

import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.exception.BadResourceLocationException;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.BaseStream;

/**
 * Description: FileService, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/19/2020 5:24 PM
 */
public interface FileService {
    /**
     * Gets all files.
     *
     * @return the all files
     */
    Flux<Path> getAllFiles();

    /**
     * default method to create a flux from a stream of file paths
     *
     * @param path to traverse
     * @return flux
     */
    default Flux<Path> fromPath(Path path) {
        return Flux.using(() -> Files.walk(path, FileVisitOption.FOLLOW_LINKS), Flux::fromStream, BaseStream::close)
                .doOnDiscard(BaseStream.class, BaseStream::close)
                .doOnError(err -> {
                    throw Exceptions.propagate(
                            new BadResourceLocationException("error retrieving data from video location",
                                    (IOException) err));
                })
                .filter(filePath -> !filePath.toFile().isDirectory())
                .filter(filePath -> !filePath.getFileName().toString().startsWith("."));
    }
}
