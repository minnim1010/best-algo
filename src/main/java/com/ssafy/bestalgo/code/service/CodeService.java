package com.ssafy.bestalgo.code.service;

import com.ssafy.bestalgo.code.dto.request.BestCodeUpdateRequest;
import com.ssafy.bestalgo.code.dto.request.CodeDeleteRequest;
import com.ssafy.bestalgo.code.dto.request.CodeRequest;
import com.ssafy.bestalgo.code.dto.request.CodeSearchRequest;
import com.ssafy.bestalgo.code.dto.response.CodeResponse;
import com.ssafy.bestalgo.code.entity.Code;
import com.ssafy.bestalgo.code.entity.CodeType;
import com.ssafy.bestalgo.code.repository.CodeRepository;
import com.ssafy.bestalgo.common.exception.type.DataNotFoundException;
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

    public CodeResponse getCode(CodeSearchRequest request) {
        if (request.type() != null) {
            return getCodeByType(request);
        }
        return codeRepository.findByProblemIdAndSolverName(request.problem(), request.solver())
                .orElseThrow(DataNotFoundException::new);
    }

    private CodeResponse getCodeByType(CodeSearchRequest request) {
        if (!CodeType.exists(request.type())) {
            throw new InvalidRequestException(request.type() + " 코드 타입은 존재하지 않습니다.");
        }
        return codeRepository.findByProblemIdAndType(request.problem(), CodeType.get(request.type()))
                .orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    public CodeResponse createCode(CodeRequest request) {
        // TODO: 2024/02/02 create code
        return new CodeResponse(0, null, null, null, null);
    }

    @Transactional
    public void updateCodeType(BestCodeUpdateRequest request) {
        if (!CodeType.exists(request.type())) {
            throw new InvalidRequestException(request.type() + " 코드 타입은 존재하지 않습니다.");
        }

        // TODO: 2024/02/03 jpql로 리팩토링 예정 
        Problem problem = problemRepository.findById(request.problem())
                .orElseThrow(() -> new DataNotFoundException(request.problem() + " 해당 문제가 존재하지 않습니다."));
        Member member = memberRepository.findByName(request.solver())
                .orElseThrow(() -> new DataNotFoundException(request.solver() + " 해당 풀이자가 존재하지 않습니다."));
        Code code = codeRepository.findByProblemAndMember(problem, member)
                .orElseThrow(() -> new DataNotFoundException("해당 코드를 찾을 수 없습니다."));

        if (!CodeType.exists(request.type())) {
            throw new InvalidRequestException(request.type() + " 코드 타입은 존재하지 않습니다.");
        }

        code.updateType(CodeType.get(request.type()));
    }

    @Transactional
    public CodeResponse updateCode(int codeId, CodeRequest request) {
        // TODO: 2024/02/02 update code
        return new CodeResponse(0, (String) null, null, null, null);
    }

    @Transactional
    public void deleteCode(int codeId, CodeDeleteRequest codeDeleteRequest) {
        // TODO: 2024/02/02 delete code
    }
}
