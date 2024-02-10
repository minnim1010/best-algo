package com.ssafy.bestalgo.domain.problem.controller;

import com.ssafy.bestalgo.domain.code.dto.response.CodeResponse;
import com.ssafy.bestalgo.domain.problem.dto.request.ProblemCreateRequest;
import com.ssafy.bestalgo.domain.problem.dto.response.ProblemCreateResponse;
import com.ssafy.bestalgo.domain.problem.dto.response.ProblemListResponse;
import com.ssafy.bestalgo.domain.problem.dto.response.ProblemSolvedCodeListResponse;
import com.ssafy.bestalgo.domain.problem.service.ProblemService;
import com.ssafy.bestalgo.global.exception.type.AuthenticationFailException;
import com.ssafy.bestalgo.global.exception.type.InvalidRequestException;
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
@RequestMapping("/api/v1/problems")
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

    @GetMapping("{id}/codes")
    @ResponseStatus(HttpStatus.OK)
    public ProblemSolvedCodeListResponse getCodeListByProblem(@PathVariable("id") int problemId) {
        return problemService.getCodeListByProblem(problemId);
    }

    @GetMapping("{id}/{type}")
    @ResponseStatus(HttpStatus.OK)
    public CodeResponse getCodeByType(@PathVariable("id") int problemId,
                                      @PathVariable("type") String type) {
        if (!"best".equals(type) && !"novel".equals(type)) {
            throw new InvalidRequestException();
        }

        return problemService.getCodeByType(problemId, type);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProblemCreateResponse createProblem(@RequestBody @Valid ProblemCreateRequest request) {
        if (!adminPassword.equals(request.password())) {
            throw new AuthenticationFailException();
        }

        return problemService.createProblem(request);
    }
}
