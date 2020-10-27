package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.filewatch;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.WatchService;
import java.util.Optional;

/**
 * Description: WatchServiceSingleton, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/20/2020 4:52 PM
 **/
@Slf4j
public class WatchServiceSingleton {
    private static volatile WatchService singletonInstance;

    /**
     * Private constructors are required for Singleton classes so that objects of such classes cannot be instantiated
     * from outside the class. If Singleton classes do not have private constructors, objects of such classes can be
     * created from outside too and hence the class will no longer be Singleton class.
     *
     * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/26/2020 1:57 PM
     */
    private WatchServiceSingleton() {
    }

    /**
     * Gets WatchService instance. Minimize synchronized range, with double null check.
     *
     * @return the WatchService instance
     * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/26/2020 1:51 PM
     */
    @SneakyThrows
    public static WatchService getInstance() {
        if (singletonInstance == null) {
            synchronized (WatchServiceSingleton.class) {
                if (singletonInstance == null) {
                    singletonInstance = FileSystems.getDefault().newWatchService();
                    log.debug("WatchService instance initiated.");
                }
            }
        }
        return singletonInstance;
    }

    /**
     * Close WatchService synchronously.
     *
     * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/26/2020 1:49 PM
     */
    public static synchronized void close() {
        Optional.ofNullable(singletonInstance).ifPresent(watchService -> {
            try {
                watchService.close();
                log.debug("WatchService closed.");
            } catch (IOException e) {
                log.error("Exception occurred when closing WatchService.", e);
            }
        });
    }
}
