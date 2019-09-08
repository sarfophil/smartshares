package com.smartshare.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Listener {
    @JsonProperty
    private String email;

    @JsonProperty
    private int messageType;

    @JsonProperty
    private LocalDateTime eventTime;

    @JsonProperty
    private Boolean isSuccess;

    public Listener(@JsonProperty String email,@JsonProperty int messageType,@JsonProperty LocalDateTime eventTime,@JsonProperty Boolean isSuccess) {
        this.email = email;
        this.messageType = messageType;
        this.eventTime = eventTime;
        this.isSuccess = isSuccess;
    }
}
