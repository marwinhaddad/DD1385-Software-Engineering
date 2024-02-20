import java.util.ArrayList;

interface Component {

    double getWeight();

    String toString();

    // X4
    ArrayList<Component> getComposition();
    String getName();
}