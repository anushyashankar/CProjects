import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.HashSet;

public class ExternalChainingHashMap<K, V> {

    public static final int INITIAL_CAPACITY = 13;

    public static final double MAX_LOAD_FACTOR = 0.67;

    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    public ExternalChainingHashMap() {
        this(INITIAL_CAPACITY);
    }

    public ExternalChainingHashMap(int capacity) {
        table = new ExternalChainingMapEntry[capacity];
        size = 0;
    }

    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null.");
        }
        if (((size + 1.0) / table.length) > MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }

        int index = Math.abs(key.hashCode() % table.length);

        ExternalChainingMapEntry<K, V> curr = table[index];
        table[index] = new ExternalChainingMapEntry<K, V>(key, value);
        table[index].setNext(curr);

        while (curr != null) {
            if (curr.getKey().equals(key)) {
                table[index] = table[index].getNext();
                V temp = curr.getValue();
                curr.setValue(value);
                return temp;
            }
            curr = curr.getNext();
        }

        size++;
        return null;

    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null, cannot be removed from map.");
        }
        int index = Math.abs(key.hashCode() % table.length);

        ExternalChainingMapEntry<K, V> curr = table[index];
        if (curr == null) {
            throw new NoSuchElementException("Key is not in map, cannot be removed.");
        } else if (curr.getKey().equals(key)) {
            V temp = curr.getValue();
            table[index] = curr.getNext();

            size--;
            return temp;
        }

        while (curr.getNext() != null) {
            if (curr.getNext().getKey().equals(key)) {
                V temp = curr.getNext().getValue();
                curr.setNext(curr.getNext().getNext());

                size--;
                return temp;
            }
            curr = curr.getNext();
        }

        throw new NoSuchElementException("Key is not in map, cannot be removed.");
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null, cannot be found.");
        }

        int index = Math.abs(key.hashCode() % table.length);
        ExternalChainingMapEntry<K, V> curr = table[index];

        while (curr != null) {
            if (curr.getKey().equals(key)) {
                return curr.getValue();
            }
            curr = curr.getNext();
        }

        throw new NoSuchElementException("Key is not in the map.");
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null, cannot be found.");
        }

        try {
            get(key);
            return true;
        } catch (NoSuchElementException exp) {
            return false;
        }
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();

        for (int i = 0; i < table.length; i++) {
            ExternalChainingMapEntry<K, V> curr = table[i];

            while (curr != null) {
                keys.add(curr.getKey());
                curr = curr.getNext();
            }
        }

        return keys;
    }

    public List<V> values() {
        List<V> values = new ArrayList<V>();

        for (int i = 0; i < table.length; i++) {
            ExternalChainingMapEntry<K, V> curr = table[i];

            while (curr != null) {
                values.add(curr.getValue());
                curr = curr.getNext();
            }
        }

        return values;
    }

    public void resizeBackingTable(int length) {
        if (length < size) {
            throw new IllegalArgumentException("Table cannot be resized to a length smaller than current size.");
        }

        ExternalChainingMapEntry<K, V>[] newTable = new ExternalChainingMapEntry[length];

        for (int i = 0; i < table.length; i++) {
            ExternalChainingMapEntry<K, V> curr = table[i];

            while (curr != null) {
                int index = Math.abs(curr.getKey().hashCode() % newTable.length);

                ExternalChainingMapEntry<K, V> newCurr = newTable[index];
                newTable[index] = new ExternalChainingMapEntry<>(curr.getKey(), curr.getValue());
                newTable[index].setNext(newCurr);

                curr = curr.getNext();
            }
        }
        table = newTable;
    }

    public void clear() {
        table = new ExternalChainingMapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    public ExternalChainingMapEntry<K, V>[] getTable() {
        return table;
    }

    public int size() {
        return size;
    }
}
