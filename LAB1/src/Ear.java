import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ear implements ActionListener {

    MyButton button;

    Ear(MyButton button) {

        this.button = button;
        button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        button.toggleState();
    }
}
