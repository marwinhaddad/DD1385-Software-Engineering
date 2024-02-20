import java.util.Scanner;

class Text15 {
    public static void main(String[] u) {
        Scanner scan = new Scanner(System.in);
        Boardgame thegame = new FifteenModel();                 // Model object is created
        // Boardgame thegame = new TicTacToeModel();
        System.out.println("\nWelcome to game\n");
        while (true) {
            // Print the current board
            // 3 = tictactoe, 4 = fifteen game
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 0; j++)
                    System.out.print("  " + thegame.getStatus(i, j)); // getStatus
                System.out.println();
            }
            System.out.println();
            System.out.print("Your move: ");
            int i = scan.nextInt();  // get an int number from terminal window
            int j = scan.nextInt();
            thegame.move(i, j);                                 // move
            System.out.println(thegame.getMessage());         // getMessage
        }
    }
}
