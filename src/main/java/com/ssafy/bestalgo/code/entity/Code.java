package com.ssafy.bestalgo.code.entity;

import com.ssafy.bestalgo.common.entity.BaseTimeEntity;
import com.ssafy.bestalgo.member.entity.Member;
import com.ssafy.bestalgo.problem.entity.Problem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Code extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Problem problem;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String content;

    @NotNull
    @Column(columnDefinition = "ENUM('BEST', 'NOVEL', 'GOOD', 'CAN_BE_BETTER')")
    @Enumerated(EnumType.STRING)
    private CodeType type;

    @NotNull
    private boolean isDeleted;

    public Code() {
    }

    private Code(Member member, Problem problem, String content, CodeType type) {
        this.member = member;
        this.problem = problem;
        this.content = content;
        this.type = type;
    }

    public static Code create(Member member, Problem problem, String content, CodeType type) {
        return new Code(member, problem, content, type);
    }

    public Integer getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Problem getProblem() {
        return problem;
    }

    public String getContent() {
        return content;
    }

    public void updateContent(@NotBlank String content) {
        this.content = content;
    }

    public CodeType getType() {
        return type;
    }

    public void updateType(@NotNull CodeType type) {
        this.type = type;
    }

    public boolean getDeleted() {
        return isDeleted;
    }
}
