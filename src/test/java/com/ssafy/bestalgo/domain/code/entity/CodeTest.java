package com.ssafy.bestalgo.domain.code.entity;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import com.ssafy.bestalgo.domain.member.entity.Member;
import com.ssafy.bestalgo.domain.problem.entity.Problem;
import com.ssafy.bestalgo.global.util.PasswordEncoder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class CodeTest {

    @Nested
    class 코드_생성시 {

        @Test
        void 작성자가_널이라면_예외가_발생한다() {
            //given
            Member member = null;
            Problem problem = Problem.create("problem", "category");
            String content = "content";
            CodeType codeType = CodeType.BEST;
            //when then
            assertThatNullPointerException().isThrownBy(
                    () -> Code.create(member, problem, content, codeType));
        }

        @Test
        void 문제가_널이라면_예외가_발생한다() {
            //given
            Member member = Member.create("name", PasswordEncoder.encode("password"));
            Problem problem = null;
            String content = "content";
            CodeType codeType = CodeType.BEST;
            //when then
            assertThatNullPointerException().isThrownBy(
                    () -> Code.create(member, problem, content, codeType));
        }

        @CsvFileSource(resources = "/test-data/code_content.csv", lineSeparator = "\\",
                numLinesToSkip = 1, maxCharsPerColumn = 20010)
        @ParameterizedTest
        void 코드내용이_공백이거나_1자이상_20000자이하가_아니라면_예외가_발생한다(String content) {
            //given
            Member member = Member.create("name", PasswordEncoder.encode("password"));
            Problem problem = Problem.create("problem", "category");
            CodeType codeType = CodeType.BEST;
            //when then
            assertThatIllegalArgumentException().isThrownBy(
                    () -> Code.create(member, problem, content, codeType));
        }

        @Test
        void 코드타입이_널이라면_예외가_발생한다() {
            //given
            Member member = Member.create("name", PasswordEncoder.encode("password"));
            Problem problem = Problem.create("problem", "category");
            String content = "content";
            CodeType codeType = null;
            //when then
            assertThatNullPointerException().isThrownBy(
                    () -> Code.create(member, problem, content, codeType));
        }

        @Test
        void 성공적으로_생성한다() {
            //given
            Member member = Member.create("name", PasswordEncoder.encode("password"));
            Problem problem = Problem.create("problem", "category");
            String content = "content";
            CodeType codeType = CodeType.BEST;
            //when then
            assertThatNoException().isThrownBy(
                    () -> Code.create(member, problem, content, codeType));
        }
    }
}