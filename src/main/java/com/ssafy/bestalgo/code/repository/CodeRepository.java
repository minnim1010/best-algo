package com.ssafy.bestalgo.code.repository;

import com.ssafy.bestalgo.code.dto.response.CodeResponse;
import com.ssafy.bestalgo.code.entity.Code;
import com.ssafy.bestalgo.code.entity.CodeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CodeRepository extends JpaRepository<Code, Integer> {

    @Query("""
            SELECT NEW com.ssafy.bestalgo.code.dto.response.CodeResponse(c.id, m.name, c.createdTime, c.content, c.type)
            FROM Code c
            JOIN c.member m
            WHERE c.problem.id = :problemId and m.name = :solverName and c.isDeleted = false""")
    Optional<CodeResponse> findByProblemIdAndSolverName(int problemId, String solverName);

    @Query("""
            SELECT NEW com.ssafy.bestalgo.code.dto.response.CodeResponse(c.id, m.name, c.createdTime, c.content, c.type)
            FROM Code c
            JOIN c.member m
            WHERE c.problem.id = :problemId and c.type = :type and c.isDeleted = false""")
    Optional<CodeResponse> findByProblemIdAndType(int problemId, CodeType type);
}
