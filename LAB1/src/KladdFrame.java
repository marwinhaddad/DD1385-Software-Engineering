import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KladdFrame extends JFrame implements ActionListener {

    private JButton button = new JButton("jag har rumpklåda");
    private JButton button1 = new JButton("jag har rumpklåda");

    public KladdFrame() {
        setVisible(true);
        setSize(500, 500);

        add(button);
        button.addActionListener(this);

        add(button1);
        button1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            button.setBackground(new Color((int)(Math.random() * 0x1000000)));
        } else {
            button1.setBackground(new Color((int)(Math.random() * 0x1000000)));
        }
    }

    public static void main(String[] args) {
        new KladdFrame();
    }
}
