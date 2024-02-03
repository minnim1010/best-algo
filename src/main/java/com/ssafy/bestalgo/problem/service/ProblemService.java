package com.ssafy.bestalgo.problem.service;

import com.ssafy.bestalgo.common.exception.type.DuplicatedDataException;
import com.ssafy.bestalgo.problem.dto.request.ProblemCreateRequest;
import com.ssafy.bestalgo.problem.dto.response.ProblemCreateResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemListResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemSolverListResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemSolverResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemSubmissionListResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemSubmissionResponse;
import com.ssafy.bestalgo.problem.entity.Problem;
import com.ssafy.bestalgo.problem.repository.ProblemRepository;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProblemService {
    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public ProblemListResponse getProblemList() {
        // TODO: 2024/02/03 get problem list

        ProblemListResponse problemListResponse = new ProblemListResponse();
        ProblemSubmissionListResponse problemSubmissionListResponse = new ProblemSubmissionListResponse();
        problemSubmissionListResponse.setProblems(
                List.of(new ProblemSubmissionResponse(), new ProblemSubmissionResponse()));
        problemListResponse.setItems(List.of(problemSubmissionListResponse));
        return problemListResponse;
    }

    public ProblemSolverListResponse getSolverList(int problemId) {
        List<ProblemSolverResponse> problemSolverResponseList = problemRepository.findSolverByProblem(problemId);
        return new ProblemSolverListResponse(problemSolverResponseList);
    }

    @Transactional
    public ProblemCreateResponse createProblem(ProblemCreateRequest problemCreateRequest) {
        Problem problem = Problem.create(problemCreateRequest.getName(), problemCreateRequest.getDate());

        // TODO: 2024/02/03 problemName 중복된 경우 if문 예외 처리 필요
        try {
            Problem save = problemRepository.save(problem);
            return new ProblemCreateResponse(save.getId(), save.getName(), save.getCategory());
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedDataException();
        }
    }
}
