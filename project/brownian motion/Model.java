import java.util.HashSet;
import java.awt.Color;

public class Model {

    private final int N;
    private final double L;
    private final int DIM_WIDTH;
    private final int DIM_HEIGTH;
    private  boolean randomStart;

    private HashSet<Particle> deadParticles = new HashSet<>();
    private HashSet<Particle> aliveParticles = new HashSet<>();
    private int[][] grid;

    private final Color colorAlive = Color.WHITE;
    private final Color colorBackgorund = Color.BLACK;

    Model(int N, double L, boolean randomStart, int DIM_WIDTH, int DIM_HEIGTH) {
        this.N = N;
        this.L = L;
        this.DIM_WIDTH = DIM_WIDTH;
        this.DIM_HEIGTH = DIM_HEIGTH;
        this.randomStart = randomStart;
        grid = new int[DIM_WIDTH][DIM_HEIGTH];

        createAliveParticles();
        createDeadBorder();
        createDeadCircle();
        // createDeadCross();
        // createDeadSquare();
        placeDeadParticles(grid);
    }

    private void placeDeadParticles(int[][] grid) {
        for (Particle particle : deadParticles) {
            int x = (int) particle.getX();
            int y = (int) particle.getY();
            grid[x][y] = 1;
        }
    }

    private void createAliveParticles() {
        if (randomStart) {
            for (int i = 0; i < N; i++) {
                aliveParticles.add(new Particle(L, true, DIM_WIDTH, DIM_HEIGTH, colorAlive));
            }

        } else {

            // https://math.stackexchange.com/questions/1039482/how-to-evenly-space-a-number-of-points-in-a-rectangle
            
            int nx = (int) Math.sqrt((DIM_WIDTH/DIM_HEIGTH) * N + 
            ((DIM_WIDTH - DIM_HEIGTH) * (DIM_WIDTH - DIM_HEIGTH)) / (4*DIM_HEIGTH*DIM_HEIGTH)) +
            (DIM_WIDTH - DIM_HEIGTH) / (2*DIM_HEIGTH);
            int ny = N / nx;

            for (int i = 0; i < nx; i++) {
                for (int j = 0; j < ny; j++) {

                    double x = (i + 0.5) * DIM_WIDTH / nx;
                    double y = (j + 0.5) * DIM_HEIGTH / ny;

                    aliveParticles.add(new Particle(x, y, L, true, colorAlive));
                }
            }   
        }
    }

    private void createDeadBorder() {
        for (int i = 0; i < DIM_WIDTH; i++) {
            deadParticles.add(new Particle(i, 0, L, false, colorBackgorund));
            deadParticles.add(new Particle(i, DIM_HEIGTH-1, L, false, colorBackgorund));
            deadParticles.add(new Particle(0, i, L, false, colorBackgorund));
            deadParticles.add(new Particle(DIM_WIDTH-1, i, L, false, colorBackgorund));
        }
    }

    private void createDeadCross() {
        int d = (int) DIM_WIDTH / 4;
         for (int i = d; i < DIM_WIDTH-d; i++) {
            deadParticles.add(new Particle(i, i, L, false, colorBackgorund));
            deadParticles.add(new Particle(i, DIM_HEIGTH - i, L, false, colorBackgorund));
        }
    }

    private void createDeadCircle() {
        int cx = DIM_WIDTH / 2;
        int cy = DIM_HEIGTH / 2;
        int r = DIM_WIDTH / 4;

        for (int i = 0; i < DIM_WIDTH; i++) {
            for (int j = 0; j < DIM_HEIGTH; j++) {

                int x = i - cx;
                int y = j - cy;
                double d2 = x*x + y*y;
    
                if (Math.abs(d2 - r*r) <= r) {
                    deadParticles.add(new Particle(i, j, L, false, colorBackgorund));
                }
            }
        }
    }

    private void createDeadSquare() {
        int d = (int) DIM_WIDTH / 4;
        for (int i = d; i < DIM_WIDTH-d; i++) {
            deadParticles.add(new Particle(i, d, L, false, colorBackgorund));
            deadParticles.add(new Particle(i, DIM_HEIGTH-d, L, false, colorBackgorund));
            deadParticles.add(new Particle(d, i, L, false, colorBackgorund));
            deadParticles.add(new Particle(DIM_WIDTH - d, i, L, false, colorBackgorund));
        }
    }

    public HashSet<Particle> getAliveParticles() {
        return aliveParticles;
    }

    public HashSet<Particle> getDeadParticles() {
        return deadParticles;
    }

    public void moveParticles() {     
        for (Particle particle : aliveParticles) {
            if (hasDeadNeighbor(particle)) {
                grid[(int) particle.getX()][(int) particle.getY()] = 1;
                particle.stopMoving();
            } else {
                particle.move();
            }
        }
    }

    public int getWIDTH() {
        return DIM_WIDTH;
    }

    public int getHEIGHT() {
        return DIM_HEIGTH;
    }

    private boolean hasDeadNeighbor(Particle particle) {
        return (grid[(int) particle.getX() + 1][(int) particle.getY()] == 1 ||
                grid[(int) particle.getX() - 1][(int) particle.getY()] == 1 ||
                grid[(int) particle.getX()][(int) particle.getY() + 1] == 1 ||
                grid[(int) particle.getX()][(int) particle.getY() - 1] == 1 ||
                grid[(int) particle.getX() + 1][(int) particle.getY() + 1] == 1 ||
                grid[(int) particle.getX() - 1][(int) particle.getY() - 1] == 1 ||
                grid[(int) particle.getX() + 1][(int) particle.getY() - 1] == 1 ||
                grid[(int) particle.getX() - 1][(int) particle.getY() + 1] == 1);
    }

    public Color getColorBackground() {
        return colorBackgorund;
    }
}