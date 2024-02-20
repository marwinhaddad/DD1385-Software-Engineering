import javax.swing.*;
import java.awt.*;

class ParticleCanvas extends JPanel {

    private Model model;
    
    ParticleCanvas(Model model) {
        this.model = model;

        setPreferredSize(new Dimension(model.getWIDTH(), model.getHEIGHT()));
        setBackground(model.getColorBackground());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Particle particle : model.getAliveParticles()) {
            g.setColor(particle.getColor());
            g.fillOval((int) particle.getX(), (int) particle.getY(), 1, 1);
        }
        for (Particle particle : model.getDeadParticles()) {
            g.setColor(particle.getColor());
            g.fillOval((int) particle.getX(), (int) particle.getY(), 1,1);
        }
    }
}