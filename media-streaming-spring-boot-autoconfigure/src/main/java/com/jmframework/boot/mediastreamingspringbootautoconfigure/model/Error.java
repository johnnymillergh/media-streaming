package com.jmframework.boot.mediastreamingspringbootautoconfigure.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Description: Error, customized error
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com
 * date 10/19/2020 4:56 PM
 **/
@Data
@Builder
public class Error {
    @JsonProperty("time")
    private String timestamp;
    private String err;
    private String message;
    private Integer status;
    private String path;

    @Override
    public String toString() {
        return "{" +
                "\"status\":" + status +
                ",\"timestamp\":\"" + timestamp + '\"' +
                ",\"error\":\"" + err + '\"' +
                ",\"message\":\"" + message + '\"' +
                ",\"path\": \"" + path + '\"' +
                '}';
    }
}
