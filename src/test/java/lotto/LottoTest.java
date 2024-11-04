package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    static final Lotto winner = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    static final List<List<Integer>> rangeExceptionNumbers = List.of(
            List.of(1, 2, 3, 4, 5, Lotto.NUMBER_MAX + 1),
            List.of(1, 2, 3, 4, 5, Lotto.NUMBER_MIN -1)
    );
    static final List<List<Integer>> sizeExceptionNumbers = List.of(
            List.of(1, 2, 3, 4, 5),
            List.of(1, 2, 3, 4, 5, 6, 7)
    );

    @ParameterizedTest
    @FieldSource("sizeExceptionNumbers")
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @FieldSource("rangeExceptionNumbers")
    void 번호_범위_예외(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 번호_출력() {
        assertThat(winner.toString()).contains("[1, 2, 3, 4, 5, 6]");
    }

    @Test
    void 번호_정렬() {
        assertThat(new Lotto(List.of(5, 4, 3, 2, 1, 6)).toString()).contains("[1, 2, 3, 4, 5, 6]");
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
}
