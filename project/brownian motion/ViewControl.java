import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ViewControl extends JFrame implements ActionListener {


    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");

    private final JLabel SPEED_LABEL = new JLabel("Speed: ");
    private final String[] SPEED_OPTIONS = {"1.0x", "0.1x", "0.2x", "0.5x", "2x", "5x", "10x"};
    JComboBox<String> speed = new JComboBox<String>(SPEED_OPTIONS);

    private JPanel buttonPanel = new JPanel();

    private ParticleCanvas particleCanvas;
    private Timer timer;
    private double speedFactor = 1.0;
    private final int DELAY = 50;


    ViewControl(Model model) {
        this.particleCanvas = new ParticleCanvas(model);

        this.timer = new Timer((int) (DELAY / speedFactor), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.moveParticles();
                particleCanvas.repaint();
            }
        });
        
        int HEIGHT_BUTTON = 35;
        int HEIGHT_CANVAS = model.getHEIGHT() + 37;
        int WIDTH_FRAME = model.getWIDTH() + 14;
        int HEIGHT_FRAME = HEIGHT_CANVAS + HEIGHT_BUTTON;
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setTitle("Browninan Motion");
        setResizable(true);

        buttonPanel.setPreferredSize(new Dimension(WIDTH_FRAME, HEIGHT_BUTTON));
        buttonPanel.add(SPEED_LABEL);
        buttonPanel.add(speed);
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        add(particleCanvas);
        add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        speed.addActionListener(this);

        setVisible(true);
    } 

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            timer.start();

        } else if (e.getSource() == stopButton) {
            timer.stop();

        } else if (e.getSource() == speed) {
            String selectedSpeed = (String) speed.getSelectedItem();
            switch (selectedSpeed) {
                case "1.0x":
                    speedFactor = 1.0;
                    break;
                case "0.1x":
                    speedFactor = 0.1;
                    break;
                case "0.2x":
                    speedFactor = 0.2;
                    break;
                case "0.5x":
                    speedFactor = 0.5;
                    break;
                case "2x":
                    speedFactor = 2.0;
                    break;
                case "5x":
                    speedFactor = 5.0;
                    break;
                case "10x":
                    speedFactor = 10.0;
                    break;
            }
        this.timer.setDelay((int) (DELAY / speedFactor));
        }
    }

    public static void main(String[] args) {
    
        int N = 10000;
        int L = 1;
        boolean randomStart = true;

        int DIM_WIDTH = 500;
        int DIM_HEIGTH = 500;

        new ViewControl(new Model(N, L, randomStart, DIM_WIDTH, DIM_HEIGTH));
    }
}