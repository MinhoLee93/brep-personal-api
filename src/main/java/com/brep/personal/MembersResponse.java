package com.brep.personal;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 설명 :
 *
 * @author 이민호(Mark) / minholee93@sk.com
 * 2021/01/26
 * 8:52 오후
 */
@Getter
@ToString
@Builder
public class MembersResponse {

    private final Long id;

    private final String name;

    public static MembersResponse from(Member member) {
        return MembersResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
    }
}
