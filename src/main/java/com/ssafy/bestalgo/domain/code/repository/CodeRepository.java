package com.ssafy.bestalgo.domain.code.repository;

import com.ssafy.bestalgo.domain.code.dto.response.CodeResponse;
import com.ssafy.bestalgo.domain.code.entity.Code;
import com.ssafy.bestalgo.domain.code.entity.CodeType;
import com.ssafy.bestalgo.domain.problem.dto.response.CodeListResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CodeRepository extends JpaRepository<Code, Integer> {

    @Query("""
            SELECT NEW com.ssafy.bestalgo.domain.problem.dto.response.CodeListResponse(
            c.id, m.name, c.createdTime)
            FROM Code c
            JOIN c.member m
            WHERE c.problem.id = :problemId and c.isDeleted = false""")
    List<CodeListResponse> getResponseAllByProblemId(@Param("problemId") Integer problemId);

    @Query("""
            SELECT NEW com.ssafy.bestalgo.domain.code.dto.response.CodeResponse(
            c.id, m.name, c.createdTime, c.content, c.type)
            FROM Code c
            JOIN c.member m
            WHERE c.id = :codeId and c.isDeleted = false""")
    Optional<CodeResponse> getResponseById(int codeId);

    @Query("""
            SELECT EXISTS (
                SELECT 1
                FROM Code c
                JOIN c.member m
                WHERE c.id = :codeId and c.isDeleted = false and m.name = :solverName
                AND m.password = :solverPassword
            ) AS result""")
    boolean existsByIdAndSolverNameAndSolverPassword(int codeId, String solverName, String solverPassword);

    boolean existsByIdAndIsDeletedFalse(int codeId);

    Optional<Code> findByIdAndIsDeletedFalse(int codeId);

    @Query("""
            SELECT NEW com.ssafy.bestalgo.domain.code.dto.response.CodeResponse(
            c.id, m.name, c.createdTime, c.content, c.type)
            FROM Code c
            JOIN c.member m
            WHERE c.problem.id = :problemId and c.type = :type and c.isDeleted = false""")
    Optional<CodeResponse> getResponseByIdAndCodeType(int problemId, CodeType type);

    Optional<Code> findByProblemIdAndTypeAndIsDeletedFalse(int problemId, CodeType type);
}
