public class MockModel implements Boardgame{

    private String message = "Click game";
    private int count = 0;
    private final String[][] board = new String[1][1];

    MockModel() {
        board[0][0] = String.valueOf(count);
    }

    public boolean move(int x, int y) {
        count ++;
        board[x][y] = String.valueOf(count);
        return true;
    }


    public String getStatus(int x, int y) {
        return board[x][y];
    }


    public String getMessage() {
        return message + ": " + count;
    }
}
