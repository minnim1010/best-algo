package com.ssafy.bestalgo.code.dto.request;

public class CodeRequest {
    private String solver;
    private String code;
    private String password;

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CodeRequest{" +
                "solver='" + solver + '\'' +
                ", code='" + code + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}