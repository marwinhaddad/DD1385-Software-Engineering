public class NumberedItem<T> {

    private final T value;
    private final int index;

    NumberedItem(int index, T value){
        this.index = Math.max(index, 0);
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    public int compareTo(NumberedItem<T> item) {
        return this.getIndex() - item.getIndex();
    }

    @Override
    public boolean equals(Object item) {
        if (item instanceof NumberedItem) {
            return  this == item ||
                    this.getValue() == ((NumberedItem<?>) item).getValue() ||
                    this.getIndex() == ((NumberedItem<?>) item).getIndex();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Index: " + this.getIndex() + " Value: " + this.getValue();
    }
}
