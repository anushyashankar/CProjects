import java.util.NoSuchElementException;

public class LinkedQueue<T> {

    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data entered is null.");
        }
        if (size == 0) {
            head = new LinkedNode<T>(data);
            tail = head;
        } else {
            LinkedNode<T> newNode = new LinkedNode<>(data);
            tail.setNext(newNode);
            tail = tail.getNext();
        }
        size++;
    }

    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty, nothing to dequeue.");
        }
        LinkedNode<T> dequeued = head;
        head = head.getNext();
        size--;
        return dequeued.getData();
    }

    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty, nothing to peek at.");
        }
        return head.getData();
    }

    public LinkedNode<T> getHead() {
        return head;
    }

    public LinkedNode<T> getTail() {
        return tail;
    }

    public int size() {
        return size;
    }
}
