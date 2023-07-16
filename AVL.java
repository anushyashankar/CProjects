import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class AVL<T extends Comparable<? super T>> {

    private AVLNode<T> root;
    private int size;

    public AVL() {
    }

    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Collection is null.");
        }

        for (T datum : data) {
            if (datum == null) {
                throw new IllegalArgumentException("One of the data in the collection is null.");
            }
            add(datum);
        }
    }

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null, cannot be added.");
        }
        root = rAdd(root, data);
    }

    private AVLNode<T> rAdd(AVLNode<T> curr, T data) {
        if (curr == null) {
            size++;
            AVLNode<T> newNode = new AVLNode<T>(data);
            newNode.setHeight(0);
            newNode.setBalanceFactor(0);
            return newNode;
        }

        if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(rAdd(curr.getLeft(), data));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(rAdd(curr.getRight(), data));
        }
        return update(curr);
    }

    private AVLNode<T> update(AVLNode<T> curr) {
        int leftH = ((curr.getLeft() == null) ? (-1) : (curr.getLeft().getHeight()));
        int rightH = ((curr.getRight() == null) ? (-1) : (curr.getRight().getHeight()));
        curr.setHeight(Math.max(leftH, rightH) + 1);
        curr.setBalanceFactor(leftH - rightH);

        if (curr.getBalanceFactor() == -2) {
            if (curr.getRight().getBalanceFactor() == 1) {
                curr.setRight(rotateRight(curr.getRight()));
            }
            curr = rotateLeft(curr);
            update(curr);
        } else if (curr.getBalanceFactor() == 2) {
            if (curr.getLeft().getBalanceFactor() == -1) {
                curr.setLeft(rotateLeft(curr.getLeft()));
            }
            curr = rotateRight(curr);
            update(curr);
        }

        return curr;
    }
    
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null, cannot be searched for in list to remove.");
        }
        AVLNode<T> dummy = new AVLNode<T>(null);
        root = rRemove(root, data, dummy);
        return dummy.getData();
    }

    private AVLNode<T> rRemove(AVLNode<T> curr, T data, AVLNode<T> dummy) {
        //ADD ROTATION PARTS
        if (curr == null) {
            throw new NoSuchElementException("Data is not in tree, nothing to remove.");
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(rRemove(curr.getLeft(), data, dummy));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(rRemove(curr.getRight(), data, dummy));
        } else if (curr.getData().equals(data)) {
            dummy.setData(curr.getData());
            size--;
            if (curr.getHeight() == 0) {
                return null;
            } else if (curr.getLeft() == null) {
                return curr.getRight();
            } else if (curr.getRight() == null) {
                return curr.getLeft();
            } else {
                AVLNode<T> dummy2 = new AVLNode<>(data);
                curr.setLeft(remPred(curr.getLeft(), dummy2));
                curr.setData(dummy2.getData());
                return update(curr);
            }
        }
        return update(curr);
    }

    private AVLNode<T> remPred(AVLNode<T> node, AVLNode<T> dummy) {
        if (node.getRight() == null) {
            dummy.setData(node.getData());
            return node.getLeft();
        } else {
            node.setRight(remPred(node.getRight(), dummy));
            node = update(node);
            return node;
        }
    }

    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null, cannot be searched for in list.");
        }
        AVLNode<T> found = find(root, data);
        if (found == null) {
            throw new NoSuchElementException("Data is not in tree, cannot be retrieved.");
        }
        return found.getData();
    }

    private AVLNode<T> find(AVLNode<T> curr, T data) {
        if (curr == null) {
            return null;
        } else if (curr.getData().compareTo(data) > 0) {
            return find(curr.getLeft(), data);
        } else if (curr.getData().compareTo(data) < 0) {
            return find(curr.getRight(), data);
        } else {
            //equal
            return curr;
        }
    }

    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null, cannot be searched for in tree.");
        }
        return (find(root, data) != null);
    }

    public int height() {
        return ((root == null) ? (-1) : (root.getHeight()));
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public List<T> deepestBranches() {
        List<T> output = new ArrayList<>();
        rDeepestBranches(root, output);

        return output;
    }

    private void rDeepestBranches(AVLNode<T> curr, List<T> output) {
        if (curr != null) {
            output.add(curr.getData());

            if (curr.getBalanceFactor() == 1) {
                rDeepestBranches(curr.getLeft(), output);
            } else if (curr.getBalanceFactor() == 0) {
                rDeepestBranches(curr.getLeft(), output);
                rDeepestBranches(curr.getRight(), output);
            } else if (curr.getBalanceFactor() == -1) {
                rDeepestBranches(curr.getRight(), output);
            }
        }
    }

    public List<T> sortedInBetween(T data1, T data2) {
        if (data1 == null || data2 == null || data1.compareTo(data2) > 0) {
            throw new IllegalArgumentException("Either one of the data is null or the first number is "
                    + "greater than the second.");
        }
        List<T> output = new ArrayList<>();
        rSortedInBetween(root, data1, data2, output);
        return output;
    }

    private void rSortedInBetween(AVLNode<T> curr, T data1, T data2, List<T> output) {
        if (curr != null) {
            if (curr.getData().compareTo(data1) > 0 && curr.getData().compareTo(data2) < 0) {
                rSortedInBetween(curr.getLeft(), data1, data2, output);

                output.add(curr.getData());

                rSortedInBetween(curr.getRight(), data1, data2, output);
            } else if (curr.getData().compareTo(data1) <= 0) {
                rSortedInBetween(curr.getRight(), data1, data2, output);
            } else if (curr.getData().compareTo(data2) >= 0) {
                rSortedInBetween(curr.getLeft(), data1, data2, output);
            }
        }
    }

    public AVLNode<T> getRoot() {
        return root;
    }

    public int size() {
        return size;
    }

    private AVLNode<T> rotateRight(AVLNode<T> y) {
        AVLNode<T> x = y.getLeft();
        AVLNode<T> z = x.getRight();
        y.setLeft(z);
        x.setRight(y);
        update(y);
        return x;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> y) {
        AVLNode<T> x = y.getRight();
        AVLNode<T> z = x.getLeft();
        y.setRight(z);
        x.setLeft(y);
        update(y);
        return x;
    }
}
