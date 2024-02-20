import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ViewControl extends JFrame implements ActionListener {

    private final Boardgame game;
    private final int size;
    private final Square[][] board;
    private final JLabel message;
    private final Container messagePanel;

    ViewControl(Boardgame game, int size, String nameOfGame) {
        this.size = size;
        this.board = new Square[this.size][this.size];
        this.game = game;
        this.message = new JLabel();
        this.messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setTitle(nameOfGame);

        Container buttonPanel = new JPanel(new GridLayout(size, size));
        buttonPanel.setBackground(Color.darkGray);
        Color color = Color.orange;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Square button = new Square(i, j, game.getStatus(i, j), color);
                board[i][j] = button;
                board[i][j].addActionListener(this);
                board[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                buttonPanel.add(board[i][j]);
            }
        }
        this.add(buttonPanel, BorderLayout.CENTER);

        message.setText(game.getMessage());
        message.setForeground(Color.white);

        messagePanel.setBackground(Color.darkGray);
        messagePanel.add(message);
        this.add(messagePanel, BorderLayout.PAGE_END);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
         Square button = (Square) e.getSource();
         if (game.move(button.x, button.y)) {
             for (int i = 0; i < size; i++) {
                 for (int j = 0; j < size; j++) {
                     board[i][j].setText(game.getStatus(i, j));
                 }
             }
         }
         message.setText(game.getMessage());
         messagePanel.add(message);
    }

    public static void main(String[] args) {
        int gm = 1; // 0 = Fifteen Game, 1 = TicTacToe, 2 = mock game

        if (gm == 0) {
            new ViewControl(new FifteenModel(), 4, "Fifteen Game");
        } else if (gm == 1) {
            new ViewControl(new TicTacToeModel(), 3, "TicTacToe");
        } else if (gm == 2) {
            new ViewControl(new MockModel(), 1, "Mock game");
        }
    }
}