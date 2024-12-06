package lotto;

import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {
    @Test
    @DisplayName("중복된 보너스 번호는 예외를 발생 시킨다.")
    void constructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WinningLotto(List.of(1,2,3,4,5,6),6);
        });
    }

}