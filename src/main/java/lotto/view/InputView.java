package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

public class InputView {
    public static final String DELEMETER = ",";

    public Integer askPrice() throws NumberFormatException {
        return Integer.parseInt(Console.readLine());
    }

    public List<Integer> askWinningNumbers() throws NumberFormatException {
        List<String> inputNumbers = splitWithDelimiter(Console.readLine());

        return inputNumbers.stream()
                .map(Integer::parseInt)
                .toList();
    }

    public Integer askBonusNumber() throws NumberFormatException{
        return Integer.parseInt(Console.readLine());
    }

    public List<String> splitWithDelimiter(String input){
        return Arrays.stream(input.split(DELEMETER))
                .map(String::trim)
                .toList();
    }


}

