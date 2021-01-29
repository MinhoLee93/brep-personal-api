package com.brep.personal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 설명 :
 *
 * @author 이민호(Mark) / minholee93@sk.com
 * 2021/01/26
 * 8:45 오후
 */
@Service
@RequiredArgsConstructor
public class MemberService {

    private final ServiceConfig serviceConfig;

    private final MemberRepository memberRepository;

    public List<MembersResponse> getAllMembers() {
        return memberRepository.findAll().stream().map(MembersResponse::from).collect(Collectors.toList());
    }

    public String getExampleProperty() {
        return serviceConfig.getExampleProperty();
    }
}
