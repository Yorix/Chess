public class Queen extends ChessPiece {

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
        return toLine == line || toColumn == column || Math.abs(toLine - line) == Math.abs(toColumn - column);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
