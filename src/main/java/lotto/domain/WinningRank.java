package lotto.domain;

import java.util.Objects;

public enum WinningRank {
    FIRST("1등", 6, Boolean.FALSE, 2_000_000_000),
    SECOND("2등", 5, Boolean.TRUE, 30_000_000),
    THIRD("3등", 5, Boolean.FALSE, 1_500_000),
    FOURTH("4등", 4, Boolean.FALSE, 50_000),
    FIFTH("5등", 3, Boolean.FALSE, 5_000);

    private final String name;
    private final Integer matchCount;
    private final Boolean bonusBall;
    private final Integer price;

    WinningRank(String name, Integer matchCount, Boolean bonusBall, Integer price) {
        this.name = name;
        this.matchCount = matchCount;
        this.bonusBall = bonusBall;
        this.price = price;
    }

    public static WinningRank getRank(int matchCount, boolean isBonusBallMatch) {
        for (WinningRank winningRank : WinningRank.values()) {
            if (winningRank == WinningRank.SECOND && !isBonusBallMatch) continue;
            if (Objects.equals(winningRank.getMatchCount(), matchCount)) {
                return winningRank;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Integer getMatchCount() {
        return matchCount;
    }

    public Boolean getBonusBall() {
        return bonusBall;
    }

    public Integer getPrice() {
        return price;
    }

    public String getCondition() {
        String condition = getMatchCount() + "개 일치";
        if (this == WinningRank.SECOND) condition += ", 보너스 볼 일치";
        return condition;
    }
}
