package com.jmframework.boot.mediastreamingspringbootautoconfigure.configuration;

import com.jmframework.boot.mediastreamingspringbootautoconfigure.filewatch.FileWatcher;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.filewatch.FileWatcherHandler;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.model.Video;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.repository.VideoRepository;
import com.jmframework.boot.mediastreamingspringbootautoconfigure.services.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.nio.file.Path;

/**
 * Description: Bootstrap, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/19/2020 2:51 PM
 **/
@Slf4j
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private final VideoRepository videoRepository;
    private final FileService fileService;
    private final MediaStreamingProperties mediaStreamingProperties;

    @PostConstruct
    public void afterInitialization() {
        log.debug("Bootstrap initialization is done. Start to process videos.");
        log.debug("Starting FileWatcher...");
        FileWatcher fileWatcher;
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
                log.info("onCreated: {}", file);
                Video video = new Video();
                video.setName(file.getFileName().toString());
                video.setLocation(file);
                Mono.just(video).then(videoRepository.addVideo(video)).subscribe();
            }

            @Override
            public void onDeleted(Path file) {
                log.info("onDeleted: {}", file);
            }

            @Override
            public void onModified(Path file) {
                log.info("onModified: {}", file);
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
                .doOnNext(path -> log.debug("found file in path: " + path.toUri() + " FileName: " + path.getFileName()))
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
}
