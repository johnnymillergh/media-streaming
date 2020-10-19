package com.jmframework.boot.mediastreamingspringbootautoconfigure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: MediaStreamingProperties, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * date 10/19/2020 3:28 PM
 **/
@Data
@ConfigurationProperties(prefix = MediaStreamingProperties.PREFIX)
public class MediaStreamingProperties {
    public static final String PREFIX = "media-streaming";
    /**
     * Video directory on file system
     */
    private String videoDirectoryOnFileSystem;
}
