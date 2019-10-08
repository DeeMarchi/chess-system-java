package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    private boolean canMove(Position position) {
        ChessPiece chessPiece = (ChessPiece)getBoard().piece(position);
        return (chessPiece == null) || (chessPiece.getColor() != getColor());
    }

    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return (p instanceof Rook)
                && (p.getColor() == getColor())
                && (p.getMoveCount() == 0);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        // above
        p.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }
        // below
        p.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }
        // left
        p.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }
        // right
        p.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }
        // northwest
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }
        // northeast
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }
        // southwest
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }
        // southeast
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }
        // special move Castling
        if ((getMoveCount() == 0) && !chessMatch.isCheck()) {
            // special move Castling kingside rook
            Position posRook1 = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(posRook1)) {
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);
                if ((getBoard().piece(p1) == null) && (getBoard().piece(p2) == null)) {
                    matrix[position.getRow()][position.getColumn() + 2] = true;
                }
            }
            // special move Castling queenside rook
            Position posRook2 = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(posRook2)) {
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);
                if ((getBoard().piece(p1) == null)
                        && (getBoard().piece(p2) == null)
                        && (getBoard().piece(p3) == null)) {
                    matrix[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }
        return matrix;
    }

    @Override
    public String toString() {
        return "K";
    }
}
