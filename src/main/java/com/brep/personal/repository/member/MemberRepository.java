package com.brep.personal.repository.member;

import com.brep.personal.repository.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 설명 :
 *
 * @author 이민호(Mark) / minholee93@sk.com
 * 2021/01/26
 * 8:49 오후
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findAll();

}
