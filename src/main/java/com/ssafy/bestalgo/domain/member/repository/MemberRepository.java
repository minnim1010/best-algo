package com.ssafy.bestalgo.domain.member.repository;

import com.ssafy.bestalgo.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
