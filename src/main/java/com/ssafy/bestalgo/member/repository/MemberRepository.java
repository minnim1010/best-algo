package com.ssafy.bestalgo.member.repository;

import com.ssafy.bestalgo.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
