package lotto.view;

import lotto.domain.WinningRank;

import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static final String ERROR_PREFIX = "[ERROR] ";

    public void printInstruction(String instruction) {
        System.out.println(instruction);
    }

    public void printList(List<String> list) {
        printInstruction(list.size() + "개를 구매했습니다.");
        list.forEach(System.out::println);
    }

    public void printLineSeparator() {
        System.out.println();
    }

    public void printStatus(Map<WinningRank, Integer> status) {
        printInstruction("당첨 통계");
        printInstruction("---");
        status.forEach((k, v) ->
                System.out.printf("%s (%,d원) - %,d개%n", k.getCondition(), k.getPrice(), v));
    }

    public void printTotalProfit(Float profitRate) {
        System.out.printf("총 수익률은 %,.1f%%입니다.%n", profitRate);
    }

    public void printError(String error) {
        System.out.println(ERROR_PREFIX + error);
    }
}
