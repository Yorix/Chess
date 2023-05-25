package com.yorix.chess;

public class Pawn extends ChessPiece {
    private Pawn enPassant;

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    private boolean enPassant(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (column == toColumn) {
            if (color.equals("White") && line == 1 && toLine == 3
                    || color.equals("Black") && line == 6 && toLine == 4) {
                ChessPiece threatLeft = toColumn - 1 >= 0 ? chessBoard.board[toLine][toColumn - 1] : null;
                ChessPiece threatRight = toColumn + 1 <= 7 ? chessBoard.board[toLine][toColumn + 1] : null;
                if (threatLeft instanceof Pawn && !threatLeft.color.equals(this.color))
                    ((Pawn) threatLeft).enPassant = this;
                if (threatRight instanceof Pawn && !threatRight.color.equals(this.color))
                    ((Pawn) threatRight).enPassant = this;
                return true;
            }
        }
        return false;
    }

    private boolean canCapture(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (Math.abs(toColumn - column) == 1) {
            if (chessBoard.board[line][toColumn] == enPassant) {
                chessBoard.board[line][toColumn] = null;
                enPassant = null;
                return true;
            }
        } else return false;

        return chessBoard.board[toLine][toColumn] != null
                && !chessBoard.board[toLine][toColumn].color.equals(this.color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn))
            return false;
        if (color.equals("White") && toLine == line + 1 || color.equals("Black") && toLine == line - 1) {
            if (canCapture(chessBoard, line, column, toLine, toColumn))
                return true;
            if (toColumn == column && chessBoard.board[toLine][toColumn] == null)
                return true;
        }

        return enPassant(chessBoard, line, column, toLine, toColumn);
    }

    @Override
    public String getSymbol() {
        return "P";
    }
    public void removeEnPassant() {
        enPassant = null;
    }
}
