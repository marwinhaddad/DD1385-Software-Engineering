import javax.swing.*;
import java.awt.*;

public class Square extends JButton {

    int x;
    int y;
    String label;

    Square(int x, int y, String label, Color color) {
        this.x = x;
        this.y = y;
        this.label = label;

        this.setBackground(color);
        this.setText(this.label);
    }
}
