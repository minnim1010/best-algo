package com.ssafy.bestalgo.problem.repository;

import com.ssafy.bestalgo.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {
}
