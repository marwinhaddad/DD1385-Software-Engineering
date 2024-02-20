import static org.junit.Assert.*;

import org.junit.Test;

public class VectorTest {

    @Test
    public void testEmpty() {
        SparseVec<Integer> vector = new Vector<>();
        assertEquals(-1, vector.minIndex());
        assertEquals(-1, vector.maxIndex());
        assertNull(vector.get(1));
        assertEquals(-1, vector.indexOf(1));
        assertEquals(0, vector.size());

        assertEquals(0, vector.sortedValues().size());
        assertEquals(0, vector.toArray().length);
    }

    @Test
    public void testSpecificAdd() {
        SparseVec<Integer> vector = new Vector<>();

        vector.add(0, 0);
        assertEquals(1, vector.size());

        vector.add(1, 1);
        assertEquals(2, vector.size());

        vector.add(100, 100);
        assertEquals(3, vector.size());

        vector.add(-1, 2);
        assertEquals(2, vector.get(0).intValue());
        assertEquals(3, vector.size());

        assertEquals(100, vector.maxIndex());
        assertEquals(0, vector.minIndex());
    }

    @Test
    public void testUnspecificAdd() {
        SparseVec<Integer> vector = new Vector<>();

        for (int i = 0; i < 5; i++) {
            vector.add(i);
        }

        assertEquals(5, vector.size());
        assertEquals(5, vector.sortedValues().size());
        assertEquals(5, vector.toArray().length);

        assertEquals(4, vector.maxIndex());
        assertEquals(0, vector.minIndex());

        assertEquals(0, vector.get(0).intValue());
        assertEquals(1, vector.get(1).intValue());
        assertEquals(2, vector.get(2).intValue());
        assertEquals(3, vector.get(3).intValue());
        assertEquals(4, vector.get(4).intValue());
        assertNull(vector.get(5));

        vector.add(6, 6);
        vector.add(5);

        assertEquals(5, vector.indexOf(5));
        assertEquals(5, vector.get(5).intValue());

        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6}, vector.toArray());

        assertEquals(7, vector.size());
    }

    @Test
    public void testRemove() {
        SparseVec<Integer> vector = new Vector<>();

        for (int i = 0; i < 5; i++) {
            vector.add(i);
        }
        assertEquals(5, vector.size());
        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4}, vector.toArray());

        vector.removeAt(0);
        assertArrayEquals(new Integer[]{null, 1, 2, 3, 4}, vector.toArray());

        vector.removeElem(2);
        assertArrayEquals(new Integer[]{null, 1, null, 3, 4}, vector.toArray());

        assertEquals(3, vector.size());

        assertNull(vector.get(2));
        assertEquals(1, vector.get(1).intValue());
        assertEquals(4, vector.indexOf(4));
    }

    @Test
    public void testStringValues() {
        SparseVec<String> vector = new Vector<>();

        vector.add(3, "A");
        vector.add(4, "B");
        vector.add(5, "C");
        vector.add("D");
        vector.add("E");
        vector.add("F");

        assertArrayEquals(new String[]{"D", "E", "F", "A", "B", "C"}, vector.toArray());
        assertEquals(6, vector.size());

        assertEquals("A", vector.get(3));
        assertArrayEquals(new String[]{"A", "B", "C", "D", "E", "F"}, vector.sortedValues().toArray());

        vector.removeAt(0);
        vector.removeElem("F");
        vector.removeAt(4);

        assertArrayEquals(new String[]{null, "E", null, "A", null, "C"}, vector.toArray());

        assertEquals(3, vector.size());
        assertEquals(1, vector.minIndex());
        assertEquals(5, vector.maxIndex());

        assertEquals(-1, vector.indexOf("B"));
    }
}