package com.ssafy.bestalgo.code.dto.request;

public class CodeDeleteRequest {
    private String solver;
    private String password;

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
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
                ", password='" + password + '\'' +
                '}';
    }
}