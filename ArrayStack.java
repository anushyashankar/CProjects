import java.util.NoSuchElementException;

public class ArrayStack<T> {

    public static final int INITIAL_CAPACITY = 9;

    private T[] backingArray;
    private int size;

    public ArrayStack() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data entered is null.");
        }
        if (size == backingArray.length) {
            T[] newArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0;  i < size; i++) {
                newArray[i] = backingArray[i];
            }
            backingArray = newArray;
        }
        backingArray[size] = data;
        size++;
    }
    
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("The stack is empty, nothing to pop.");
        }
        T popped = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return popped;
    }

    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException("The stack is empty, nothing to pop.");
        }

        return backingArray[size - 1];
    }

    public T[] getBackingArray() {
        return backingArray;
    }

    public int size() {
        return size;
    }
}
