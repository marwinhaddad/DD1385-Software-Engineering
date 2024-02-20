import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MyFrameX extends JFrame implements ActionListener {

    MyButtonX[] buttonArr;

    public MyFrameX(int n, String[] toggleText) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.green);
        this.setSize(500, 500);
        this.setTitle("Marwin Haddad");

        Container c = getContentPane();
        c.setBackground(Color.green);
        c.setLayout(new FlowLayout(FlowLayout.LEADING));

        this.setVisible(true);

        this.buttonArr = new MyButtonX[n];

        for(int i = 0; i < n; i++) {
            MyButtonX button = new MyButtonX(Color.orange, toggleText[i*2], Color.pink, toggleText[i*2 + 1]);

            buttonArr[i] = button;
            buttonArr[i].addActionListener(this);
            add(buttonArr[i]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        for (MyButtonX button : buttonArr) {
            if (button != e.getSource()) {
                button.toggleState();
            }
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        String[] toggleText = Arrays.copyOfRange(args, 1, args.length);

        new MyFrameX(n, toggleText);
    }
}