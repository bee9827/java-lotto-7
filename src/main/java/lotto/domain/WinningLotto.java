package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {
    private final Integer bonusBall;

    public WinningLotto(List<Integer> numbers, Integer bonusBall) {
        super(numbers);
        validateDuplicate(numbers, bonusBall);
        this.bonusBall = bonusBall;
    }

    public Integer getMatchCount(Lotto lotto) {
        return super.matchCount(lotto);
    }

    public boolean isMatchBonusBall(Lotto lotto) {
        return lotto.isMatch(bonusBall);
    }

    private void validateDuplicate(List<Integer> numbers, Integer bonusBall) {
        if (numbers.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼은 중복 될수 없습니다.");
        }
    }
}
