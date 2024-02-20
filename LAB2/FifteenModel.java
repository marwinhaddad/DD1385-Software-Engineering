import java.util.*;

public class FifteenModel implements Boardgame {

    private String message;
    private static String[][] board = new String[4][4];

    FifteenModel(){
        List<String> numbers = new ArrayList<>();

        for (int i = 1; i < 16; i++) {
            numbers.add(String.valueOf(i));
        }
        numbers.add(" ");

        Collections.shuffle(numbers);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = numbers.get(i*4 + j);
            }
        }
    }

    public boolean move(int x, int y) {
        // input is the coordinates of the number you want to move to the empty space
        String temp = board[x][y];

        // first: check if the chosen number is above/ bellow or to the left/ right of the empty space

        if (y < 3) {
            if (board[x][y + 1] == " ") {
                board[x][y] = board[x][y + 1];
                board[x][y + 1] = temp;
                message = "OK";
                return true;
            }
        }

        if (y > 0) {
            if (board[x][y - 1] == " ") {
                board[x][y] = board[x][y - 1];
                board[x][y - 1] = temp;
                message = "OK";
                return true;
            }
        }
        if (x < 3) {
            if (board[x + 1][y] == " ") {
                board[x][y] = board[x + 1][y];
                board[x + 1][y] = temp;
                message = "OK";
                return true;
            }
        }

        if (x > 0) {
            if (board[x - 1][y] == " ") {
                board[x][y] = board[x - 1][y];
                board[x - 1][y] = temp;
                message = "OK";
                return true;
            }
        }
        message = "Please choose a position next to the empty one!";
        return false;
    }

    public String getStatus(int x, int y) {
        return board[x][y];
    }

    public String getMessage() {
        return message;
    }
}

