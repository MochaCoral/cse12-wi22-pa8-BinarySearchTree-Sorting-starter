/**
 * Name: Morales, Kyle
 * ID: A16162998
 * Email: kmmorale@ucsd.edu
 * Sources used: Zybooks, course
 * 
 * Implementation of binary search tree and binary search tree node classes
 */
import java.util.ArrayList;


public class MyBST<K extends Comparable<K>,V>{
    MyBSTNode<K,V> root = null;
    int size = 0;

    public int size(){
        return size;
    }

    public V insert(K key, V value) throws NullPointerException{
        MyBSTNode<K,V> node = new MyBSTNode<K,V>(key, value, null);
        if(key == null) {
            throw new NullPointerException();
        }
        else if(this.root == null) {
            this.root = node;
            return null;
        }
        else {
            MyBSTNode<K,V> curr = root;
            while(curr != null) {
                if(node.getKey().compareTo(curr.getKey()) < 0) {
                    if(curr.getLeft() == null) {
                        node.setParent(curr);
                        curr.setLeft(node);
                        return null;
                    }
                    else {
                        curr = curr.getLeft();
                    }
                }
                else if(node.getKey().compareTo(curr.getKey()) > 0){
                    if(curr.getRight() == null) {
                        node.setParent(curr);
                        curr.setRight(node);
                        return null;
                    }
                    else {
                        curr = curr.getRight();
                    }
                }
                else {

                    return value;
                }
            }
            return value;
        }
    }

    public V search(K key){
        MyBSTNode<K,V> curr = this.root;
        while(curr != null) {
            if(key.compareTo(curr.getKey()) == 0) {
                return curr.getValue();
            } 
            else if (key.compareTo(curr.getKey()) < 0) {
                curr = curr.getLeft();
            }
            else {
                curr = curr.getRight();
            }
        }
        return null;
    }

    public V remove(K key){
        if(key == null) {
            return null;
        }
        MyBSTNode<K,V> par = null;
        MyBSTNode<K,V> cur = this.root;
        while(cur != null) { // Search for node
            if (cur.getKey() == key) { // Node found 
                V curValue = cur.getValue();
                if (cur.getLeft() == null && cur.getRight() == null) { // Remove leaf
                    if (par == null) { // Node is root
                        this.root = null;
                    }
                    else if (par.getLeft() == cur) {
                        par.setRight(null);
                    }
                    else {
                        par.setRight(null);
                    }
                }
                else if (cur.getRight() == null) {                // Remove node with only left child
                    if (par == null) {// Node is root
                       this.root = cur.getLeft();
                    }
                    else if (par.getLeft() == cur) {
                        par.setLeft(cur.getLeft());
                    }
                    else {
                        par.setRight(cur.getLeft());
                    }
                }
                else if (cur.getLeft() == null) {                // Remove node with only right child
                    if (par == null) {// Node is root
                        this.root = cur.getRight();
                    }
                    else if (par.getLeft() == cur) {
                        par.setLeft(cur.getRight());
                    }
                    else {
                        par.setRight(cur.getRight());
                    }
                }
                else {                                      // Remove node with two children
                // Find successor (leftmost child of right subtree)
                    MyBSTNode<K,V> suc = cur.getRight();
                    while (suc.getLeft() != null) {
                        suc = suc.getLeft();
                    }
                    V successorValue = suc.getValue();
                    remove(suc.getKey());     // Remove successor
                    cur.setValue(successorValue);
                }
                return curValue;// Node found and removed
            }
            else if (cur.getKey().compareTo(key) < 0) { // Search right
                par = cur;
                cur = cur.getRight();
            }
            else {                     // Search left
                par = cur;
                cur = cur.getLeft();
            }
        }
        return null;// Node not found
    }
    
    public ArrayList<MyBSTNode<K, V>> inorder(){
        ArrayList<MyBSTNode<K,V>> inorderArray = new ArrayList<MyBSTNode<K,V>>();
        if(this.root == null) {
            return inorderArray;
        }
        else {
            MyBSTNode<K,V> curr = this.root;
            while(curr.getLeft() != null) {
                curr = curr.getLeft();
            }
            inorderArray.add(curr);
            while(inorderArray.size() < this.size) {
                inorderArray.add(curr.successor());
                curr = curr.successor();
            }
            return inorderArray;
        }
    }

    static class MyBSTNode<K,V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K,V> parent;
        private MyBSTNode<K,V> left = null;
        private MyBSTNode<K,V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent; 
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey(){
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue(){
            return value;
        }

        /**
         * Return the parent
         * @return the parent
         */
        public MyBSTNode<K,V> getParent(){
            return parent;
        }

        /**
         * Return the left child 
         * @return left child
         */
        public MyBSTNode<K,V> getLeft(){
            return left;
        }

        /**
         * Return the right child 
         * @return right child
         */
        public MyBSTNode<K,V> getRight(){
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * @param newKey the key to be stored
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * @param newValue the data to be stored
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Set the parent
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Set the left child
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Set the right child
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /**
         * TODO: add inline comments for this method to demonstrate your
         *   understanding of this method. The predecessor can be implemented
         *   in a similar way.
         *
         * This method returns the in order successor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor(){
            if(this.getRight() != null){
                MyBSTNode<K,V> curr = this.getRight();
                while(curr.getLeft() != null){
                    curr = curr.getLeft();
                }
                return curr;
            }
            else{
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                while(parent != null && curr == parent.getRight()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        public MyBSTNode<K, V> predecessor(){
            if(this.getLeft() != null) {
                MyBSTNode<K,V> curr = this.getLeft();
                while(curr.getRight() != null) {
                    curr = curr.getRight();
                }
                return curr;
            }
            else {
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                while(parent != null && curr == parent.getLeft()) {
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /** This method compares if two node objects are equal.
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj){
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K,V> comp = (MyBSTNode<K,V>)obj;
            
            return( (this.getKey() == null ? comp.getKey() == null : 
                this.getKey().equals(comp.getKey())) 
                && (this.getValue() == null ? comp.getValue() == null : 
                this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * @return "Key:Value" that represents the node object
         */
        public String toString(){
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}