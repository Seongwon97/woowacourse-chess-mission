package chess.domain.game;

import chess.domain.board.Point;
import chess.domain.piece.Piece;

import java.util.Map;

public interface GameState {

    GameState start();

    GameState finish();

    boolean isRunnable();

    Map<Point, Piece> getPointPieces();
}