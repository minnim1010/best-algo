package com.ssafy.bestalgo.code.service;

import com.ssafy.bestalgo.code.dto.request.BestCodeUpdateRequest;
import com.ssafy.bestalgo.code.dto.request.CodeDeleteRequest;
import com.ssafy.bestalgo.code.dto.request.CodeRequest;
import com.ssafy.bestalgo.code.dto.request.CodeSearchRequest;
import com.ssafy.bestalgo.code.dto.response.CodeResponse;
import com.ssafy.bestalgo.code.entity.CodeType;
import com.ssafy.bestalgo.code.repository.CodeRepository;
import com.ssafy.bestalgo.common.exception.type.DataNotFoundException;
import com.ssafy.bestalgo.common.exception.type.InvalidRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CodeService {
    private final CodeRepository codeRepository;

    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
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
    public CodeResponse createCode(CodeRequest codeRequest) {
        // TODO: 2024/02/02 create code
        return new CodeResponse(0, null, null, null, null);
    }

    @Transactional
    public void updateCodeType(int problemId, BestCodeUpdateRequest bestCodeUpdateRequest) {
        // TODO: 2024/02/03 update best code
    }

    @Transactional
    public CodeResponse updateCode(int codeId, CodeRequest codeRequest) {
        // TODO: 2024/02/02 update code
        return new CodeResponse(0, (String) null, null, null, null);
    }

    @Transactional
    public void deleteCode(int codeId, CodeDeleteRequest codeDeleteRequest) {
        // TODO: 2024/02/02 delete code
    }
}
