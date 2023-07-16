import java.util.NoSuchElementException;

public class LinkedStack<T> {
   
    private LinkedNode<T> head;
    private int size;

    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data entered is null.");
        }
        if (size == 0) {
            head = new LinkedNode<T>(data);
        } else {
            LinkedNode<T> pushed = new LinkedNode<>(data);
            pushed.setNext(head);
            head = pushed;
        }

        size++;
    }

    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("Stack is empty, nothing to pop.");
        }
        LinkedNode<T> popped = head;
        head = head.getNext();
        size--;
        return popped.getData();
    }

    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException("Stack is empty, nothing to peek at.");
        }
        return head.getData();
    }

    public LinkedNode<T> getHead() {
        return head;
    }

    public int size() {
        return size;
    }
}
