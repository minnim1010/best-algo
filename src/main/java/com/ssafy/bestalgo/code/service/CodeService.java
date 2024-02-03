package com.ssafy.bestalgo.code.service;

import com.ssafy.bestalgo.code.dto.request.BestCodeUpdateRequest;
import com.ssafy.bestalgo.code.dto.request.CodeDeleteRequest;
import com.ssafy.bestalgo.code.dto.request.CodeRequest;
import com.ssafy.bestalgo.code.dto.response.CodeResponse;
import com.ssafy.bestalgo.code.entity.Code;
import com.ssafy.bestalgo.code.entity.CodeType;
import com.ssafy.bestalgo.code.repository.CodeRepository;
import com.ssafy.bestalgo.common.exception.type.AuthenticationFailException;
import com.ssafy.bestalgo.common.exception.type.DataNotFoundException;
import com.ssafy.bestalgo.common.exception.type.DuplicatedDataException;
import com.ssafy.bestalgo.common.exception.type.InvalidRequestException;
import com.ssafy.bestalgo.member.entity.Member;
import com.ssafy.bestalgo.member.repository.MemberRepository;
import com.ssafy.bestalgo.problem.entity.Problem;
import com.ssafy.bestalgo.problem.repository.ProblemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CodeService {
    private final CodeRepository codeRepository;
    private final ProblemRepository problemRepository;
    private final MemberRepository memberRepository;

    public CodeService(CodeRepository codeRepository, ProblemRepository problemRepository,
                       MemberRepository memberRepository) {
        this.codeRepository = codeRepository;
        this.problemRepository = problemRepository;
        this.memberRepository = memberRepository;
    }

    public CodeResponse getCode(int codeId) {
        return codeRepository.getResponseById(codeId)
                .orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    public CodeResponse createCode(int problemId, CodeRequest request) {
        Member member = memberRepository.save(Member.create(request.solver(), request.password()));
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new DataNotFoundException(problemId + "번에 해당하는 문제가 존재하지 않습니다."));

        Code code = codeRepository.save(Code.create(member, problem, request.content(), CodeType.GOOD));
        return new CodeResponse(code.getId(), member.getName(), code.getCreatedTime(),
                code.getContent(), code.getType());
    }

    @Transactional
    public void updateCodeType(BestCodeUpdateRequest request) {
        String type = request.type();
        if (!CodeType.exists(type)) {
            throw new InvalidRequestException(type + " 코드 타입은 존재하지 않습니다.");
        }

        // TODO: 2024/02/03 jpql로 리팩토링 예정
        if (!problemRepository.existsById(request.problem())) {
            throw new DataNotFoundException(request.problem() + "번에 해당하는 문제를 찾을 수 없습니다.");
        }
        Code code = codeRepository.findByIdAndIsDeletedFalse(request.code())
                .orElseThrow(() -> new DataNotFoundException("해당 코드를 찾을 수 없습니다."));

        if (codeRepository.existsByIdAndCodeType(request.problem(), CodeType.get(type))) {
            throw new DuplicatedDataException(type + " 타입의 코드가 이미 존재합니다.");
        }

        code.updateType(CodeType.get(type));
    }

    @Transactional
    public CodeResponse updateCode(int codeId, CodeRequest request) {
        if (!codeRepository.existsByIdAndIsDeletedFalse(codeId)) {
            throw new DataNotFoundException("해당 코드를 찾을 수 없습니다.");
        }

        if (!codeRepository.existsByIdAndSolverNameAndSolverPassword(
                codeId, request.solver(), request.password())) {
            throw new AuthenticationFailException();
        }

        Code code = codeRepository.findByIdAndIsDeletedFalse(codeId)
                .orElseThrow(() -> new DataNotFoundException("해당 코드를 찾을 수 없습니다."));
        code.updateContent(request.content());

        return new CodeResponse(code.getId(), request.solver(), code.getCreatedTime(),
                code.getContent(), code.getType());
    }

    @Transactional
    public void deleteCode(int codeId, CodeDeleteRequest request) {
        if (!codeRepository.existsByIdAndIsDeletedFalse(codeId)) {
            throw new DataNotFoundException();
        }

        if (!codeRepository.existsByIdAndSolverNameAndSolverPassword(
                codeId, request.solver(), request.password())) {
            throw new AuthenticationFailException();
        }

        Code code = codeRepository.findByIdAndIsDeletedFalse(codeId)
                .orElseThrow(DataNotFoundException::new);
        code.setDeleted();
    }
}
