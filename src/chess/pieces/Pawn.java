package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            p.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }
            // if first time moving a WHITE pawn it can move 2 tiles above
            p.setValues(position.getRow() - 2, position.getColumn());
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            if ((getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
                    && (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2))
                    && (getMoveCount() == 0)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }
            // check for opponent piece on northwest one row above
            p.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }
            // check for opponent piece on northeast one row above
            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }
            // special move en passant WHITE
            if (position.getRow() == 3) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if ((getBoard().positionExists(left) && isThereOpponentPiece(left))
                        && (getBoard().piece(left) == chessMatch.getEnPassantVulnerable())) {
                    matrix[left.getRow() - 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if ((getBoard().positionExists(right) && isThereOpponentPiece(right))
                        && (getBoard().piece(right) == chessMatch.getEnPassantVulnerable())) {
                    matrix[right.getRow() - 1][right.getColumn()] = true;
                }
            }
        } else {
            p.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }
            // if first time moving a BLACK pawn it can move 2 tiles below
            p.setValues(position.getRow() + 2, position.getColumn());
            Position p2 = new Position(position.getRow() + 1, position.getColumn());
            if ((getBoard().positionExists(p) && !getBoard().thereIsAPiece(p))
                    && (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2))
                    && (getMoveCount() == 0)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }
            // check for opponent piece on northwest one row below
            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }
            // check for opponent piece on northeast one row below
            p.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                matrix[p.getRow()][p.getColumn()] = true;
            }
            // special move en passant BLACK
            if (position.getRow() == 4) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if ((getBoard().positionExists(left) && isThereOpponentPiece(left))
                        && (getBoard().piece(left) == chessMatch.getEnPassantVulnerable())) {
                    matrix[left.getRow() + 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if ((getBoard().positionExists(right) && isThereOpponentPiece(right))
                        && (getBoard().piece(right) == chessMatch.getEnPassantVulnerable())) {
                    matrix[right.getRow() + 1][right.getColumn()] = true;
                }
            }
        }
        return matrix;
    }

    @Override
    public String toString() {
        return "P";
    }
}
