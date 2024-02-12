package com.ssafy.bestalgo.domain.member.entity;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import com.ssafy.bestalgo.global.util.PasswordEncoder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MemberTest {

    @Nested
    class 코드작성자_생성시 {

        @ValueSource(strings = {"", " ", "abcdefghijk"})
        @ParameterizedTest
        void 작성자이름이_공백이거나_1자이상_10자이하가_아니라면_예외가_발생한다(String name) {
            //given
            String password = PasswordEncoder.encode("password");
            //when then
            assertThatIllegalArgumentException().isThrownBy(() -> Member.create(name, password));
        }

        @ValueSource(strings = {"", " ", "password"})
        @ParameterizedTest
        void 작성자비밀번호가_공백이거나_sha256인코딩이_되지않았다면_예외가_발생한다(String password) {
            //given
            String name = "name";
            //when then
            assertThatIllegalArgumentException().isThrownBy(() -> Member.create(name, password));
        }

        @Test
        void 성공적으로_생성한다() {
            //given
            String name = "minjiminji";
            String password = PasswordEncoder.encode("password");
            //when then
            assertThatNoException().isThrownBy(() -> Member.create(name, password));
        }
    }
}