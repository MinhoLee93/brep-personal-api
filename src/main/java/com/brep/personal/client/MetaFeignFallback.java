package com.brep.personal.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 설명 :
 *
 * @author 이민호(Mark) / minholee93@sk.com
 * 2021/01/31
 * 10:13 오후
 */
@Slf4j
@Component
public class MetaFeignFallback implements MetaFeignClient {

    @Override
    public String getTracks() {
        log.error("meta feign client fallback");
        return "Track Default";
    }
}
