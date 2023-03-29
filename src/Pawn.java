public class Pawn extends ChessPiece {

    public Pawn(String color) {
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
        if (column == toColumn)
            if (color.equals("White") && line == 1 && toLine == 3
                    || color.equals("Black") && line == 6 && toLine == 4)
                return true;
        return column == toColumn
                && (color.equals("White") && toLine == line + 1 || color.equals("Black") && toLine == line - 1);
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
