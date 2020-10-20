package com.jmframework.boot.mediastreamingspringbootautoconfigure.filewatch;

import java.nio.file.Path;

/**
 * Description: FileWatcherHandler, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/20/2020 3:22 PM
 */
public interface FileWatcherHandler {
    /**
     * On created.
     *
     * @param file the file
     */
    void onCreated(Path file);

    /**
     * On deleted.
     *
     * @param file the file
     */
    void onDeleted(Path file);

    /**
     * On modified.
     *
     * @param file the file
     */
    void onModified(Path file);
}
