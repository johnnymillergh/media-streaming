package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.filewatch;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.sun.nio.file.SensitivityWatchEventModifier;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.Consumer;

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
            new ThreadPoolExecutor(1, 4, 0L, TimeUnit.MILLISECONDS,
                                   new LinkedBlockingQueue<>(1024), NAMED_THREAD_FACTORY,
                                   new ThreadPoolExecutor.AbortPolicy());
    private static final ConcurrentHashMap<WatchKey, Path> WATCH_KEY_MAP = new ConcurrentHashMap<>(256);
    /**
     * Register path. Store WatchKey for each directory in WATCH_KEY_MAP.
     */
    private static final Consumer<Path> REGISTER = (path) -> {
        if (!path.toFile().exists() || !path.toFile().isDirectory()) {
            throw new RuntimeException(String.format("Folder %s does not exist or is not a directory!", path));
        }
        if (WATCH_KEY_MAP.containsValue(path)) {
            log.debug("Found duplicated path in WATCH_KEY_MAP, will not register again. Path: {}", path);
            return;
        }
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    WatchKey watchKey = dir.register(WatchServiceSingleton.getInstance(),
                                                     new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY},
                                                     SensitivityWatchEventModifier.HIGH);
                    WATCH_KEY_MAP.put(watchKey, dir);
                    log.debug("Registering {} in watcher service. WATCH_KEY_MAP size: {}", dir,
                              WATCH_KEY_MAP.values().size());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error registering path: %s", path));
        }
    };

    private final Path monitoredPath;
    private FileWatcherHandler fileWatcherHandler;

    public FileWatcher(String directory) {
        this(Paths.get(directory));
    }

    @SneakyThrows
    private FileWatcher(Path path) {
        this.monitoredPath = path;
        log.debug("Starting Recursive Watcher");
        REGISTER.accept(monitoredPath);
        THREAD_POOL.execute(this::monitorRecursively);
    }

    /**
     * Monitor directory recursively.
     *
     * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/27/2020 9:46 AM
     */
    private void monitorRecursively() {
        while (true) {
            // Wait for key to be signaled
            final Optional<WatchKey> optionalWatchKey;
            try {
                optionalWatchKey = Optional.ofNullable(WatchServiceSingleton.getInstance().poll());
            } catch (ClosedWatchServiceException e) {
                log.error("Detected closed WatchService. Terminating followup FileWatcher operations.", e);
                return;
            }

            if (optionalWatchKey.isPresent()) {
                val watchKey = optionalWatchKey.get();
                val optionalDirectory = Optional.ofNullable(WATCH_KEY_MAP.get(watchKey));
                if (optionalDirectory.isEmpty()) {
                    log.warn("WatchKey {} not recognized!", watchKey);
                    continue;
                }

                watchKey.pollEvents()
                        .stream()
                        // This watcherKey is registered only for ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY events,
                        // but an OVERFLOW event can occur regardless if events are lost or discarded.
                        .filter(watchEvent -> (watchEvent.kind() != OVERFLOW))
                        // Iterate WatchEvent
                        .forEach(watchEvent -> {
                            // The filename is the context of the event if possible.
                            @SuppressWarnings("unchecked") val absolutePath =
                                    optionalDirectory.get().resolve(((WatchEvent<Path>) watchEvent).context());
                            val file = absolutePath.toFile();
                            val kind = watchEvent.kind();
                            if (file.isDirectory()) {
                                log.debug("Absolute path found, directory: {}", absolutePath);
                                REGISTER.accept(absolutePath);
                            } else {
                                log.debug("Detected file changed. File: {}, WatchEvent kind: {}", file, kind);
                                val optionalFileWatcherHandler = Optional.ofNullable(this.fileWatcherHandler);
                                if (optionalFileWatcherHandler.isEmpty()) {
                                    log.warn("FileWatcherHandler is null! FileWatcher will not work properly.");
                                }
                                optionalFileWatcherHandler.ifPresent(handler -> {
                                    if (kind == ENTRY_CREATE) {
                                        this.fileWatcherHandler.onCreated(absolutePath);
                                    } else if (kind == ENTRY_DELETE) {
                                        this.fileWatcherHandler.onDeleted(absolutePath);
                                    } else if (kind == ENTRY_MODIFY) {
                                        this.fileWatcherHandler.onModified(absolutePath);
                                    }
                                });
                            }
                        });

                // IMPORTANT: The key must be reset after processed
                // Reset the key -- this step is critical if you want to receive further watch events.
                // If the key is no longer valid, the directory is inaccessible so exit the loop.
                val valid = watchKey.reset();
                if (!valid) {
                    log.debug("The watch key wasn't valid. {}", watchKey);
                    break;
                }
            }
        }
    }

    /**
     * Destroy FileWatcher.
     * <p>
     * 1. Close WatchService
     * <p>
     * 2. Terminate THREAD_POOL
     *
     * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/27/2020 10:01 AM
     */
    @SneakyThrows
    public void destroy() {
        WatchServiceSingleton.close();
        val terminated = THREAD_POOL.awaitTermination(5, TimeUnit.SECONDS);
        log.debug("Terminated THREAD_POOL for FileWatcher. Termination Result: {}", terminated);
    }
}
