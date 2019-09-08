package com.smartshare;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;

@Data
@ConfigurationProperties("com.smartshare")
public class ApplicationProperties {
    private CorsConfiguration cors = new CorsConfiguration();
    private Env env = new Env();

    @Data
    public class Env{
        private String url;
    }


}
