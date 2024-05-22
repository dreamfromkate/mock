package com.example.demo.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wiremock.webhooks.Webhooks;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Configuration
public class WiremockConfig {
    @Value("${wiremock.port}")
    private int port;

    @Bean
    public WireMockServer wireMockServer() {
        WireMockServer wireMockServer = new WireMockServer(
                options()
                        .port(port)
                        .extensions(Webhooks.class)
                        .extensions(responseTemplateTransformer())
        );
        wireMockServer.start();

        return wireMockServer;
    }

    @Bean
    public ResponseTemplateTransformer responseTemplateTransformer() {

        return new ResponseTemplateTransformer(true);
    }

}
