package com.ssafy.bestalgo.problem.repository;

import com.ssafy.bestalgo.problem.dto.response.ProblemSolverResponse;
import com.ssafy.bestalgo.problem.entity.Problem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {

    @Query("""
            SELECT NEW com.ssafy.bestalgo.problem.dto.response.ProblemSolverResponse(
            m.name, c.createdTime)
            FROM Code c
            JOIN c.member m
            WHERE c.problem.id = :problemId""")
    List<ProblemSolverResponse> findSolverByProblem(@Param("problemId") Integer problemId);
}
