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
import jakarta.validation.constraints.NotNull;

@Entity
public class Code extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CodeType getType() {
        return type;
    }

    public void setType(CodeType type) {
        this.type = type;
    }

    public boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
