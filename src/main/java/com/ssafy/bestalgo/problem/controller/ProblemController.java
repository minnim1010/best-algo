package com.ssafy.bestalgo.problem.controller;

import com.ssafy.bestalgo.common.exception.type.AuthenticationFailException;
import com.ssafy.bestalgo.problem.dto.request.ProblemCreateRequest;
import com.ssafy.bestalgo.problem.dto.response.ProblemCreateResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemListResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemSolverListResponse;
import com.ssafy.bestalgo.problem.service.ProblemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/problem")
public class ProblemController {
    private final ProblemService problemService;

    @Value("${admin-password}")
    private String adminPassword;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProblemListResponse getProblemList(){
        return problemService.getProblemList();
    }

    @GetMapping("/{id}/code")
    @ResponseStatus(HttpStatus.OK)
    public ProblemSolverListResponse getSolverList(@PathVariable int id) {
        return problemService.getSolverList(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProblemCreateResponse createProblem(@RequestBody @Valid ProblemCreateRequest problemCreateRequest) {
        if (!adminPassword.equals(problemCreateRequest.password())) {
            throw new AuthenticationFailException();
        }

        return problemService.createProblem(problemCreateRequest);
    }
}
