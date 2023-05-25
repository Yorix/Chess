package com.yorix.chess;

public class Queen extends WalkingFarPiece {

    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn) || toLine == line && toColumn == column)
            return false;
        if (chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].color.equals(this.color))
            return false;

        return (toLine == line || toColumn == column) && isHVFree(chessBoard, line, column, toLine, toColumn)
                || (Math.abs(toLine - line) == Math.abs(toColumn - column) && isDiagonalFree(chessBoard, line, column, toLine, toColumn));
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
