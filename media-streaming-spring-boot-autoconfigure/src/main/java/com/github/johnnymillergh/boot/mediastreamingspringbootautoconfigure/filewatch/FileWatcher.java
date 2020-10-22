package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.filewatch;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.*;
import java.util.Optional;
import java.util.concurrent.*;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Description: FileWatcher, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/20/2020 3:19 PM
 * @see <a href='https://github.com/WhileLoop/file-watcher/'>Inspired by file-watcher</a>
 **/
@Slf4j
@Setter
public class FileWatcher {
    private static final ThreadFactory NAMED_THREAD_FACTORY =
            new ThreadFactoryBuilder().setNameFormat("file-watcher-thread-%d").build();
    private static final ExecutorService THREAD_POOL =
            new ThreadPoolExecutor(1, 2, 0L, TimeUnit.MILLISECONDS,
                                   new LinkedBlockingQueue<>(1024), NAMED_THREAD_FACTORY,
                                   new ThreadPoolExecutor.AbortPolicy());

    private final Path monitoredPath;
    private FileWatcherHandler fileWatcherHandler;

    public FileWatcher(String directory) {
        this(Paths.get(directory));
    }

    @SneakyThrows
    private FileWatcher(Path path) {
        this.monitoredPath = path;
        this.monitoredPath.register(WatchServiceSingleton.getInstance(),
                                    StandardWatchEventKinds.ENTRY_CREATE,
                                    StandardWatchEventKinds.ENTRY_DELETE,
                                    StandardWatchEventKinds.ENTRY_MODIFY);
        THREAD_POOL.execute(this::monitor);
    }

    private void monitor() {
        log.debug("Started watching: {}", this.monitoredPath);
        while (true) {
            // wait for key to be signaled
            Optional<WatchKey> optionalWatchKey = Optional.ofNullable(WatchServiceSingleton.getInstance().poll());
            if (optionalWatchKey.isPresent()) {
                var watchKey = optionalWatchKey.get();
                for (var watchEvent : watchKey.pollEvents()) {
                    WatchEvent.Kind<?> kind = watchEvent.kind();

                    // This key is registered only for ENTRY_CREATE events,
                    // but an OVERFLOW event can occur regardless if events are lost or discarded.
                    if (kind == OVERFLOW) {
                        continue;
                    }

                    // The filename is the context of the event.
                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> event = (WatchEvent<Path>) watchEvent;
                    Path filename = event.context();

                    // Resolve the filename against the directory.
                    // If the filename is "test" and the directory is "foo", the resolved name is "foo/test".
                    Path child = monitoredPath.resolve(filename);
                    if (kind == ENTRY_CREATE) {
                        fileWatcherHandler.onCreated(child);
                    } else if (kind == ENTRY_DELETE) {
                        fileWatcherHandler.onDeleted(child);
                    } else if (kind == ENTRY_MODIFY) {
                        fileWatcherHandler.onModified(child);
                    }
                }

                // Reset the key -- this step is critical if you want to receive further watch events.
                // If the key is no longer valid, the directory is inaccessible so exit the loop.
                boolean valid = watchKey.reset();
                if (!valid) {
                    log.debug("The watch key wasn't valid. {}", watchKey);
                    return;
                }
            }
        }
    }

    @SneakyThrows
    public void destroy() {
        THREAD_POOL.awaitTermination(5, TimeUnit.SECONDS);
        log.debug("THREAD_POOL for FileWatcher was terminated.");
    }
}
