package com.ssafy.bestalgo.domain.problem.entity;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProblemTest {

    @Nested
    class 문제_생성시 {

        @ValueSource(strings = {"", " ", "namenamenamenamenamenamenamenam"})
        @ParameterizedTest
        void 문제이름이_공백이거나_1자이상_30자이하가_아니라면_예외가_발생한다(String name) {
            //given
            String category = "category";
            //when then
            assertThatIllegalArgumentException().isThrownBy(() -> Problem.create(name, category));
        }

        @ValueSource(strings = {"", " ", "categorycategorycategorycategor"})
        @ParameterizedTest
        void 문제카테고리가_공백이거나_1자이상_30자이하가_아니라면_예외가_발생한다(String category) {
            //given
            String name = "name";
            //when then
            assertThatIllegalArgumentException().isThrownBy(() -> Problem.create(name, category));
        }

        @Test
        void 성공적으로_생성한다() {
            //given
            String name = "problemproblemproblemproblempr";
            String category = "categorycategorycategorycatego";
            //when then
            assertThatNoException().isThrownBy(() -> Problem.create(name, category));
        }
    }
}