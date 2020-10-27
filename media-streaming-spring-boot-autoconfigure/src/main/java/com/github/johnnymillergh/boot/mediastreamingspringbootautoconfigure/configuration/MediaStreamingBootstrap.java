package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.configuration;

import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.filewatch.FileWatcher;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.filewatch.FileWatcherHandler;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.model.Video;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.repository.VideoRepository;
import com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Description: MediaStreamingBootstrap, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/19/2020 2:51 PM
 **/
@Slf4j
@RequiredArgsConstructor
public class MediaStreamingBootstrap implements CommandLineRunner {
    private final VideoRepository videoRepository;
    private final FileService fileService;
    private final MediaStreamingProperties mediaStreamingProperties;
    private FileWatcher fileWatcher;

    @PostConstruct
    private void postConstruct() {
        log.debug("MediaStreamingBootstrap initialization is done. Start to process videos.");
        log.debug("Starting FileWatcher...");
        try {
            fileWatcher = new FileWatcher(mediaStreamingProperties.getVideoDirectoryOnFileSystem());
        } catch (Exception e) {
            log.error("Cannot build FileWatcher, file observation failed! " +
                              "Check `media-streaming.videoDirectoryOnFileSystem` configuration.", e);
            return;
        }
        fileWatcher.setFileWatcherHandler(new FileWatcherHandler() {
            @Override
            public void onCreated(Path file) {
                Video video = new Video();
                video.setName(file.getFileName().toString());
                video.setLocation(file);
                Mono.just(video).then(videoRepository.addVideo(video)).subscribe();
            }

            @Override
            public void onDeleted(Path file) {
                Mono.just(file).then(videoRepository.deleteVideoByPath(file)).subscribe();
            }

            @Override
            public void onModified(Path file) {
                Video video = new Video();
                video.setName(file.getFileName().toString());
                video.setLocation(file);
                Mono.just(video).then(videoRepository.addVideo(video)).subscribe();
            }
        });
    }

    @Override
    public void run(String... args) {
        fileService.getAllFiles()
                .doOnNext(path -> log.debug("Found file in path: {}, file name: {}", path.toUri(), path.getFileName()))
                .flatMap(path -> {
                    Video video = new Video();
                    video.setName(path.getFileName().toString());
                    video.setLocation(path);
                    return videoRepository.addVideo(video);
                })
                .subscribe();

        videoRepository.getAllVideos()
                .doOnNext(video -> log.debug("Registered video: " + video.getName()))
                .subscribe();
    }

    @PreDestroy
    private void preDestroy() {
        log.debug("Destroying {}, fileWatcher: {}", this.getClass().getSimpleName(), fileWatcher);
        Optional.ofNullable(fileWatcher).ifPresent(fileWatcher1 -> {
            fileWatcher1.terminate();
            log.debug("FileWatcher terminated: {}", fileWatcher1.isTerminated());
        });
    }
}
