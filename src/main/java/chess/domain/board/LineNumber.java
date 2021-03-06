package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LineNumber {

    public static final int MIN = 1;
    public static final int MAX = 8;
    private static final Map<Integer, LineNumber> LINE_NUMBER_CACHE = new HashMap<>();

    private final int number;

    private LineNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    public static LineNumber of(int number) {
        return LINE_NUMBER_CACHE.computeIfAbsent(number, ignored -> new LineNumber(number));
    }

    private static void validateRange(int number) {
        if (!isInRange(number)) {
            throw new IllegalArgumentException(String.format("[ERROR] 위치는 %d ~ %d 까지 입력 가능합니다.", MIN, MAX));
        }
    }

    private static boolean isInRange(int number) {
        return MIN <= number && number <= MAX;
    }

    public boolean isInRangeNext(int degree) {
        return isInRange(number + degree);
    }

    public int subtract(LineNumber other) {
        return number - other.number;
    }

    public int moveLine(int degree) {
        return number + degree;
    }

    public String changeHorizontalId() {
        return String.valueOf((char) (number - 1 + 'a'));
    }

    public String changeVerticalId() {
        return String.valueOf(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineNumber that = (LineNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "LineNumber{" +
                "number=" + number +
                '}';
    }
}
