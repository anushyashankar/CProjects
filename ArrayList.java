import java.util.NoSuchElementException;

public class ArrayList<T> {

    public static final int INITIAL_CAPACITY = 9;

    private T[] backingArray;
    private int size;
    
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index you've entered is too small"
                    + " or greater than the size of the list.");
        }
        if (data == null) {
            throw new IllegalArgumentException("The data you've entered is invalid.");
        }

        if (size + 1 > backingArray.length) {
            T[] newArr = (T[]) new Object[size * 2];
            for (int i = 0; i < index; i++) {
                newArr[i] = backingArray[i];
            }
            newArr[index] = data;
            for (int i = index + 1; i < size; i++) {
                newArr[i] = backingArray[i];
            }
            backingArray = newArr;
        } else {
            for (int i = backingArray.length - 1; i > index; i--) {
                backingArray[i] = backingArray[i - 1];
            }
            backingArray[index] = data;
        }
        size++;
    }

    public void addToFront(T data) {
        addAtIndex(0, data);
    }

    public void addToBack(T data) {
        addAtIndex(size, data);
    }

    public T removeAtIndex(int index) {
        if (index < 0 || index > (size - 1)) {
            throw new IndexOutOfBoundsException("The index you've entered is too small"
                    + " or greater than the size of the list.");
        }
        T temp = backingArray[index];
        for (int i = index; i < backingArray.length - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        size--;
        return temp;
    }

    public T removeFromFront() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty, nothing to remove.");
        }
        return removeAtIndex(0);
    }

    public T removeFromBack() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty, nothing to remove.");
        }
        return removeAtIndex(size - 1);
    }

    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index you've entered is too small"
                    + " or greater than the size of the list.");
        }
        return backingArray[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public T[] getBackingArray() {
        return backingArray;
    }

    public int size() {
        return size;
    }
}
