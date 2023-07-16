import java.util.NoSuchElementException;

public class CircularSinglyLinkedList<T> {

    private CircularSinglyLinkedListNode<T> head;
    private int size;

    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is not in the bounds of this list.");
        } else if (data == null) {
            throw new IllegalArgumentException("The data entered is invalid.");
        }

        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            CircularSinglyLinkedListNode curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            CircularSinglyLinkedListNode newNode = new CircularSinglyLinkedListNode(curr.getData(), curr.getNext());
            curr.setNext(newNode);
            curr.setData(data);

            size++;
        }
    }

    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data entered is invalid.");
        }
        CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode(null);
        if (head == null) {
            head = newNode;
        }
        newNode.setData(head.getData());
        newNode.setNext(head.getNext());
        head.setNext(newNode);
        head.setData(data);

        size++;
    }

    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data entered is invalid.");
        }
        CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode(null);
        if (head == null) {
            head = newNode;
        }
        newNode.setData(head.getData());
        newNode.setNext(head.getNext());
        head.setNext(newNode);
        head.setData(data);
        head = head.getNext();

        size++;
    }

    public T removeAtIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is not in the bounds of this list.");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty, nothing to remove.");
        } else if (index == 0) {
            return removeFromFront();
        } else {
            CircularSinglyLinkedListNode<T> curr = head;

            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }

            T temp = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());
            size--;
            return temp;
        }
    }

    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty, nothing to remove.");
        }

        T temp = head.getData();
        if (size == 1) {
            head = null;
        } else {
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
        }
        size--;

        return temp;
    }

    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty, nothing to remove.");
        }

        return removeAtIndex(size - 1);
    }

    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is not in the bounds of this list.");
        }
        CircularSinglyLinkedListNode<T> curr = new CircularSinglyLinkedListNode<T>(null);
        curr = head;

        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }

        return (T) curr.getData();
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data entered is invalid.");
        }
        if (isEmpty()) {
            return null;
        }
        CircularSinglyLinkedListNode<T> curr = head;
        CircularSinglyLinkedListNode<T> beforeLastOcc = null;

        for (int i = 0; i < size - 1; i++) {
            if (curr.getNext().getData().equals(data)) {
                beforeLastOcc = curr;
            }
            curr = curr.getNext();
        }

        if (beforeLastOcc == null && curr.getNext().getData().equals(data)) {
            beforeLastOcc = curr;
            return removeFromFront();
        }
        if (beforeLastOcc == null) {
            throw new NoSuchElementException("Element not in list.");
        }

        T remove = beforeLastOcc.getNext().getData();
        beforeLastOcc.setNext(beforeLastOcc.getNext().getNext());

        size--;
        return remove;
    }

    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        CircularSinglyLinkedListNode<T> curr = head;

        for (int i = 0; i < size; i++) {
            arr[i] = curr.getData();
            curr = curr.getNext();
        }

        return arr;
    }

    public CircularSinglyLinkedListNode<T> getHead() {
        return head;
    }

    public int size() {
        return size;
    }
}
