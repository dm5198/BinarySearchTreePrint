package hw3.com;

import net.datastructures.*;
import java.util.Comparator;

public class MyAVLTreeMap<K, V> extends TreeMap<K, V> {

    /** Constructs an empty map using the natural ordering of keys. */
    public MyAVLTreeMap() {
        super();
    }

    /**
     * Constructs an empty map using the given comparator to order keys.
     * @param comp comparator defining the order of keys in the map
     */
    public MyAVLTreeMap(Comparator<K> comparator) {
        super(comparator);
    }

    /** Returns the height of the given tree position. */
    protected int getNodeHeight(Position<Entry<K, V>> position) {
        return tree.getAux(position);
    }

    /** Recomputes the height of the given position based on its children's heights. */
    protected void recomputeNodeHeight(Position<Entry<K, V>> position) {
        tree.setAux(position, 1 + Math.max(getNodeHeight(left(position)), getNodeHeight(right(position))));
    }

    /** Returns whether a position has balance factor between -1 and 1 inclusive. */
    protected boolean isNodeBalanced(Position<Entry<K, V>> position) {
        return Math.abs(getNodeHeight(left(position)) - getNodeHeight(right(position))) <= 1;
    }

    /** Returns a child of position with height no smaller than that of the other child. */
    protected Position<Entry<K, V>> getTallerChild(Position<Entry<K, V>> position) {
        if (getNodeHeight(left(position)) > getNodeHeight(right(position))) {
            return left(position);
        } else if (getNodeHeight(left(position)) < getNodeHeight(right(position))) {
            return right(position);
        } else {
            if (isRoot(position)) {
                return left(position);
            } else if (position == left(parent(position))) {
                return left(position);
            } else {
                return right(position);
            }
        }
    }

    /**
     * Utility used to rebalance after an insert or removal operation. This traverses the
     * path upward from position, performing a trinode restructuring when imbalance is found,
     * continuing until balance is restored.
     */
    protected void rebalance(Position<Entry<K, V>> position) {
        int oldHeight, newHeight;
        do {
            oldHeight = getNodeHeight(position); // not yet recalculated if internal
            if (!isNodeBalanced(position)) { // imbalance detected
                // perform trinode restructuring, setting position to resulting root,
                // and recompute new local heights after the restructuring
                position = restructure(getTallerChild(getTallerChild(position)));
                recomputeNodeHeight(left(position));
                recomputeNodeHeight(right(position));
            }
            recomputeNodeHeight(position);
            newHeight = getNodeHeight(position);
            position = parent(position);
        } while (oldHeight != newHeight && position != null);
    }

    /** Overrides the TreeMap rebalancing hook that is called after an insertion. */
    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> position) {
        rebalance(position);
    }

    /** Overrides the TreeMap rebalancing hook that is called after a deletion. */
    @Override
    protected void rebalanceDelete(Position<Entry<K, V>> position) {
        if (!isRoot(position)) {
            rebalance(parent(position));
        }
    }

    /** Ensure that the current tree structure is a valid AVL (for debug use only). */
    private boolean sanityCheck() {
        for (Position<Entry<K, V>> position : tree.positions()) {
            if (isInternal(position)) {
                if (position.getElement() == null) {
                    System.out.println("VIOLATION: Internal node has null entry");
                } else if (getNodeHeight(position) != 1 + Math.max(getNodeHeight(left(position)), getNodeHeight(right(position)))) {
                    System.out.println("VIOLATION: AVL unbalanced node with key " + position.getElement().getKey());
                    dump();
                    return false;
                }
            }
        }
        return true;
    }
    private void recursivePrintTree(Position<Entry<K, V>> position, int vertical, int horizontal, int horizontalLength, char[][] matrix, boolean verbose) {
        if (position.getElement() == null) {
            return;
        }
        K key = position.getElement().getKey();
        char keyChar = key.toString().charAt(0);
        // Insert the key into the matrix
        matrix[vertical][horizontal] = keyChar;
        // Print if verbose
        if (verbose) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    char currentChar = matrix[i][j];
                    if (currentChar != 0) {
                        System.out.print(matrix[i][j]);
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println("");
            }
        }
        // Check if the position has a left child
        if (left(position).getElement() != null) {
            // Insert the left branch
            matrix[vertical + 1][horizontal - (horizontalLength / 2)] = '/';
            Position<Entry<K, V>> leftChild = left(position);
            int leftVertical = vertical + 2;
            int leftHorizontal = horizontal - horizontalLength;
            recursivePrintTree(leftChild, leftVertical, leftHorizontal, horizontalLength / 2, matrix, verbose);
        }
        // Check if the position has a right child
        if (right(position).getElement() != null) {
            // Insert the right branch
            matrix[vertical + 1][horizontal + (horizontalLength / 2)] = '\\';
            Position<Entry<K, V>> rightChild = right(position);
            int rightVertical = vertical + 2;
            int rightHorizontal = horizontal + horizontalLength;
            recursivePrintTree(rightChild, rightVertical, rightHorizontal, horizontalLength / 2, matrix, verbose);
        }
    }

    /**
     * Prints the AVL tree structure.
     * @param verbose true to print the tree at each step, false to only print the final tree
     */
    public void printTree(boolean verbose) {
        // Create matrix to print on
        char[][] matrix = new char[12][128];
        // Initialize at the top of the tree
        Position<Entry<K, V>> root = this.root();
        int currentVertical = 0;
        int currentHorizontal = 32;
        recursivePrintTree(root, currentVertical, currentHorizontal, 16, matrix, verbose);

        // Print the tree
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                char currentChar = matrix[i][j];
                if (currentChar != 0) {
                    System.out.print(matrix[i][j]);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    
}

