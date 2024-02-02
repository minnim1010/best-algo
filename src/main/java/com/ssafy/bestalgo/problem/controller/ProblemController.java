package com.ssafy.bestalgo.problem.controller;

import com.ssafy.bestalgo.problem.dto.request.ProblemCreateRequest;
import com.ssafy.bestalgo.problem.dto.response.ProblemCreateResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemListResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemSolverListResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemSolverListResponse.ProblemSolverResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemSubmissionListResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemSubmissionResponse;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/problem")
public class ProblemController {

    @GetMapping
    public ProblemListResponse getProblemList(){
        // TODO: 2024/02/03 get problem list 
        ProblemListResponse problemListResponse = new ProblemListResponse();
        ProblemSubmissionListResponse problemSubmissionListResponse = new ProblemSubmissionListResponse();
        problemSubmissionListResponse.setProblems(List.of(new ProblemSubmissionResponse(), new ProblemSubmissionResponse()));
        problemListResponse.setItems(List.of(problemSubmissionListResponse));
        return problemListResponse;
    }

    @GetMapping("/{id}/solvers")
    public ProblemSolverListResponse getSolverList(@PathVariable int id) {
        // TODO: 2024/02/03 get solver list
        List<ProblemSolverResponse> list = new ArrayList<>();
        list.add(new ProblemSolverListResponse.ProblemSolverResponse());
        ProblemSolverListResponse problemSolverListResponse = new ProblemSolverListResponse();
        problemSolverListResponse.setSolvers(list);
        return problemSolverListResponse;
    }

    @PostMapping
    public ProblemCreateResponse createProblem(@RequestBody @Valid ProblemCreateRequest problemCreateRequest) {
        // TODO: 2024/02/03 create problem
        return new ProblemCreateResponse();
    }
}
