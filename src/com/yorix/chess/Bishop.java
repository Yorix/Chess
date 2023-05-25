package com.yorix.chess;

public class Bishop extends WalkingFarPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn) || toLine == line)
            return false;
        if (chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].color.equals(this.color))
            return false;

        return isDiagonalFree(chessBoard, line, column, toLine, toColumn)
                && (chessBoard.board[toLine][toColumn] == null
                || !chessBoard.board[toLine][toColumn].getColor().equals(this.color));
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
