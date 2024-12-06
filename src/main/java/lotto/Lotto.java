package lotto;

import java.util.Collections;
import java.util.List;

public class Lotto {
    private static final int SIZE = 6;
    private static final int MIN = 1;
    private static final int MAX = 45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    protected Integer matchCount(Lotto lotto){
        return (int) numbers.stream()
                .filter(lotto::isMatch)
                .count();
    }

    protected boolean isMatch(Integer number) {
        return numbers.contains(number);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(n -> n < MIN) || numbers.stream().anyMatch(n -> n > MAX)) {
            throw new IllegalArgumentException(String.format("로또 번호는 %d부터 %d 사이의 숫자여야 합니다.", MIN, MAX));
        }
    }

    private void validateDuplicate(List<Integer> numbers){
        if(numbers.stream().distinct().count() != numbers.size()){
            throw new IllegalArgumentException("중복된 번호는 허용 되지 않습니다.");
        }
    }

    // TODO: 추가 기능 구현
}
