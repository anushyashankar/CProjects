import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Your implementation of a BST.
 *
 * @author Anushya Shankar
 * @version 1.0
 * @userid ashankar92
 * @GTID 903826158
 *
 * Collaborators: Angela Yu
 *
 * Resources: n/a
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
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

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data entered is null and cannot be added.");
        }
        root = rAdd(root, data);
    }

    /**
     * Recursive helper method for add().
     * @param curr the current node
     * @param data the data to add
     * @return the subtree
     */
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

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null, cannot be searched for and removed from tree.");
        }
        BSTNode<T> dummy = new BSTNode<T>(null);
        root = rRemove(root, data, dummy);
        size--;
        return data;
    }

    /**
     * Recursive helper method for remove().
     * @param curr the current node being accessed in the BST
     * @param data the data to remove
     * @param dummy the placeholder for a node
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     * @return the data that was removed
     */
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

    /**
     * Recursive helper method for remove() to identify successor.
     * @param node the current node in the BST
     * @param dummy the placeholder node
     * @return the given node's successor
     */
    private BSTNode<T> remSucc(BSTNode<T> node, BSTNode<T> dummy) {
        if (node.getLeft() == null) {
            dummy.setData(node.getData());
            return node.getRight();
        } else {
            node.setLeft(remSucc(node.getLeft(), dummy));
            return node;
        }
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
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

    /**
     * Recursive helper method for both get() and contains()
     * @param curr the current node
     * @param data the data to find
     * @return the node containing it
     */
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

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null, cannot be searched for in tree.");
        }
        return (find(root, data) != null);
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        return rPreOrder(root, new LinkedList<T>());
    }

    /**
     * Recursive helper method for preorder().
     * @param curr the current node
     * @param list the list to add the preorder traversal to
     * @return the preorder traversal of the tree as it is constructed
     */
    private List<T> rPreOrder(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            list.add(curr.getData());
            rPreOrder(curr.getLeft(), list);
            rPreOrder(curr.getRight(), list);
        }
        return list;
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        return rInOrder(root, new LinkedList<T>());
    }

    /**
     * Recursive helper method for inorder().
     * @param curr the current node
     * @param list the list to add the inorder traversal to
     * @return the inorder traversal of the tree as it is constructed
     */
    private List<T> rInOrder(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            rInOrder(curr.getLeft(), list);
            list.add(curr.getData());
            rInOrder(curr.getRight(), list);
        }
        return list;
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        return rPostOrder(root, new LinkedList<T>());
    }

    /**
     * Recursive helper method for postorder().
     * @param curr the current node
     * @param list the list to add the postorder traversal to
     * @return the postorder traversal of the tree as it is constructed
     */
    private List<T> rPostOrder(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            rPostOrder(curr.getLeft(), list);
            rPostOrder(curr.getRight(), list);
            list.add(curr.getData());
        }
        return list;
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
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

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return rHeight(root);
    }

    /**
     * Recursive helper method for height().
     * @param curr the current node
     * @return the height of the tree
     */
    private int rHeight(BSTNode<T> curr) {
        if (curr == null) {
            return -1;
        }

        return Math.max(rHeight(curr.getLeft()), rHeight(curr.getRight())) + 1;
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Finds and retrieves the k-largest elements from the BST in sorted order,
     * least to greatest.
     *
     * This must be done recursively.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * EXAMPLE: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * kLargest(5) should return the list [25, 37, 40, 50, 75].
     * kLargest(3) should return the list [40, 50, 75].
     *
     * Should have a running time of O(log(n) + k) for a balanced tree and a
     * worst case of O(n + k), with n being the number of data in the BST
     *
     * @param k the number of largest elements to return
     * @return sorted list consisting of the k largest elements
     * @throws java.lang.IllegalArgumentException if k < 0 or k > size
     */
    public List<T> kLargest(int k) {
        //reverse postorder, make sure to stop at k
        if (k < 0 || k > size) {
            throw new IllegalArgumentException("k value is not within the bounds of the tree, "
                                               + "cannot get this number of the largest elements.");
        }
        List<T> output = new LinkedList<>();
        return rKLargest(root, k, output);
    }

    /**
     * Recursive helper method for kLargest().
     * @param curr the current node
     * @param k the number of largest elements to return
     * @param output sorted list consisting of k largest elements
     */
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


    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
