package com.ssafy.bestalgo.code.dto.request;

public class CodeSearchRequest {
    private int problem;
    private String solver;
    private String type;

    public int getProblem() {
        return problem;
    }

    public void setProblem(int problem) {
        this.problem = problem;
    }

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CodeSearchRequest{" +
                "problem=" + problem +
                ", solver='" + solver + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
