import java.util.NoSuchElementException;

public class ArrayQueue<T> {

    public static final int INITIAL_CAPACITY = 9;

    private T[] backingArray;
    private int front;
    private int size;

    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
        size = 0;
    }

    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data entered is null.");
        }
        if (size == backingArray.length) {
            T[] newArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0;  i < size; i++) {
                newArray[i] = backingArray[(front + i) % backingArray.length];
            }
            front = 0;
            backingArray = newArray;
        }
        int enqueued = (front + size) % backingArray.length;
        backingArray[enqueued] = data;
        size++;
    }

    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty, nothing to dequeue.");
        }
        T dequeued = backingArray[front];
        backingArray[front] = null;
        front = (front + 1) % backingArray.length;
        size--;
        return dequeued;
    }

    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty, nothing to peek at.");
        }
        return backingArray[front];
    }

    public T[] getBackingArray() {
        return backingArray;
    }

    public int getFront() {
        return front;
    }

    public int size() {
        return size;
    }
}
