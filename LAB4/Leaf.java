import java.util.ArrayList;

public class Leaf implements Component{

    double weight;
    String name;

    Leaf(String name, double weight) {
        this.weight = weight;
        this.name = name;
    }


    public double getWeight() {
        return this.weight;
    }

    public String toString() {
        return this.name;
    }

    // X4
    public String getName() {
        return this.name;
    }

    public ArrayList<Component> getComposition() {
        return null;
    }
}