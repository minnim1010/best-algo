package com.ssafy.bestalgo.code.controller;

import com.ssafy.bestalgo.code.dto.request.BestCodeUpdateRequest;
import com.ssafy.bestalgo.code.dto.request.CodeDeleteRequest;
import com.ssafy.bestalgo.code.dto.request.CodeRequest;
import com.ssafy.bestalgo.code.dto.response.CodeResponse;
import com.ssafy.bestalgo.code.service.CodeService;
import com.ssafy.bestalgo.common.exception.type.AuthenticationFailException;
import com.ssafy.bestalgo.common.exception.type.InvalidRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/codes")
public class CodeController {
    private final CodeService codeService;

    @Value("${admin-password}")
    private String adminPassword;
    @Value("${algorithm-manager-password}")
    private String algorithmManagerPassword;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CodeResponse getCode(@PathVariable("id") int codeId) {
        return codeService.getCode(codeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CodeResponse createCode(@RequestParam("problem") int problemId,
                                   @RequestBody @Valid CodeRequest request) {
        return codeService.createCode(problemId, request);
    }

    @PostMapping("/type")
    @ResponseStatus(HttpStatus.OK)
    public void updateCodeType(@RequestBody @Valid BestCodeUpdateRequest request) {
        String password = request.password();
        if (!adminPassword.equals(password) && !algorithmManagerPassword.equals(password)) {
            throw new AuthenticationFailException();
        }

        if (!request.type().equals("best") && !request.type().equals("novel")) {
            throw new InvalidRequestException();
        }

        codeService.updateCodeType(request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CodeResponse updateCode(@PathVariable("id") int codeId,
                                   @RequestBody @Valid CodeRequest request) {
        return codeService.updateCode(codeId, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCode(@PathVariable("id") int codeId,
                           @RequestBody @Valid CodeDeleteRequest request) {
        codeService.deleteCode(codeId, request);
    }
}
