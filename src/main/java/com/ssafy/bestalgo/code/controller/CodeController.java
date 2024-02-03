package com.ssafy.bestalgo.code.controller;

import com.ssafy.bestalgo.code.dto.request.BestCodeUpdateRequest;
import com.ssafy.bestalgo.code.dto.request.CodeDeleteRequest;
import com.ssafy.bestalgo.code.dto.request.CodeRequest;
import com.ssafy.bestalgo.code.dto.request.CodeSearchRequest;
import com.ssafy.bestalgo.code.dto.response.CodeResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/code")
public class CodeController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CodeResponse getCode(@ModelAttribute("codeSearchRequest") CodeSearchRequest codeSearchRequest) {
        // TODO: 2024/02/02 search code
        return new CodeResponse();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CodeResponse createCode(@RequestBody @Valid CodeRequest codeRequest) {
        // TODO: 2024/02/02 create code
        return new CodeResponse();
    }

    @PostMapping("/best")
    @ResponseStatus(HttpStatus.OK)
    public void updateBestCode(@RequestParam("problem") int problemId,
                               @RequestBody @Valid BestCodeUpdateRequest bestCodeUpdateRequest) {
        // TODO: 2024/02/03 update best code
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CodeResponse updateCode(@PathVariable("id") int codeId,
                                   @RequestBody @Valid CodeRequest codeRequest) {
        // TODO: 2024/02/02 update code
        return new CodeResponse();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCode(@PathVariable("id") int codeId,
                           @RequestBody @Valid CodeDeleteRequest codeDeleteRequest) {
        // TODO: 2024/02/02 delete code
    }
}
