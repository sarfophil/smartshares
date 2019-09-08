package com.smartshare.ro;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Links {
    @JsonProperty
    private String linkDescription;

    @JsonProperty
    private String link;

    public Links(@JsonProperty String link,@JsonProperty String linkDescription) {
        this.link = link;
        this.linkDescription = linkDescription;
    }
}
