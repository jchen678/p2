// starter class for a BalancedSearchTree
// you may implement AVL, Red-Black, 2-3 Tree, or 2-3-4 Tree
// be sure to include in class header which tree you have implemented
public class BalancedSearchTree<T extends Comparable<T>> implements SearchTreeADT<T> {

    // inner node class used to store key items and links to other nodes
    protected class Treenode<K extends Comparable<K>> {

        public Treenode(K item, boolean color) {
            this(item);
            this.color = color;
        }

        public Treenode(K item) {
            this(item,null,null);
            color = true;
        }
        public Treenode(K item, Treenode<K> left, Treenode<K> right) {
            color = true;
            key = item;
            this.left = left;
            this.right = right;
        }
        K key;
        Treenode<K> left;
        Treenode<K> right;
        Treenode<K> parent = null;
        //red = true, black = false
        boolean color = true;
        
        public String toString() {
            return key.toString();
        }
    }

    protected Treenode<T> root;

    public String inAscendingOrder() {
        //TODO : must return comma separated list of keys in ascending order
        return "" ;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int height() {
        //TODO return the height of this tree
        return heightHelper(root);
    }
    
    private int heightHelper(Treenode<T> node) {
        if (node == null) {
            return 0;
        }
        else {
            int leftHeight = heightHelper(node.left);
            int rightHeight = heightHelper(node.right);
            
            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            }
            else {
                return rightHeight + 1;
            }
        }
    }

    public boolean lookup(T item) {
        //TODO must return true if item is in tree, otherwise false
        return false;
    }

    public void insert(T item) {
        //TODO if item is null throw IllegalArgumentException, 
        // otherwise insert into balanced search tree
        if (item == null) throw new IllegalArgumentException();

        if (isEmpty()) {
            root = new Treenode<T>(item);
            root.color = false;
        }
        else {
            Treenode<T> node = BSTInsert(item, root);
            balance(node);
            root.color = false;
        }
    }

    private void balance(Treenode<T> node) {
        Treenode<T> u = getUncle(node);

        if (node == root) {
            root.color = false;
            return;
        }
        
        if (node.parent.color != false) {
            if (u == null || u.color == false) { //if sibling is black or null, TNR
                if (node.parent.parent.left == node.parent) {
                    //left left
                    if (node.parent.left == node) {
                        rightRotate(node.parent.parent);
                        
                        //swap colors of parent and grandparent
                        boolean temp = node.parent.right.color;
                        node.parent.right.color = node.parent.color;
                        node.parent.color = temp;
                    }
                    //left right
                    else {
                        leftRotate(node.parent);
                        rightRotate(node.left); //parent
                        
                        //swap colors of parent and grandparent
                        boolean temp = node.color;
                        node.color = node.right.color;
                        node.right.color = temp;
                    }
                }
                else {
                    //right left
                    if (node.parent.left == node) {
                        rightRotate(node.parent);
                        leftRotate(node.left);
                        
                        //swap colors of parent and grandparent
                        boolean temp = node.color;
                        node.color = node.left.color;
                        node.left.color = temp;
                    }
                    //right right
                    else {
                        leftRotate(node.parent.parent);
                        
                        //swap colors of parent and grandparent
                        boolean temp = node.parent.left.color;
                        node.parent.left.color = node.parent.color;
                        node.parent.color = temp;
                    }
                }

            }
            else if (node.parent.color == true) { //if parent is red
                if (u.color == true) { //if sibling is red, recoloring
                    //change parent and sibling black, gp red
                    node.parent.color = false;
                    u.color = false;
                    node.parent.parent.color = true;
                    balance(node.parent.parent);
                }
            }
        }
    }

    private void rightRotate(Treenode<T> node) {
        Treenode<T> left = node.left;
        Treenode<T> leftRightChild = node.left.right;
        
        left.right = node;
        node.left = leftRightChild;
        
        if (node.left != null) {
            node.left.parent = node;
        }
        left.parent = node.parent;
        node.parent = left;
        
        
        if (left.parent == null) {
            root = left;
        }
        else {
            if (left.parent.left == node) {
                left.parent.left = left;
            }
            else {
                left.parent.right = left;
            }
        }
        
    }

