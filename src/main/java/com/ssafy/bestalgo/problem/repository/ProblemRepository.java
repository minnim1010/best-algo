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
            p.id, p.name, COUNT(c.id), p.category)
            FROM Problem p
            LEFT JOIN p.codes c
            WHERE c.isDeleted = false or c.isDeleted is null
            GROUP BY p.category, p.name, p.id"""
    )
    List<ProblemSubmission> findAllWithSubmissionCount();


}
