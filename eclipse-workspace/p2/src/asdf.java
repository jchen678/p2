
public class asdf {
    public static void main(String[] args) {
        BalancedSearchTree<Integer> tree = new BalancedSearchTree<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.delete(3);
        
        System.out.println(tree);
    }
}