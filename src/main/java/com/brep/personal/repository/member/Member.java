package com.brep.personal.repository.member;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 설명 :
 *
 * @author 이민호(Mark) / minholee93@sk.com
 * 2021/01/26
 * 8:45 오후
 */
@Getter
@Entity
@Table(name = "tb_member")
public class Member {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

}
