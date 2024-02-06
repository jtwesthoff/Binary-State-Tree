// CLIENT PROGRAM

import java.util.Random;

public class BSTTest {
    public static void main(String[] args)
    {

        // Testing insertBalanced and inorderByLevel

        System.out.println("TREE #1\n");
        Integer [] array = new Integer[31];

        for (int i = 0; i < 31; i++)
            array[i] = i+1;

        BST<Integer> tree1 = new BST<>();

        tree1.insertBalanced(array);
        tree1.inOrder();
        System.out.println();

        for (int level = 0; level < 5; level++)
        {
            int count = tree1.depthCount(level);
            System.out.println("Number of nodes at level " + level + " = " + count);
        }
        System.out.println("\nBST-1 inorder by levels");
        for (int d = 0; d < 6; d++)
        {
            System.out.printf("Level %d ",d);
            tree1.inorderByLevel(d);
        }
        System.out.println();

        int leaves = tree1.leafCount();
        System.out.println("Number of leaves = " + leaves);

        System.out.println("Root Key = " + tree1.rootKey());

        tree1.remove(tree1.rootKey());
        tree1.inOrder();

        System.out.println("Root Key = " + tree1.rootKey());


        // General cases 1,2,3 removal

        System.out.println("\nTREE #2\n");
        BST<Integer> tree2 = new BST<>();
        Integer [] array2 = {50,40,60,30,43,53,41,45,51,58,48,57,47};

        for (int i = 0; i < 13; i++)
            tree2.insert(array2[i]);
        tree2.inOrder();

        System.out.println("\nBST-2 inorder by levels");
        for (int d = 0; d < 6; d++)
        {
            System.out.printf("Level %d ",d);
            tree2.inorderByLevel(d);
        }
        System.out.println();

        int leaves2 = tree2.leafCount();
        System.out.printf("Number of leaves = %d%n",leaves2);
        System.out.printf("Root Key = %d%n",tree2.rootKey());

        tree2.remove(51);
        System.out.println("\nRemove leaf 51");
        tree2.inOrder();

        tree2.remove(60);
        System.out.println("\nRemove one-child node 60");
        tree2.inOrder();

        tree2.remove(43);
        System.out.println("\nRemove 2-child node 43");
        tree2.inOrder();

        tree2.remove(50);
        System.out.println("\nRemove 2-child root 50");
        tree2.inOrder();

        System.out.printf("Root Key = %d%n",tree2.rootKey());
        leaves2 = tree2.leafCount();
        System.out.printf("\nNumber of leaves = %d%n",leaves2);


        //Create a balanced BST with 1,000,000+ nodes

        System.out.println("\nTREE #3\n");

        int bigSize = 1024*1024-1;

        Integer [] array3 = new Integer[bigSize];
        BST<Integer> tree3 = new BST<>();

        for (int i = 0; i < bigSize; i++)
            array3[i] = i+1;

        tree3.insertBalanced(array3);

        for (int level = 0; level < 20; level++)
        {
            int count = tree3.depthCount(level);
            System.out.printf("Number of nodes at level %d = %,d%n",level, count);
        }

        //Empty BST and root removal

        System.out.println("\nTREE #4\n");
        BST<Integer> tree4 = new BST<>();

        tree4.inOrder();
        System.out.println("Trying to remove 32");
        tree4.remove(32);
        tree4.inOrder();

        tree4.insert(32);
        System.out.println("\nTrying to remove 32");
        tree4.remove(32);
        tree4.inOrder();

        for (int i = 2; i < 10; i+=2)
            tree4.insert(i);

        tree4.inOrder();
        System.out.println("\nTrying to remove 2");
        tree4.remove(2);
        tree4.inOrder();


        // Odds and Evens BST

        System.out.println("\nTREE #5\n");
        BST<Integer> tree5 = new BST<>();

        Integer [] array5 = new Integer[15];

        for (int i = 0; i < 15; i++)
            array5[i] = i+1;

        tree5.insertBalanced(array5);

        tree5.inOrder();
        System.out.println("root = " + tree5.rootKey());

        for (int key = 2; key < 15; key += 2)
            tree5.remove(key);

        tree5.inOrder();
        System.out.println("root = " + tree5.rootKey());
        for (int key = 1; key < 15; key += 2)
            tree5.remove(key);

        tree5.inOrder();
        System.out.println("root = " + tree5.rootKey());


        // Optional BST to determine the height of the tree
        // This section can be optionally uncommented



        System.out.println("\nOPTIONALTREE CHALENGE\n");
        System.out.println("\nDetermine the height of the BST\n");

        System.out.println("Height of tree2 is " + tree2.height() + "\n");

        System.out.println("Height of tree3 is " + tree3.height() + "\n");

        System.out.println("Height of tree5 is " + tree5.height() + "\n");

        for (int key = 1; key < 100; key++ )
            tree2.remove(key);

        System.out.println("Height of tree2 is " + tree2.height() + "\n");

    }
}