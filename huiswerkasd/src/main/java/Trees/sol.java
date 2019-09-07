package Trees;

public class sol {

    public static void main(String[] args){
        BinaryTree<Integer> bst = new BinaryTree<Integer>();
        bst.addNode(5);
        bst.addNode(6);
        bst.addNode(2);
        bst.addNode(3);
        bst.addNode(4);
        bst.inOrderTraverseTree(bst.findNode(5));


        System.out.print("Ssss");
    }
}
