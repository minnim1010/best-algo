package com.ssafy.bestalgo.domain.problem.service;

import com.ssafy.bestalgo.domain.code.dto.response.CodeResponse;
import com.ssafy.bestalgo.domain.code.entity.CodeType;
import com.ssafy.bestalgo.domain.code.repository.CodeRepository;
import com.ssafy.bestalgo.domain.problem.dto.persist.ProblemSubmission;
import com.ssafy.bestalgo.domain.problem.dto.request.ProblemCreateRequest;
import com.ssafy.bestalgo.domain.problem.dto.response.CodeListResponse;
import com.ssafy.bestalgo.domain.problem.dto.response.ProblemCreateResponse;
import com.ssafy.bestalgo.domain.problem.dto.response.ProblemListResponse;
import com.ssafy.bestalgo.domain.problem.dto.response.ProblemSolvedCodeListResponse;
import com.ssafy.bestalgo.domain.problem.entity.Problem;
import com.ssafy.bestalgo.domain.problem.repository.ProblemRepository;
import com.ssafy.bestalgo.global.exception.type.DataNotExistsYetException;
import com.ssafy.bestalgo.global.exception.type.DuplicatedDataException;
import com.ssafy.bestalgo.global.exception.type.InvalidRequestException;
import java.util.Collections;
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
    private final CodeRepository codeRepository;

    public ProblemService(ProblemRepository problemRepository, CodeRepository codeRepository) {
        this.problemRepository = problemRepository;
        this.codeRepository = codeRepository;
    }

    public ProblemListResponse getProblemList() {
        List<ProblemSubmission> problemSubmissions = problemRepository.findAllWithSubmissionCount();
        return convertProblemListResponse(problemSubmissions);
    }

    private ProblemListResponse convertProblemListResponse(List<ProblemSubmission> problemSubmissions) {
        List<Map<String, Object>> collect = problemSubmissions.stream()
                .collect(Collectors.groupingBy(ProblemSubmission::date))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Collections.reverseOrder()))
                .map(entry ->
                        Map.of("date", entry.getKey(),
                                "problems", entry.getValue().stream()
                                        .map(ProblemSubmission::toResponse)
                                        .toList()))
                .toList();

        return new ProblemListResponse(collect);
    }

    public ProblemSolvedCodeListResponse getCodeListByProblem(int problemId) {
        List<CodeListResponse> codeListResponse = problemRepository.findCodesById(problemId);
        return new ProblemSolvedCodeListResponse(codeListResponse);
    }

    @Transactional
    public ProblemCreateResponse createProblem(ProblemCreateRequest request) {
        try {
            Problem save = problemRepository.save(Problem.create(request.name(), request.date()));
            return new ProblemCreateResponse(save.getId(), save.getName(), save.getCategory());
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedDataException("문제가 이미 존재합니다.", e);
        }
    }

    public CodeResponse getCodeByType(int problemId, String type) {
        if (!CodeType.exists(type)) {
            throw new InvalidRequestException(type + " 코드 타입은 존재하지 않습니다.");
        }
        return codeRepository.findByIdAndCodeType(problemId, CodeType.get(type))
                .orElseThrow(DataNotExistsYetException::new);
    }
}
