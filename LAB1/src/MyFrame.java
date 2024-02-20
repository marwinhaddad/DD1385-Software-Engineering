import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

// Del A

public class MyFrame extends JFrame implements ActionListener {

    private final MyButton button1 = new MyButton(Color.magenta, "This button", Color.pink, "That button");
    private final MyButton button2 = new MyButton(Color.red, "The other button", Color.orange, "Not a button");

    public MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.green);
        this.setSize(500, 500);
        this.setTitle("Marwin Haddad");

        Container c = getContentPane();
        c.setBackground(Color.green);
        c.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.setVisible(true);

        this.button1.addActionListener(this);
        this.button2.addActionListener(this);

        this.add(button1);
        this.add(button2);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            button1.toggleState();
        } else {
            button2.toggleState();
        }
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}



// Del B
/*
public class MyFrame extends JFrame {

    private final MyButton button1 = new MyButton(Color.magenta, "This button", Color.pink, "That button");
    private final MyButton button2 = new MyButton(Color.red, "The other button", Color.orange, "Not a button");

    public MyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.green);
        setSize(500, 500);
        setTitle("Marwin Haddad");

        Container c = getContentPane();
        c.setBackground(Color.green);
        c.setLayout(new FlowLayout(FlowLayout.CENTER));

        setVisible(true);

        add(button1);
        add(button2);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
*/
/*
// Del C
public class MyFrame extends JFrame {

    private final MyButton button1 = new MyButton(Color.magenta, "This button", Color.pink, "That button");
    private final MyButton button2 = new MyButton(Color.red, "The other button", Color.orange, "Button button");

    public MyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.green);
        setSize(500, 500);
        setTitle("Marwin Haddad");

        Container c = getContentPane();
        c.setBackground(Color.green);
        c.setLayout(new FlowLayout(FlowLayout.CENTER));

        setVisible(true);

        add(button1);
        add(button2);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
*/