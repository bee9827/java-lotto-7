package lotto;

import lotto.service.LottoMachine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.junit.jupiter.api.Assertions.*;

class LottoMachineTest {

    @DisplayName("로또 발급 번호 정렬에 성공한다.")
    @Test
    void getLotto() {
        List<Integer> numbers = List.of(6, 5, 4, 3, 2, 1);
        LottoMachine lottoMachine = new LottoMachine();
        assertRandomUniqueNumbersInRangeTest(() -> {
                    assertEquals(List.of(1, 2, 3, 4, 5, 6), lottoMachine.getLotto().getNumbers());
                }, numbers
        );
    }

    @DisplayName("로또 구매")
    @ParameterizedTest
    @CsvSource({"10000,10","200000,200"})
    void buyLotto(int price, int count){
        LottoMachine lottoMachine = new LottoMachine();
        assertEquals(count,lottoMachine.buyLotto(price).size());
    }
}