package com.ssafy.bestalgo.code.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.ssafy.bestalgo.code.entity.Code;
import com.ssafy.bestalgo.code.entity.CodeType;
import com.ssafy.bestalgo.member.entity.Member;
import com.ssafy.bestalgo.member.repository.MemberRepository;
import com.ssafy.bestalgo.problem.entity.Problem;
import com.ssafy.bestalgo.problem.repository.ProblemRepository;
import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CodeRepositoryTest {
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProblemRepository problemRepository;

    @Test
    void create() {
        //given
        Member member = Member.create("member", "password");
        Problem problem = Problem.create("problem1", "20240202");
        Code code = Code.create(member, problem, "codecodecode", CodeType.BEST);

        memberRepository.save(member);
        problemRepository.save(problem);
        //when
        codeRepository.save(code);
        //then
        List<Code> result = codeRepository.findAll();

        assertThat(result).hasSize(1)
                .extracting("content", "type", "isDeleted")
                .contains(Tuple.tuple(code.getContent(), code.getType(), code.getDeleted()));
    }
}