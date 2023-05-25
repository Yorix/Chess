package com.yorix.chess;

public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn))
            return false;
        if (chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].color.equals(this.color))
            return false;
        return Math.abs(toLine - line) == 2 && Math.abs(toColumn - column) == 1
                || Math.abs(toLine - line) == 1 && Math.abs(toColumn - column) == 2;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
