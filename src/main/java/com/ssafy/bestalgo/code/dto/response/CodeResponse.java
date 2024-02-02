package com.ssafy.bestalgo.code.dto.response;

import java.time.LocalDateTime;

public class CodeResponse {
    private int codeId;
    private String solver;
    private LocalDateTime createdTime;
    private String code;

    public int getCodeId() {
        return codeId;
    }

    public String getSolver() {
        return solver;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "CodeResponse{" +
                "codeId=" + codeId +
                ", solver='" + solver + '\'' +
                ", createdTime=" + createdTime +
                ", code='" + code + '\'' +
                '}';
    }
}
