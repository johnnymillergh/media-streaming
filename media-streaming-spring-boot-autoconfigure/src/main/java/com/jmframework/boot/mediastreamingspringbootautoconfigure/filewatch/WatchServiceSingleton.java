package com.jmframework.boot.mediastreamingspringbootautoconfigure.filewatch;

import lombok.SneakyThrows;

import java.nio.file.FileSystems;
import java.nio.file.WatchService;

/**
 * Description: SingletonWatchService, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/20/2020 4:52 PM
 **/
public class WatchServiceSingleton {
    private static volatile WatchService singletonInstance;

    private WatchServiceSingleton() {
    }

    @SneakyThrows
    public static WatchService getInstance() {
        if (singletonInstance == null) {
            synchronized (WatchServiceSingleton.class) {
                if (singletonInstance == null) {
                    singletonInstance = FileSystems.getDefault().newWatchService();
                }
            }
        }
        return singletonInstance;
    }
}
