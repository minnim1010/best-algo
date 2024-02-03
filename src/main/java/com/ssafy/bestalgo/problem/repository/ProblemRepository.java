package com.ssafy.bestalgo.problem.repository;

import com.ssafy.bestalgo.code.dto.response.CodeResponse;
import com.ssafy.bestalgo.code.entity.CodeType;
import com.ssafy.bestalgo.problem.dto.persist.ProblemSubmission;
import com.ssafy.bestalgo.problem.dto.response.CodeListResponse;
import com.ssafy.bestalgo.problem.entity.Problem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {

    @Query("""
            SELECT NEW com.ssafy.bestalgo.problem.dto.response.CodeListResponse(
            c.id, m.name, c.createdTime)
            FROM Code c
            JOIN c.member m
            WHERE c.problem.id = :problemId and c.isDeleted = false""")
    List<CodeListResponse> findCodesById(@Param("problemId") Integer problemId);


    @Query("""
            SELECT NEW com.ssafy.bestalgo.problem.dto.persist.ProblemSubmission(
            p.id, p.name, COUNT(c.id), p.category)
            FROM Code c
            JOIN c.problem p
            WHERE c.isDeleted = false
            GROUP BY p.category, p.name, p.id"""
    )
    List<ProblemSubmission> findAllWithSubmissionCount();

    @Query("""
            SELECT NEW com.ssafy.bestalgo.code.dto.response.CodeResponse(c.id, m.name, c.createdTime, c.content, c.type)
            FROM Code c
            JOIN c.member m
            WHERE c.problem.id = :problemId and c.type = :type and c.isDeleted = false""")
    Optional<CodeResponse> findByIdAndCodeType(int problemId, CodeType type);

    @Query("""
            SELECT EXISTS (
                SELECT 1
                FROM Code c
            JOIN c.member m
            WHERE c.problem.id = :problemId and c.type = :type and c.isDeleted = false
            ) AS result""")
    boolean existsByIdAndCodeType(int problemId, CodeType type);
}
