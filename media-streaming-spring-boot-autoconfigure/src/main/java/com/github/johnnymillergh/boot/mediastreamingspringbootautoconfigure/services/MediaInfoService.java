package com.github.johnnymillergh.boot.mediastreamingspringbootautoconfigure.services;

import com.drew.metadata.Directory;
import reactor.core.publisher.Flux;

/**
 * Description: MediaInfoService, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 10/27/2020 2:46 PM
 **/
public interface MediaInfoService {
    Flux<Directory> getMediaInfo(String name);
}
