import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 *
 * @author Anushya Shankar
 * @version 1.0
 * @userid ashankar92
 * @GTID 903826158
 *
 * Collaborators: n/a
 *
 * Resources: n/a
 */
public class AVL<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
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

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null, cannot be added.");
        }
        root = rAdd(root, data);
    }

    /**
     * Recursive helper for add().
     * @param curr the current node
     * @param data the dat to add
     * @return the updated tree
     */
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
        //add allowances for rotations
    }

    /**
     * Recursive helper to update heights and BF's.
     * @param curr the current node
     * @return updated node (if needed)
     */
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

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data, NOT successor. As a reminder, rotations can occur
     * after removing the predecessor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null, cannot be searched for in list to remove.");
        }
        AVLNode<T> dummy = new AVLNode<T>(null);
        root = rRemove(root, data, dummy);
        return dummy.getData();
    }

    /**
     * Private recursive helper for remove.
     * @param curr the current node being accessed in the BST
     * @param data the data to remove
     * @param dummy the placeholder for a node
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     * @return the data that was removed
     */
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

    /**
     * Recursive helper for finding predecessor.
     * @param node the current node
     * @param dummy a placeholder node
     * @return predecessor node
     */
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

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
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

    /**
     * Recursive helper method for both get() and contains()
     * @param curr the current node
     * @param data the data to find
     * @return the node containing it
     */
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

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
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
     * Returns the height of the root of the tree.
     *
     * Should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return ((root == null) ? (-1) : (root.getHeight()));
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the data on branches of the tree with the maximum depth. If you
     * encounter multiple branches of maximum depth while traversing, then you
     * should list the remaining data from the left branch first, then the
     * remaining data in the right branch. This is essentially a preorder
     * traversal of the tree, but only of the branches of maximum depth.
     *
     * This must be done recursively.
     *
     * Your list should not have duplicate data, and the data of a branch should be
     * listed in order going from the root to the leaf of that branch.
     *
     * Should run in worst case O(n), but you should not explore branches that
     * do not have maximum depth. You should also not need to traverse branches
     * more than once.
     *
     * Hint: How can you take advantage of the balancing information stored in
     * AVL nodes to discern deep branches?
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * Returns: [10, 5, 2, 1, 0, 7, 8, 9, 15, 20, 25, 30]
     *
     * @return the list of data in branches of maximum depth in preorder
     * traversal order
     */
    public List<T> deepestBranches() {
        List<T> output = new ArrayList<>();
        rDeepestBranches(root, output);

        return output;
    }

    /**
     * Recursive helper for deepestBranches().
     * @param curr the current node
     * @param output the list of data in branches of maximum depth in preorder traversal order
     */
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

    /**
     * Returns a sorted list of data that are within the threshold bounds of
     * data1 and data2. That is, the data should be > data1 and < data2.
     *
     * This must be done recursively.
     *
     * Should run in worst case O(n), but this is heavily dependent on the
     * threshold data. You should not explore branches of the tree that do not
     * satisfy the threshold.
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * sortedInBetween(7, 14) returns [8, 9, 10, 13]
     * sortedInBetween(3, 8) returns [4, 5, 6, 7]
     * sortedInBetween(8, 8) returns []
     *
     * @param data1 the smaller data in the threshold
     * @param data2 the larger data in the threshold
     * @return a sorted list of data that is > data1 and < data2
     * @throws IllegalArgumentException if data1 or data2 are null
     * or if data1 > data2
     */
    public List<T> sortedInBetween(T data1, T data2) {
        if (data1 == null || data2 == null || data1.compareTo(data2) > 0) {
            throw new IllegalArgumentException("Either one of the data is null or the first number is "
                    + "greater than the second.");
        }
        List<T> output = new ArrayList<>();
        rSortedInBetween(root, data1, data2, output);
        return output;
    }

    /**
     * Recursive helper for sortedInBetween().
     * @param curr the current node
     * @param data1 smaller data in threshold
     * @param data2 larger data in threshold
     * @param output sorted list of data > data 1 and < data 2
     */
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

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
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

    /**
     * Right rotation with the root.
     * @param y the root node
     * @return the new root
     */
    private AVLNode<T> rotateRight(AVLNode<T> y) {
        AVLNode<T> x = y.getLeft();
        AVLNode<T> z = x.getRight();
        y.setLeft(z);
        x.setRight(y);
        update(y);
        return x;
    }

    /**
     * Left rotation with the root.
     * @param y the root node
     * @return the new root
     */
    private AVLNode<T> rotateLeft(AVLNode<T> y) {
        AVLNode<T> x = y.getRight();
        AVLNode<T> z = x.getLeft();
        y.setRight(z);
        x.setLeft(y);
        update(y);
        return x;
    }
}
