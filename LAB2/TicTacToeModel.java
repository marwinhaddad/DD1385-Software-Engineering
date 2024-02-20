public class TicTacToeModel implements Boardgame{

    public int turnCount = 0;
    private String message = "Player 1 == X     Player 2 == O";
    private String[][] board = new String[][] {{"", "", ""}, {"", "", ""}, {"", "", ""}};
    private final String[] piece = new String[] {"X", "O"};

    private boolean pieceChosen = false;
    private int xCurrent;
    private int yCurrent;

    public boolean move(int x, int y) {
        if (turnCount < 6) {
            return firstPhase(x, y);
        } else {
            return secondPhase(x, y);
        }
    }

    // place pieces
    private boolean firstPhase(int x, int y) {
        if (board[x][y] == "") {
            board[x][y] = piece[turnCount % 2];
            message = String.format("Player %s: Move is OK.", (turnCount) % 2 + 1);
            turnCount ++;
            return true;
        }
        message = String.format("Player %d: Please choose a vacant spot on the board.", (turnCount) % 2 + 1);
        return false;
    }

    // moves pieces
    private boolean secondPhase(int x, int y) {
        if (pieceChosen) {
            if (board[x][y] == "") {
                board[x][y] = piece[turnCount % 2];
                board[xCurrent][yCurrent] = "";
                pieceChosen = false;
                message = String.format("Player %d: Placement is OK.", (turnCount) % 2 + 1);
                turnCount++;
                return true;
            } else {
                message = String.format("Player %d: Choose a vacant spot on the board to place your piece.", (turnCount) % 2 + 1);
                return false;
            }
        } else {
            if (board[x][y] == piece[turnCount % 2]) {
                xCurrent = x;
                yCurrent = y;
                pieceChosen = true;
                message = String.format("Player %d: Choice of piece is OK.", (turnCount) % 2 + 1);
                return true;
            } else {
                message = String.format("Player %d: Choose one of your pieces to move.", (turnCount) % 2 + 1);
                return false;
            }
        }
    }

    public String getStatus(int x, int y) {
        return board[x][y];
    }

    public String getMessage() {
        return message;
    }
}
