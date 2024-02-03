package com.ssafy.bestalgo.code.repository;

import com.ssafy.bestalgo.code.dto.response.CodeResponse;
import com.ssafy.bestalgo.code.entity.Code;
import com.ssafy.bestalgo.member.entity.Member;
import com.ssafy.bestalgo.problem.entity.Problem;
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
            WHERE c.id = :codeId""")
    Optional<CodeResponse> getResponseById(int codeId);

    Optional<Code> findByProblemAndMember(Problem problem, Member member);

    @Query("""
            SELECT EXISTS (
                SELECT 1
                FROM Code c
                JOIN c.member m
                WHERE c.id = :codeId and c.isDeleted = false and m.name = :solverName AND m.password = :solverPassword
            ) AS result""")
    boolean existsByIdAndSolverNameAndSolverPassword(int codeId, String solverName, String solverPassword);

    boolean existsByIdAndIsDeletedFalse(int codeId);

    Optional<Code> findByIdAndIsDeletedFalse(int codeId);

    Optional<Code> findByProblemAndMemberAndIsDeletedFalse(Problem problem, Member member);
}
