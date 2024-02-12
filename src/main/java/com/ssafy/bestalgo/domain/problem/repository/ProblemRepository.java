package com.ssafy.bestalgo.domain.problem.repository;

import com.ssafy.bestalgo.domain.problem.dto.persist.ProblemSubmission;
import com.ssafy.bestalgo.domain.problem.entity.Problem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {

    @Query("""
            SELECT NEW com.ssafy.bestalgo.domain.problem.dto.persist.ProblemSubmission(
            p.id, p.name, p.category, COUNT(CASE WHEN c.isDeleted = false then 1 ELSE NULL END))
            FROM Problem p
            LEFT JOIN p.codes c
            GROUP BY p.category, p.name, p.id
            order by p.category"""
    )
    List<ProblemSubmission> getResponseAllWithSubmissionCount();
}
