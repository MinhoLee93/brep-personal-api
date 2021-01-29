package com.brep.personal.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 설명 :
 *
 * @author 이민호(Mark) / minholee93@sk.com
 * 2021/01/27
 * 1:53 오전
 */
@Component
public class MetaDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public String getTracks() {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("meta-api");

        if (instances.size() == 0) return null;
        String serviceUri = String.format("%s/meta/v1/tracks", instances.get(0).getUri().toString());

        ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, String.class);

        return restExchange.getBody();
    }
}