package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.domain.WinningRank;

import java.util.*;

public class LottoMachine {
    private List<Lotto> lottos;
    private WinningLotto winningLotto;

    public List<String> lottosToString(){
        return lottos.stream().map(Lotto::toString).toList();
    }

    public Long getTotalPrize(){
        return getRankWithCount().entrySet()
                .stream()
                .map(e -> (long)e.getKey().getPrice() * e.getValue())
                .reduce(Long::sum)
                .orElse(0L);
    }

    public List<Lotto> buyLotto(int money) {
        validateMoney(money);
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
            rankWithCount.put(rank, getRankCount(rank));
        }
        return rankWithCount;
    }

    public float getProfitRate(){
        return Math.round((float) getTotalPrize() / (Lotto.LOTTO_PRICE * lottos.size()) * 1000)/10.0f;
    }

    private void validateMoney(int money) {
        if (money % Lotto.LOTTO_PRICE != 0)
            throw new IllegalArgumentException(String.format("돈 단위는 %,d원 단위로 입력해 주세요.", Lotto.LOTTO_PRICE));
    }

    private void validateWinningLotto() {
        if (winningLotto == null) throw new IllegalStateException("우승 로또가 없습니다.");
    }

    private Integer getRankCount(WinningRank rank) {
        return (int) lottos.stream()
                .map(lotto -> WinningRank.getRank(winningLotto.getMatchCount(lotto), winningLotto.isMatchBonusBall(lotto)))
                .filter(rank::equals)
                .count();
    }

}
