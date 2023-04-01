public class Rook extends WalkingFarPiece {

    public Rook(String color) {
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

        return isHVFree(chessBoard, line, column, toLine, toColumn) && (toLine == line || toColumn == column);
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
