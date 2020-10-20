package com.jmframework.boot.mediastreamingspringbootautoconfigure.filewatch;

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
    void onCreated(String file);

    /**
     * On deleted.
     *
     * @param file the file
     */
    void onDeleted(String file);

    /**
     * On modified.
     *
     * @param file the file
     */
    void onModified(String file);
}
