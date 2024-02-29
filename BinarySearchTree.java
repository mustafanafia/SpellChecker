import java.util.ArrayList;
import java.util.Iterator;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    /** Create a default binary tree */
    public BinarySearchTree() {
    }       //binary search tree

    /** Create a binary tree from an array of objects */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }                               //BinarySearchTree

    /** Returns true if the element is in the tree */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }                                                       //if
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }//else if
            else // element matches current.element
                return true; // Element is found
        }//while
        return false;
    }//boolean search

    /** Insert element o into the binary tree
     * Return true if the element is inserted successfully.
     * Uses an iterative algorithm
     */
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
// Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }                           //if
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }//else if
                else
                    return false; // Duplicate node not inserted

// Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }                                           //else
        size++;
        return true; // Element inserted
    }               //boolean insert

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }               //trade node

    /** Inorder traversal from the root*/
    public void inorder() {
        inorder(root);
    }                       //inorder

    /** Inorder traversal from a subtree */
    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }                                               //inorder


    /** Postorder traversal from the root */
    public void postorder() {
        postorder(root);
    }                       //postorder

    /** Postorder traversal from a subtree */
    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }                                       //postorder

    /** Preorder traversal from the root */
    public void preorder() {
        preorder(root);
    }                                       //preorder

    /** Preorder traversal from a subtree */
    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }                                   //preorder

    /** Inner class tree node */
    public static class TreeNode<E extends Comparable<E>> {
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }                       //tree node
    }               //class tree node

    /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }                   //getsize

    /** Returns the root of the tree */
    public TreeNode getRoot() {
        return root;
    }           //get root

    /** Returns an ArrayList containing elements in the path from the root leading to the specified element, returns an empty ArrayList if no such element exists. */
    public ArrayList<E> path(E e){
        ArrayList<E> list = new ArrayList<>();
//implement the code here as in search method.
        path(e,list,this.root);
        if(list.get(list.size()-1).compareTo(e)==0)return list;
        else list.clear();
        return list; // Return an array of elements
    }                   //arraylist


    private ArrayList<E> path(E e, ArrayList<E> list,TreeNode<E> current){
        if(current==null) return list;
        else if (e.compareTo(current.element) > 0) path(e, list, current.right);
        else if (e.compareTo(current.element) < 0) path(e, list, current.left);
        list.add(0, current.element);
        return list;
    }                   //arraylist


    /* Returns the number of leaf nodes in this tree, returns 0 if tree is empty*/
    public int getNumberOfLeaves(){
//left for you to implement in Lab 7
        ArrayList<E> list=new ArrayList<>();
        getNumberOfLeaves(this.root,list);
        return list.size();
    }               //get number of leaves

    public ArrayList<E> getNumberOfLeaves(TreeNode<E> current,ArrayList<E> list){
        if (current.right==null && current.left==null){
            list.add(0,current.element);
            return list;
        }                                           //get number of leaves
        getNumberOfLeaves(current.left,list);
        getNumberOfLeaves(current.right,list);
        return list;
    }                   //arraylist

    /* Returns an ArrayList containing all elements in preorder of the specified element’s left sub-tree, returns an empty ArrayList if no such element exists. */
    public ArrayList<E> leftSubTree(E e){
//left for you to implement in Lab 7
        ArrayList<E> list=new ArrayList<>();
        if(this.search(e)){
            TreeNode<E> current=this.root;
            while (current.element.compareTo(e)!=0){
                if(current.element.compareTo(e)>0){
                    current=current.right;
                }       //if
                else if(current.element.compareTo(e)<0){
                    current=current.left;
                }//else if
            } //while
            current=current.left;
            getPreOrder(current,list);
        } //if
        return list;
    } //arraylist

    /* Returns an ArrayList containing all elements in preorder of the specified element’s right sub-tree, returns an empty ArrayList if no such element exists. */
    public ArrayList<E> rightSubTree(E e){
//left for you to implement in Lab 7
        ArrayList<E> list=new ArrayList<>();

        if(this.search(e)){
            TreeNode<E> current=this.root;
            while (current.element.compareTo(e)!=0){
                if(current.element.compareTo(e)>0){
                    current=current.right;
                }               //if
                else if(current.element.compareTo(e)<0){
                    current=current.left;
                }           //else if
            }               //while
            current=current.right;
            getPreOrder(current,list);
        }           //if

        return list;
    }   //arraylist

    /* Returns the inorder predecessor of the specified element, returns null if tree is empty or element 'e' is not in the tree. */

    public E inorderPredecessor(E e){
//left for you to implement in Lab 7
        ArrayList<E> inorder=new ArrayList<>();
        getInorder(e,this.root,inorder);
        for (int i = 1; i < inorder.size(); i++) {
            if(e.compareTo(inorder.get(i))==0) return inorder.get(i-1);
        }                                                                 //for
        return null;
    }   //in order

    private void getInorder(E e, TreeNode<E> current, ArrayList<E> list){
        if(current!=null) {
            getInorder(e,current.left, list);
            list.add(list.size(), current.element);
            getInorder(e,current.right, list);
        } //if
    } // get in order

    private void getPreOrder(TreeNode<E> current,ArrayList<E> list){
        if (current == null)return;
        list.add(list.size(),current.element);
        getPreOrder(current.left,list);
        getPreOrder(current.right,list);

    }               //get pree order


    /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E e) {
// Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }                               //if
            else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }//else if
            else
                break; // Element is in the tree pointed by current
        } //while
        if (current == null)
            return false; // Element is not in the tree
// Case 1: current has no left children
        if (current.left == null) {
// Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            }   //if
            else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }//else
        }//else
        else {
// Case 2 & 3: The current node has a left child
// Locate the rightmost node in the left subtree of
// the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            } //while
// Replace the element in current by the element in rightMost
            current.element = rightMost.element;

// Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
// Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        } //else
        size--;
        return true; // Element inserted
    } //delete

    /** Obtain an iterator. Use inorder. */
    public Iterator iterator() {
        return inorderIterator();
    } //iterator

    /** Obtain an inorder iterator */
    public Iterator inorderIterator() {
        return new InorderIterator();
    } //inorderIterator

    // Inner class InorderIterator
    class InorderIterator implements Iterator {
        // Store the elements in a list
        private ArrayList<E> list = new ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        } //InorderIterator

        /** Inorder traversal from the root*/
        private void inorder() {
            inorder(root);
        }//inorder

        /** Inorder traversal from a subtree */
        private void inorder(TreeNode<E> root) {
            if (root == null)return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        } //in order

        /** Next element for traversing? */
        public boolean hasNext() {
            if (current < list.size())
                return true;
            return false;
        } //has next

        /** Get the current element and move cursor to the next */
        public Object next() {
            return list.get(current++);
        } //next

        /** Remove the current element and refresh the list */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        } //remove
    } ////if

    /** Remove all elements from the tree */
    public void clear() {
        root = null;
        size = 0;
    }
}


//clear