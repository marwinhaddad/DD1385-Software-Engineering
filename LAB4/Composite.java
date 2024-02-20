import java.util.ArrayList;
import java.util.Iterator;

public class Composite implements Component, Iterable<Component> {

    ArrayList<Component> composition = new ArrayList<>();
    double weight;
    String name;

    Composite(String name, double weight) {
        this.weight = weight;
        this.name = name;
    }


    public void add(Component item) {
        composition.add(item);
    }


    public void remove(Component item) {
        composition.remove(item);
    }


    public double getWeight() {
        double temp = 0.0;
        for (Component item : composition) {
            temp += item.getWeight();
        }
        return this.weight + temp;
    }

    @Override
    public String toString() {
        String str = "\n" + this.name + ":";
        for (Component item : composition) {
            str += " " + item;
        }
        return str;
    }

    // X4
    public String getName() {
        return this.name;
    }

    public ArrayList<Component> getComposition(){
        return composition;
    }

    public Iterator<Component> iterator() {
        return new BFIterator(this);
        // return new DFIterator(this);
        }

        public Iterator<Component> dfiter() {
            return new DFIterator(this);
        }
}
