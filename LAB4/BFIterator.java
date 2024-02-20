import java.util.*;

public class BFIterator implements Iterator<Component> {

    LinkedList<Component> queue = new LinkedList<>();

    BFIterator(Component root) {
        queue.add(root);
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public Component next() {
        if (hasNext()) {

            Component current = queue.pop();
            if (current.getComposition() != null) {
                queue.addAll(current.getComposition());
                return current;
            }
            return current;
        }
        return null;
    }
}
