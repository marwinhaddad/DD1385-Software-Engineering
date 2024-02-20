import java.util.Random;
import java.awt.Color;

public class Particle {

    private double x;
    private double y;
    private double L;
    private boolean moving;
    private Random random = new Random();
    private Color color;


    Particle(double x, double y, double L, boolean moving, Color color) {
        this.x = x;
        this.y = y;
        this.L = L;
        this.moving = moving;
        this.color = color;
    }

    Particle(double L, boolean moving, int WIDTH_FRAME, int HEIGHT_CANVAS, Color color) {
        this.x = random.nextDouble() * (WIDTH_FRAME - 2) + 1 ;
        this.y = random.nextDouble() * (HEIGHT_CANVAS - 2) + 1;
        this.L = L;
        this.moving = moving;
        this.color = color;
    }
    
    void move() {
        if (this.moving) {
            double phi = random.nextDouble() * 2 * Math.PI;
            this.x += this.L * Math.cos(phi);
            this.y += this.L * Math.sin(phi);
        }
    }

    double getX() {
        return this.x;
    }

    double getY() {
        return this.y;
    }

    Color getColor() {
        return this.color;
    }

    boolean isMoving() {
        return this.moving;
    }

    void stopMoving() {
        this.moving = false;
        this.color = Color.RED;
    }
}
