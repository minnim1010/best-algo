package com.ssafy.bestalgo.problem.service;

import com.ssafy.bestalgo.common.exception.type.DuplicatedDataException;
import com.ssafy.bestalgo.problem.dto.persist.ProblemSubmission;
import com.ssafy.bestalgo.problem.dto.request.ProblemCreateRequest;
import com.ssafy.bestalgo.problem.dto.response.ProblemCreateResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemListResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemSolvedCodeResponse;
import com.ssafy.bestalgo.problem.dto.response.ProblemSolverListResponse;
import com.ssafy.bestalgo.problem.entity.Problem;
import com.ssafy.bestalgo.problem.repository.ProblemRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
        List<ProblemSubmission> problemSubmissions = problemRepository.findAllWithSubmissionCount();

        return convertProblemListResponse(problemSubmissions);
    }

    private ProblemListResponse convertProblemListResponse(List<ProblemSubmission> problemSubmissions) {
        List<Map<String, Object>> collect = problemSubmissions.stream()
                .collect(Collectors.groupingBy(ProblemSubmission::date))
                .entrySet().stream()
                .map(entry ->
                        Map.of(
                                "date", entry.getKey(),
                                "problems", entry.getValue().stream()
                                        .map(ProblemSubmission::toMap)
                                        .toList()))
                .toList();

        return new ProblemListResponse(collect);
    }

    public ProblemSolverListResponse getSolverList(int problemId) {
        List<ProblemSolvedCodeResponse> problemSolvedCodeResponseList = problemRepository.findSolverByProblem(
                problemId);
        return new ProblemSolverListResponse(problemSolvedCodeResponseList);
    }

    @Transactional
    public ProblemCreateResponse createProblem(ProblemCreateRequest request) {
        try {
            Problem save = problemRepository.save(Problem.create(request.name(), request.date()));
            return new ProblemCreateResponse(save.getId(), save.getName(), save.getCategory());
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedDataException();
        }
    }
}
