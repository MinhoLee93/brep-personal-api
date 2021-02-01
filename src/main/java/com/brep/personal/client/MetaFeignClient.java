package com.brep.personal.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 설명 :
 *
 * @author 이민호(Mark) / minholee93@sk.com
 * 2021/01/27
 * 1:53 오전
 */
@FeignClient(
        name = "meta-api"
//       ,fallbackFactory = MetaFeignFallbackFactory.class
)
public interface MetaFeignClient {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/meta/v1/tracks",
            consumes = "application/json")
    String getTracks();
}
