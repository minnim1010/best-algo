package com.ssafy.bestalgo.code.repository;

import com.ssafy.bestalgo.code.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, Integer> {
}
