
/*
 * Name: Yin Lam Lai
 * PID:  A15779757
 */
import java.lang.reflect.Array;
import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Yin Lam Lai
 * @since  7/5/2020
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node
    private static int LEVELER = 2;

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.dataList = dataList;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.dataList = new LinkedList<T>();
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setleft(BSTNode newleft) {
            left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setright(BSTNode newright) {
            right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            for (int n = 0; n < dataList.size(); n++) {
                if (data == dataList.get(n)) {
                    dataList.remove(n);
                    return true;
                }
            }
            return false;
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        nelems = 0;
        root = null;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     */
    public boolean insert(T key) {
        if (key == null) {
            throw new NullPointerException();
        }

        if (nelems == 0) {
            root = new BSTNode(null, null, key);
            nelems++;
        }
        BSTNode temp = root;

        if (!this.findKey(key)) {
            while (temp != null) {
                if (key.compareTo(temp.getKey()) == -1) {
                    if (temp.getLeft() == null) {
                        temp.setleft(new BSTNode(null, null, key));
                        nelems++;
                        return true;
                    } else {
                        temp = temp.getLeft();
                    }
                } else {
                    if (temp.getRight() == null) {
                        temp.setright(new BSTNode(null, null, key));
                        nelems++;
                        return true;
                    } else {
                        temp = temp.getRight();
                    }
                }
            }
        }
        return false;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        BSTNode temp = getRoot();
        while (temp != null) {
            if (key.equals(temp.getKey())) {
                return true;
            } else if (key.compareTo(temp.getKey()) == -1) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        return false;
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If eaither key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }

        BSTNode temp = getRoot();
        while (temp != null) {
            if (key.equals(temp.getKey())) {
                temp.getDataList().add(data);
                break;

            } else if (key.compareTo(temp.getKey()) == -1) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }

    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }

        BSTNode temp = root;
        while (temp != null) {
            if (key.equals(temp.getKey())) {
                break;
            } else if (key.compareTo(temp.getKey()) == -1) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        return temp.getDataList();
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null) {
            return -1;
        } else {
            int heightLeft = findHeightHelper(root.getLeft());
            int heightRight = findHeightHelper(root.getRight());

            if (heightLeft > heightRight) {
                return heightLeft + 1;
            } else {
                return heightRight + 1;
            }
        }
    }

    /**
     * Return the number of leaf nodes in the tree
     *
     * @return The number of leaf nodes in the tree
     */
    public int leafCount() {
        return leafCountHelper(root);
    }

    /**
     * Helper for the leafCount method
     *
     * @param root Root node
     * @return The number of leaf nodes in the tree
     */
    private int leafCountHelper(BSTNode root) {
        if (root == null) {
            return 0;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        } else {
            return leafCountHelper(root.getLeft()) + leafCountHelper(root.getRight());
        }
    }

    /* * * * * BST Iterator * * * * */

    public class BSTree_Iterator implements Iterator<T> {
        Stack<T> stack;
        public BSTree_Iterator() {
            stack = new Stack<>();
            BSTNode temp = getRoot();
            while (temp != null) {
                stack.push(temp.getKey());
                temp = temp.getLeft();
            }
        }

        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }
            return true;
        }

        public T next() {
            if (stack.isEmpty()) {
                throw new NoSuchElementException();
            }

            T popped = stack.pop();
            BSTNode treeNode = getRoot();
            while (treeNode.getKey() != popped) {
                if (popped.compareTo(treeNode.getKey()) == -1) {
                    treeNode = treeNode.getLeft();
                } else {
                    treeNode = treeNode.getRight();
                }
            }
            if (treeNode.getRight() == null) {
                return popped;
            } else {
                BSTNode current = treeNode.getRight();
                while (current != null) {
                    stack.push(current.getKey());
                    current = current.getLeft();
                }
                return popped;
            }
        }
    }

    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        ArrayList<T> iter1List = new ArrayList<>();
        ArrayList<T> iter2List = new ArrayList<>();
        while (iter1.hasNext()) {
            iter1List.add(iter1.next());
        }
        while (iter2.hasNext()) {
            iter2List.add(iter2.next());
        }
        ArrayList<T> finalList = new ArrayList<>();
        Collections.sort(iter1List);
        for (T n : iter1List) {
            if (iter2List.contains(n)) {
                finalList.add(n);
            }
        }

        return finalList;
    }

    public int levelCount(int level) {
        if (level > this.findHeight()) {
            return - 1;
        } else if (level == 0){
            return 1;
        } else {
            int count  = 0;
            for (int n = 1; n < level; n++) {
                count += n * LEVELER;
            }
            count++;
            count = this.getSize() - count ;
            return level * LEVELER - count;
        }
    }
}
