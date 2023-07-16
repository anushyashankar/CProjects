import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MaxHeap<T extends Comparable<? super T>> {

    public static final int INITIAL_CAPACITY = 13;

    private T[] backingArray;
    private int size;

    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("ArrayList passed in was null.");
        }

        backingArray = (T[]) new Comparable[2 * data.size() + 1];

        for (int i = 0; i < data.size(); i++) {
            T element = data.get(i);

            if (element == null) {
                throw new IllegalArgumentException("An element within the ArrayList is null.");
            }
            backingArray[i + 1] = element;
            size++;
        }

        buildHeap(size / 2);
    }

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data entered is null.");
        }
        if (size() == backingArray.length - 1) {
            T[] newArr = (T[]) new Comparable[backingArray.length * 2];

            for (int i = 1; i <= size; i++) {
                newArr[i] = backingArray[i];
            }

            backingArray = newArr;
        }

        size++;
        backingArray[size] = data;
        upHeap(size);
    }

    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty, nothing to remove.");
        }

        T removed = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;

        downHeap(1);

        return removed;
    }

    public T getMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty, nothing to return.");
        }
        return backingArray[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    public T[] getBackingArray() {
        return backingArray;
    }

    public int size() {
        return size;
    }

    private void buildHeap(int index) {
        if (index != 0) {
            downHeap(index);
            buildHeap(--index);
        }
    }
    
    private void downHeap(int index) {
        if (2 * index <= size && backingArray[index].compareTo(backingArray[2 * index]) < 0
                || 2 * index + 1 <= size && backingArray[index].compareTo(backingArray[index * 2 + 1]) < 0) {
            T temp = backingArray[index];

            if (2 * index + 1 > size || backingArray[index * 2].compareTo(backingArray[index * 2 + 1]) > 0) {
                backingArray[index] = backingArray[index * 2];
                backingArray[index * 2] = temp;
                downHeap(index * 2);
            } else {
                backingArray[index] = backingArray[index * 2 + 1];
                backingArray[index * 2 + 1] = temp;
                downHeap(index * 2 + 1);
            }
        }
    }

    private void upHeap(int index) {
        if (index != 1 && backingArray[index].compareTo(backingArray[index / 2]) > 0) {
            T temp = backingArray[index];
            backingArray[index] = backingArray[index / 2];
            backingArray[index / 2] = temp;
            upHeap(index / 2);
        }
    }
}
