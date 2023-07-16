import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

public class BST<T extends Comparable<? super T>> {

    private BSTNode<T> root;
    private int size;

    public BST() {
    }

    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The list of data is null and cannot be used to initialize the tree.");
        }
        for (T datum : data) {
            if (datum == null) {
                throw new IllegalArgumentException("This element of data is null"
                                                    + " and cannot be used to initialize the tree.");
            }
            add(datum);
        }
    }

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data entered is null and cannot be added.");
        }
        root = rAdd(root, data);
    }

    private BSTNode<T> rAdd(BSTNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new BSTNode<T>(data);
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(rAdd(curr.getLeft(), data));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(rAdd(curr.getRight(), data));
        }
        return curr;
    }

    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null, cannot be searched for and removed from tree.");
        }
        BSTNode<T> dummy = new BSTNode<T>(null);
        root = rRemove(root, data, dummy);
        size--;
        return data;
    }

    private BSTNode<T> rRemove(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("Data is not in tree, nothing to remove.");
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(rRemove(curr.getLeft(), data, dummy));
            return curr;
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(rRemove(curr.getRight(), data, dummy));
            return curr;
        } else if (curr.getData().equals(data)) {
            dummy.setData(curr.getData());
            if (curr.getLeft() == null && curr.getRight() == null) {
                //0 kids
                return null;
            } else if (curr.getLeft() != null && curr.getRight() == null) {
                //1 left kid
                return curr.getLeft();
            } else if (curr.getLeft() == null && curr.getRight() != null) {
                //1 right kid
                return curr.getRight();
            } else if (curr.getLeft() != null && curr.getRight() != null) {
                //2 kids
                BSTNode<T> dummy2 = new BSTNode(null);
                curr.setRight(remSucc(curr.getRight(), dummy2));
                curr.setData(dummy2.getData());
                return curr;
            }
        }
        return null;
    }

    private BSTNode<T> remSucc(BSTNode<T> node, BSTNode<T> dummy) {
        if (node.getLeft() == null) {
            dummy.setData(node.getData());
            return node.getRight();
        } else {
            node.setLeft(remSucc(node.getLeft(), dummy));
            return node;
        }
    }

    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null, cannot be searched for and retrieved from tree.");
        }
        BSTNode<T> found = find(root, data);
        if (found == null) {
            throw new NoSuchElementException("Data is not in tree, cannot be retrieved.");
        }
        return found.getData();
    }

    private BSTNode<T> find(BSTNode<T> curr, T data) {
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

    public List<T> preorder() {
        return rPreOrder(root, new LinkedList<T>());
    }

    private List<T> rPreOrder(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            list.add(curr.getData());
            rPreOrder(curr.getLeft(), list);
            rPreOrder(curr.getRight(), list);
        }
        return list;
    }

    public List<T> inorder() {
        return rInOrder(root, new LinkedList<T>());
    }

    private List<T> rInOrder(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            rInOrder(curr.getLeft(), list);
            list.add(curr.getData());
            rInOrder(curr.getRight(), list);
        }
        return list;
    }

    public List<T> postorder() {
        return rPostOrder(root, new LinkedList<T>());
    }

    private List<T> rPostOrder(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            rPostOrder(curr.getLeft(), list);
            rPostOrder(curr.getRight(), list);
            list.add(curr.getData());
        }
        return list;
    }

    public List<T> levelorder() {
        Queue<BSTNode<T>> q = new LinkedList<>();
        ArrayList<T> traversal = new ArrayList<>();

        q.add(root);

        while (!q.isEmpty()) {
            BSTNode<T> curr = q.remove();
            if (curr != null) {
                q.add(curr.getLeft());
                q.add(curr.getRight());
                traversal.add(curr.getData());
            }
        }
        return traversal;
    }

    public int height() {
        return rHeight(root);
    }

    private int rHeight(BSTNode<T> curr) {
        if (curr == null) {
            return -1;
        }

        return Math.max(rHeight(curr.getLeft()), rHeight(curr.getRight())) + 1;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public List<T> kLargest(int k) {
        //reverse postorder, make sure to stop at k
        if (k < 0 || k > size) {
            throw new IllegalArgumentException("k value is not within the bounds of the tree, "
                                               + "cannot get this number of the largest elements.");
        }
        List<T> output = new LinkedList<>();
        return rKLargest(root, k, output);
    }

    private List<T> rKLargest(BSTNode<T> curr, int k, List<T> output) {
        if (output.size() >= k) {
            return output;
        }
        if (curr == null) {
            if (output.size() == 0) {
                return output;
            }

            return null;
        } else {
            rKLargest(curr.getRight(), k, output);

            output.add(0, curr.getData());

            rKLargest(curr.getLeft(), k, output);
        }

        return output;
    }
    
    public BSTNode<T> getRoot() {
        return root;
    }

    public int size() {
        return size;
    }
}
