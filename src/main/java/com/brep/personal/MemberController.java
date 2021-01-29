package com.brep.personal;

import com.brep.personal.client.MetaDiscoveryClient;
import com.brep.personal.client.MetaFeignClient;
import com.brep.personal.client.MetaRestTemplateClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 설명 :
 *
 * @author 이민호(Mark) / minholee93@sk.com
 * 2021/01/25
 * 10:16 오후
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("personal/v1")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MetaDiscoveryClient metaDiscoveryClient;
    private final MetaFeignClient metaFeignClient;
    private final MetaRestTemplateClient metaRestTemplateClient;

    @GetMapping("/members")
    public List<MembersResponse> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/example-property")
    public String getExampleProperty() {
        return memberService.getExampleProperty();
    }

    @GetMapping("/{clientType}/client-test")
    public String getClientTest(@PathVariable("clientType") String clientType) {
        String result = null;

        switch (clientType) {
            case "feign":
            try {
                result = metaFeignClient.getTracks();
            }catch (FeignException fe){
                log.error("feign error", fe);
            }
                break;
            case "rest":
                result = metaRestTemplateClient.getTracks();
                break;
            case "discovery":
                result = metaDiscoveryClient.getTracks();
                break;
        }

        return result;
    }
}