    private void leftRotate(Treenode<T> node) {
        Treenode<T> right = node.right;
        Treenode<T> rightLeftChild = node.right.left;
        
        right.left = node;
        node.right = rightLeftChild;
        
        
        if (node.right != null) {
            node.right.parent = node;
        }
        right.parent = node.parent;
        node.parent = right;
        
        if (right.parent == null) {
            root = right;
        }
        else {
            if (right.parent.left == node) {
                right.parent.left = right;
            }
            else {
                right.parent.right = right;
            }
        }
    }

    private Treenode<T> getUncle(Treenode<T> node) {
        Treenode<T> u;
        
        if (node == root || node.parent.parent == null) {
            return null;
        }
        
        if (node.parent.parent.right == node.parent) {
            u = node.parent.parent.left;
        }
        else {
            u = node.parent.parent.right;
        }

        return u;
    }

    private Treenode<T> BSTInsert(T item, Treenode<T> node) {
        if (item.compareTo(node.key) > 0) {
            if (node.right == null) {
                Treenode<T> newNode = new Treenode<T>(item);
                newNode.parent = node;
                node.right = newNode;
                return newNode;
            }
            else {
                return BSTInsert(item, node.right);
            }
        }
        else {
            if (node.left == null) {
                Treenode<T> newNode = new Treenode<T>(item);
                newNode.parent = node;
                node.left = newNode;
                return newNode;
            }
            else {
                return BSTInsert(item, node.left);
            }
        }
    }

    public void delete(T item) {
        //TODO if item is null or not found in tree, return without error
        // else remove this item key from the tree and rebalance

        // NOTE: if you are unable to get delete implemented
        // it will be at most 5% of the score for this program assignment
        
        BSTDelete(item, root);
    }
    
    private Treenode<T> BSTDelete(T item, Treenode<T> node) {
        
        if (item.compareTo(node.key) > 0) {
            System.out.println("searching right...");
            node.right = BSTDelete(item, node.right);
        }
        else if (item.compareTo(node.key) < 0) {
            System.out.println("searching left...");
            node.left = BSTDelete(item, node.left);
        }
        else {
            //1 or 0 children
            if (node.left == null && node.right == null) {
                System.out.println("no children, removing..");
                node = null;
            }
            else if (node.left == null) {
                return node.right;
            }
            else if (node.right == null){
                return node.left;
            }
            else {
                node.key = getSuccessor(node);
                node.right = BSTDelete(node.key, node.right);
            }
        }
        
        return node;
    }
    
    private T getSuccessor(Treenode<T> node) {
        T value = node.key;
        node = node.right;
        while (node != null) {
            value = node.key;
            node = node.left;
        }
        
        return value;
    }


    // HINT: define this helper method that can find the smallest key 
    // in a sub-tree with "node" as its root
    // PRE-CONDITION: node is not null
    private T leftMost(Treenode<T> node) {
        // TODO return the key value of the left most node in this subtree
        // or return node's key if node does not have a left child
        return node.key;
    }
    
    public String toString() {
        printLevelOrder(root);
        return "";
    }
    
    private void printLevelOrder(Treenode<T> root)
    {
        int h = height();
        int i;
        for (i=1; i<=h; i++)
        {
            printGivenLevel(root, i);
            System.out.println();
        }
    }
    /* Print nodes at a given level */
    private void printGivenLevel(Treenode<T> root, int level)
    {
        if (root == null) {
            System.out.print("-" + "\t");
            return;
        }
        if (level == 1) {
            if (root.color == true) {
                System.out.print(root + "' \t");
            }
            else {
                System.out.print(root + "; \t");
            }
        }
            
        else if (level > 1)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }

}

