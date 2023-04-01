public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board.board[i][j];

                if (piece != null && !piece.color.equals(this.color)) {
                    if (piece instanceof Pawn && (Math.abs(column - j) == 1
                            && (piece.color.equals("White") && line == i + 1 || piece.color.equals("Black") && line == i - 1)))
                        return true;

                    if ((piece instanceof King && (Math.abs(line - i) <= 1 && Math.abs(column - j) <= 1))
                            || piece.canMoveToPosition(board, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }
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
        if (chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].color.equals(this.color))
            return false;
        return Math.abs(toLine - line) <= 1 && Math.abs(toColumn - column) <= 1 && !isUnderAttack(chessBoard, toLine, toColumn);
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
