package com.example.demo.mock;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.github.tomakehurst.wiremock.client.WireMock.get;

@Service
@RequiredArgsConstructor
public class TestMock {
    private final WireMockServer vm;

    @PostConstruct
    public void init(){
        vm.stubFor(
                get(WireMock.urlEqualTo("/test"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withHeader("Content-Type","application/json")
                                        .withStatus(200)
                                        .withBody("{\"Doc\":200}")
                        )
        );
    }
}
