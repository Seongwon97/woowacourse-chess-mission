package chess.domain.piece.move;

import chess.domain.board.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.piece.move.StraightDirection.*;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @ParameterizedTest(name = "{index} {displayName} {2}")
    @DisplayName("출발지와 도착지로 직선 방향을 찾는다.")
    @MethodSource("methodProvider")
    void findDirection(final Point from, final Point to, StraightDirection direction) {
        assertThat(find(from, to)).isEqualTo(direction);
    }

    static Stream<Arguments> methodProvider() {
        int horizontal = 4;
        int vertical = 4;
        Point from = Point.of(horizontal, vertical);
        return Stream.of(
                Arguments.of(from, Point.of(horizontal + 1, vertical), EAST),
                Arguments.of(from, Point.of(horizontal + 1, vertical + 1), NORTHEAST),
                Arguments.of(from, Point.of(horizontal + 1, vertical + -1), SOUTHEAST),
                Arguments.of(from, Point.of(horizontal + -1, vertical), WEST),
                Arguments.of(from, Point.of(horizontal + -1, vertical + 1), NORTHWEST),
                Arguments.of(from, Point.of(horizontal + -1, vertical + -1), SOUTHWEST),
                Arguments.of(from, Point.of(horizontal, vertical + 1), NORTH),
                Arguments.of(from, Point.of(horizontal, vertical + -1), SOUTH)
        );
    }

    @ParameterizedTest(name = "{index} {displayName} {0}")
    @DisplayName("상하좌우 여부를 확인한다.")
    @MethodSource("isCrossProvider")
    void isCrossTest(final StraightDirection direction, final boolean result) {
        assertThat(direction.isCross()).isEqualTo(result);
    }

    static Stream<Arguments> isCrossProvider() {
        return Stream.of(
                Arguments.of(SOUTH, true),
                Arguments.of(NORTH, true),
                Arguments.of(WEST, true),
                Arguments.of(EAST, true),
                Arguments.of(NORTHEAST, false),
                Arguments.of(SOUTHEAST, false),
                Arguments.of(NORTHWEST, false),
                Arguments.of(SOUTHWEST, false)
        );
    }
}
