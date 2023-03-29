public class Bishop extends ChessPiece {

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
        
        return Math.abs(toLine - line) == Math.abs(toColumn - column);
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
