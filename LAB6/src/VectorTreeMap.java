import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class VectorTreeMap<E extends Comparable<E>> extends TreeMap<Integer, E> implements SparseVec<E>{

    @Override
    public void add(E elem) {
        int i = 0;
        while (true) {
            if (!containsKey(i)) {
                this.put(i, elem);
                return;
            }
            i++;
        }
    }

    @Override
    public void add(int pos, E elem) {
        this.put(Math.max(pos, 0), elem);
    }

    @Override
    public int indexOf(E elem) {
        for (Map.Entry<Integer, E> entry : entrySet()) {
            if (entry.getValue().equals(elem)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    public void removeAt(int pos) {
        remove(pos);
    }

    @Override
    public void removeElem(E elem) {
        removeAt(indexOf(elem));
    }

    @Override
    public int size() {
        return sortedValues().size();
    }

    @Override
    public int minIndex() {
        return (size() == 0) ? -1 : firstKey();
    }

    @Override
    public int maxIndex() {
        return (size() == 0) ? -1 : lastKey();
    }

    @Override
    public E get(int pos) {
        for (Map.Entry<Integer, E> entry : entrySet()) {
            if (indexOf(entry.getValue()) == pos) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[maxIndex() + 1];
        for (int i = 0; i < maxIndex() + 1; i++) {
            array[i] = get(i);
        }
        return array;
    }

    @Override
    public List<E> sortedValues() {
        return values().stream().sorted().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int key : keySet().stream().sorted().toList()) {
            str.append("{Index=").append(key).append(", Value=").append(get(key)).append("}\n");
        }
        return str.toString();
    }

    public static void main(String[] args) {
        SparseVec<String> v = new VectorTreeMap<>();

        v.add("B");
        v.add(1, "A");
        v.add("D");
        v.add(3, "C");
        v.add("F");
        v.add(5, "E");

        System.out.println(v);
        System.out.println(Arrays.toString(v.toArray()));
        System.out.println(v.sortedValues());
    }
}
