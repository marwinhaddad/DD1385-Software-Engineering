import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Vector<E extends Comparable<E>> implements SparseVec<E>{

    private final TreeMap<Integer, E> map = new TreeMap<>();

    @Override
    public void add(E elem) {
        int i = 0;
        while (true) {
            if (!map.containsKey(i)) {
                map.put(i, elem);
                return;
            }
            i++;
        }
    }

    @Override
    public void add(int pos, E elem) {
        map.put(Math.max(pos, 0), elem);
    }

    @Override
    public int indexOf(E elem) {
        for (Map.Entry<Integer, E> entry : map.entrySet()) {
            if (entry.getValue().equals(elem)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    public void removeAt(int pos) {
        map.remove(pos);
    }

    @Override
    public void removeElem(E elem) {
        removeAt(indexOf(elem));
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public int minIndex() {
        return (map.size() == 0) ? -1 : map.firstKey();
    }

    @Override
    public int maxIndex() {
        return (map.size() == 0) ? -1 : map.lastKey();
    }

    @Override
    public E get(int pos) {
        return map.get(pos);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[maxIndex() + 1];
        for (int i = 0; i < maxIndex() + 1; i++) {
            array[i] = map.get(i);
        }
        return array;
    }

    @Override
    public List<E> sortedValues() {
        return map.values().stream().sorted().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int key : map.keySet().stream().sorted().toList()) {
            str.append("{Index=").append(key).append(", Value=").append(get(key)).append("}\n");
        }
        return str.toString();
    }

    public static void main(String[] args) {
        SparseVec<String> v = new Vector<>();

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
