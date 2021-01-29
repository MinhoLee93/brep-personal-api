package com.brep.personal.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 설명 :
 *
 * @author 이민호(Mark) / minholee93@sk.com
 * 2021/01/27
 * 1:53 오전
 */
@Component
@RequiredArgsConstructor
public class MetaRestTemplateClient {

    private final RestTemplate restTemplate;

    public String getTracks() {
        ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        "http://meta-api/meta/v1/tracks",
                        HttpMethod.GET,
                        null, String.class);

        return restExchange.getBody();
    }
}