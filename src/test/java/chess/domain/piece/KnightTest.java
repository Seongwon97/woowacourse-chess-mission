package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.BoardFixtures;
import chess.domain.board.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class KnightTest {

    @Test
    @DisplayName("나이트를 만든다.")
    void createPiece() {
        Piece piece = new Knight(Color.WHITE);

        assertThat(piece).isNotNull();
    }

    @Test
    @DisplayName("나이트를 움직인다.")
    void move() {
        Piece piece = new Knight(Color.WHITE);
        Point from = Point.of("b1");
        Point to = Point.of("c3");
        Board board = BoardFixtures.empty();

        assertThatCode(() -> piece.move(board.getPointPieces(), from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("나이트가 잘못된 위치로 이동할 수 없다.")
    void moveWrongPoint() {
        Piece piece = new Knight(Color.WHITE);
        Point from = Point.of("b1");
        Point to = Point.of("c4");
        Board board = BoardFixtures.empty();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> piece.move(board.getPointPieces(), from, to));
    }

    @Test
    @DisplayName("도착지에 적이 있어도 이동할 수 있다.")
    void moveWithEnemy() {
        Piece piece = new Knight(Color.WHITE);
        Point from = Point.of("b1");
        Point to = Point.of("c3");

        Board board = BoardFixtures.create(Map.of(Point.of("c3"), new Pawn(Color.BLACK)));

        assertThatCode(() -> piece.move(board.getPointPieces(), from, to))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("나이트는 장애물을 뛰어넘을 수 있다.")
    void moveWithObstacle() {
        Piece piece = new Knight(Color.WHITE);
        Point from = Point.of("b1");
        Point to = Point.of("c3");

        Board board = BoardFixtures.create(Map.of(Point.of("b2"), new Pawn(Color.WHITE)));

        assertThatCode(() -> piece.move(board.getPointPieces(), from, to))
                .doesNotThrowAnyException();
    }

}
