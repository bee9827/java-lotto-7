package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.domain.WinningRank;

import java.util.*;
import java.util.function.Predicate;

public class LottoMachine {
    private List<Lotto> lottos;
    private WinningLotto winningLotto;

    public List<Lotto> buyLotto(int money) {
        return getLottos(money / Lotto.LOTTO_PRICE);
    }

    public Lotto getLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(Lotto.MIN, Lotto.MAX, Lotto.SIZE);
        return new Lotto(numbers.stream().sorted().toList());
    }

    public List<Lotto> getLottos(int n) {
        lottos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lottos.add(getLotto());
        }
        return Collections.unmodifiableList(lottos);
    }

    public void setWinningLotto(WinningLotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public Map<WinningRank, Integer> getRankWithCount() {
        validateWinningLotto();
        EnumMap<WinningRank, Integer> rankWithCount = new EnumMap<>(WinningRank.class);

        for (WinningRank rank : WinningRank.values()) {
            rankWithCount.put(rank, getRankCount(r -> r.equals(rank)));
        }
        return rankWithCount;
    }

    private void validateWinningLotto() {
        if (winningLotto == null) throw new IllegalStateException("우승 로또가 없습니다.");
    }

    private Integer getRankCount(Predicate predicate) {
        return (int) lottos.stream()
                .map(lotto -> WinningRank.getRank(winningLotto.getMatchCount(lotto), winningLotto.isMatchBonusBall(lotto)))
                .filter(predicate::equals)
                .count();
    }

}
