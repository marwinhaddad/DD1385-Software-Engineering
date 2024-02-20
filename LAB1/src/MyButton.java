import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MyButton extends JButton {

    private Color c1;
    private String s1;
    private Color c2;
    private String s2;

    public MyButton(Color c1, String s1, Color c2, String s2) {
        this.c1 = c1;
        this.s1 = s1;
        this.c2 = c2;
        this.s2 = s2;

        this.setBackground(c1);
        this.setText(s1);
    }

    public void toggleState() {
        if (this.getBackground() == c1) {
            this.setBackground(c2);
            this.setText(s2);
        } else {
            this.setBackground(c1);
            this.setText(s1);
        }
    }
}


// Del B
/*
public class MyButton extends JButton implements ActionListener {

    private Color c1;
    private String s1;
    private Color c2;
    private String s2;

    public MyButton(Color c1, String s1, Color c2, String s2) {
        this.c1 = c1;
        this.s1 = s1;
        this.c2 = c2;
        this.s2 = s2;

        this.setBackground(c1);
        this.setText(s1);
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        toggleState();
    }

    public void toggleState() {
        if (this.getBackground() == c1) {
            this.setBackground(c2);
            this.setText(s2);
        } else {
            this.setBackground(c1);
            this.setText(s1);
        }
    }
}
 */
/*
// Del C
public class MyButton extends JButton {

    private Color c1;
    private String s1;
    private Color c2;
    private String s2;

    public MyButton(Color c1, String s1, Color c2, String s2) {
        this.c1 = c1;
        this.s1 = s1;
        this.c2 = c2;
        this.s2 = s2;

        new Ear(this);

        this.setBackground(c1);
        this.setText(s1);
    }

    public void toggleState() {
        if (this.getBackground() == c1) {
            this.setBackground(c2);
            this.setText(s2);
        } else {
            this.setBackground(c1);
            this.setText(s1);
        }
    }
}
*/
