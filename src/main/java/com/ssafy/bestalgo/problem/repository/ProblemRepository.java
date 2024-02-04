package com.ssafy.bestalgo.problem.repository;

import com.ssafy.bestalgo.problem.dto.persist.ProblemSubmission;
import com.ssafy.bestalgo.problem.dto.response.CodeListResponse;
import com.ssafy.bestalgo.problem.entity.Problem;
import java.util.List;
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
            p.id, p.name, p.category, COUNT(CASE WHEN c.isDeleted = false then 1 ELSE NULL END))
            FROM Problem p
            LEFT JOIN p.codes c
            GROUP BY p.category, p.name, p.id
            order by p.category"""
    )
    List<ProblemSubmission> findAllWithSubmissionCount();
}
