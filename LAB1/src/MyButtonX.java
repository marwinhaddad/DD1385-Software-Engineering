import javax.swing.*;
import java.awt.*;

public class MyButtonX extends JButton {

    private Color c1;
    private String s1;
    private Color c2;
    private String s2;

    public MyButtonX(Color c1, String s1, Color c2, String s2) {
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