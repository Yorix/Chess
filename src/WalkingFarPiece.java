public abstract class WalkingFarPiece extends ChessPiece {
    public WalkingFarPiece(String color) {
        super(color);
    }

    protected boolean isDiagonalFree(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        boolean isXIncrement, isYIncrement;
        if (toLine - line > 0 && toLine - line == toColumn - column) {
            isXIncrement = true;
            isYIncrement = true;
        } else if (toLine - line > 0 && toLine - line == -(toColumn - column)) {
            isXIncrement = false;
            isYIncrement = true;
        } else if (toLine - line < 0 && toLine - line == toColumn - column) {
            isXIncrement = false;
            isYIncrement = false;
        } else if (toLine - line < 0 && toLine - line == -(toColumn - column)) {
            isXIncrement = true;
            isYIncrement = false;
        } else return false;

        for (int i = 1, length = Math.abs(toLine - line); i < length; i++) {
            int x = isXIncrement ? column + i : column - i;
            int y = isYIncrement ? line + i : line - i;
            if (chessBoard.board[y][x] != null)
                return false;
        }
        return true;
    }

    protected boolean isHVFree(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int length;

        if (toLine == line) {
            length = Math.abs(toColumn - column);
        } else if (toColumn == column) {
            length = Math.abs(toLine - line);
        } else return false;

        for (int i = 1; i < length; i++) {
            int x = toColumn == column ? column : toColumn > column ? column + i : column - i;
            int y = toLine == line ? line : toLine > line ? line + i : line - i;
            if (chessBoard.board[y][x] != null)
                return false;
        }

        return true;
    }
}
