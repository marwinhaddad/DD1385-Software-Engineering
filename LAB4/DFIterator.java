import java.util.*;

public class DFIterator implements Iterator<Component> {

    LinkedList<Component> stack = new LinkedList<>();

    DFIterator(Component root) {
        stack.push(root);
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public Component next() {
        if (hasNext()) {

            Component current = stack.pop();
            if (current.getComposition() != null) {
                for (Component next : current.getComposition()) {
                    stack.push(next);
                }
                return current;
            }
            return current;
        }
        return null;
    }
}
