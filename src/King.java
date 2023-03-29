public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        return false;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn) || toLine == line && toColumn == column)
            return false;
        return Math.abs(toLine - line) <= 1 && Math.abs(toColumn - column) <= 1;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
