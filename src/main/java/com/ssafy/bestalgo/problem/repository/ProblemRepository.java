package com.ssafy.bestalgo.problem.repository;

import com.ssafy.bestalgo.problem.dto.persist.ProblemSubmission;
import com.ssafy.bestalgo.problem.entity.Problem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {

    @Query("""
            SELECT NEW com.ssafy.bestalgo.problem.dto.persist.ProblemSubmission(
            p.id, p.name, COUNT(c.id), p.category)
            FROM Code c
            JOIN c.problem p
            WHERE c.isDeleted = false
            GROUP BY p.category, p.name, p.id"""
    )
    List<ProblemSubmission> findAllWithSubmissionCount();
}
