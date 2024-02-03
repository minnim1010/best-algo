package com.ssafy.bestalgo.code.dto.response;

import java.time.LocalDateTime;

public class CodeResponse {
    private int id;
    private String solver;
    private LocalDateTime createdTime;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CodeResponse{" +
                "id=" + id +
                ", solver='" + solver + '\'' +
                ", createdTime=" + createdTime +
                ", content='" + content + '\'' +
                '}';
    }
}
