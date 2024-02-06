public class BST <T extends Comparable<T>>
{

    private BSTNode<T> root;

    public BST()
    {
        root = null;
    }

    public T rootKey(){
        if(root == null){
            return null;
        }
        return root.data;
    }


    public void inOrder()
    {
        System.out.print("[ ");
        inOrder(root);
        System.out.println("]");
        System.out.println();
    }


    private void inOrder(BSTNode<T> cur)
    {
        if (cur != null)
        {
            inOrder(cur.getLeft());
            System.out.printf("%s ", cur.data);
            inOrder(cur.getRight());
        }
    }

    public boolean insert(T data)
    {
        // nothing in the tree add new node and connect it to the root

        if (root == null)
        {
            root = new BSTNode(data);
            return true;
        }

        // tree not empty, search for a prev to connect to

        BSTNode<T> prev = search(data);

        // there is a root node, so prev cannot be null
        // UNLESS the new key == root key
        // since we are not allowing duplicates, when prev is null return false
        // meaning failure to insert

        if (prev == null)
            return false;


        if (data.compareTo(prev.getData()) < 0)  // new key < prev key
        {
            // always true unless key is a duplicate
            if (prev.left == null)
            {
                BSTNode<T> node = new BSTNode<T>(data);
                prev.setLeft(node);
                return true;
            }
        }
        else  // new key > prev key
        {
            // always true unless key is a duplicate
            if (prev.right == null)
            {
                BSTNode<T> node = new BSTNode(data);
                prev.setRight(node);
                return true;
            }
        }

        return false;  // because found duplicate
    }

    // If we never call search with an empty tree, we will never
    // return prev with a null value

    // To accomplish this we will check in insert and in remove if the root
    // is null. Insert will simply add a new node at the root, and remove will
    // fail since it cannot remove anything from an empty tree

    public BSTNode<T> search(T data)
    {
        BSTNode<T> cur = root;
        BSTNode<T> prev = null;

        while (cur != null)
        {
            if (data.compareTo(cur.data) < 0)
            {
                prev = cur;
                cur = cur.left;
            }
            else
            if (data.compareTo(cur.data) > 0)
            {
                prev = cur;
                cur = cur.right;
            }
            else               // cur.data is not <, not >, so must be EQUAL return prev
                return prev;  // to the matched cur
        }

        return prev;
    }

    public boolean remove(T key)
    {
        if (root == null)
            return false;

        BSTNode<T> prev = search(key);

        if (prev == null){

            if(root.left == null && root.right == null){
                root = null;
                return true;
            }
            else if(root.left == null || root.right == null){
                BSTNode<T> onlyC;
                if(root.left != null){
                    onlyC = root.left;
                }
                else{
                    onlyC = root.right;
                }
                root = onlyC;
                return true;
            }
            else{
                BSTNode<T> ios = inorder(root.right);
                remove(ios.data);
                ios.setLeft(root.getLeft());
                ios.setRight(root.getRight());
                root = ios;

                return true;
            }
        }
        // we can assume that the prev has a node it is referencing

        // key is smaller than prev key
        if (key.compareTo(prev.data) < 0)
        {
            if (prev.left == null)  // node not in the tree
                return false;
            else                            // node can be deleted
            {
                // TODO
                BSTNode<T> del = prev.left;

                if(del.left == null && del.right == null){
                    prev.left = null;
                    return true;
                }

                else if(del.left == null || del.right == null){
                    BSTNode<T> onlyC;
                    if(del.left != null){
                        onlyC = del.left;
                    }
                    else{
                        onlyC = del.right;
                    }
                    prev.left = onlyC;
                    return true;
                }
                else{
                    BSTNode<T> ios = inorderR(del.left);
                    remove(ios.data);
                    prev.setLeft(ios);
                    ios.setLeft(del.getLeft());
                    ios.setRight(del.getRight());
                    return true;
                }
            }
        }

// key is > prev key
        else
        {
            if (prev.right == null)
                return false;
            else
            {
                BSTNode<T> del = prev.right;

                if(del.left == null && del.right == null){
                    prev.right = null;
                    return true;
                }

                else if(del.left == null || del.right == null){
                    BSTNode<T> onlyC;
                    if(del.left != null){
                        onlyC = del.left;
                    }
                    else{
                        onlyC = del.right;
                    }
                    prev.right = onlyC;
                    return true;
                }else{
                    BSTNode<T> ios = inorder(del.right);
                    remove(ios.data);
                    prev.setRight(ios);
                    ios.setLeft(del.getLeft());
                    ios.setRight(del.getRight());
                    return true;
                }
            }
        }
    }

    public BSTNode<T> inorder(BSTNode<T> ios){
        while(ios.left != null){
            ios = ios.left;
        }
        return ios;
    }

    public BSTNode<T> inorderR(BSTNode<T> ios){
        while(ios.right!=null){
            ios = ios.right;
        }
        return ios;
    }


    public void inorderByLevel(int depth)
    {
        System.out.print("[ ");
        bylevelrecur(depth, root, 0);
        System.out.println("]");
    }

    private void bylevelrecur(int depth, BSTNode<T> cur, int level)
    {
        if (cur != null)
        {
            if (depth == level){
                System.out.print(cur.data + " ");
            }
            bylevelrecur(depth, cur.left, level+1);

            bylevelrecur(depth, cur.right, level+1);
        }
    }

    public int leafCount(){
        return printLeaves(root, 0);
    }

    private int printLeaves(BSTNode root, int count){
        if (root == null){
            return 0;
        }

        if (isLeaf(root)){
            count++;
        }

        if (root.left != null){
            count = printLeaves(root.left, count);
        }

        if (root.right != null){
            count = printLeaves(root.right, count);
        }
        return count;
    }

    private boolean isLeaf(BSTNode node){
        return node.left == null && node.right == null;
    }

    public void insertBalanced(T[] arr){
        int hi = arr.length-1;
        int lo = 0;

        balanceRecur(arr, lo, hi);
    }

    private void balanceRecur(T[] arr, int lo, int hi){
        if(lo > hi){
            return;
        }
        int mid = (hi + lo)/2;

        insert(arr[mid]);

        balanceRecur(arr, lo, mid-1);
        balanceRecur(arr, mid+1, hi);
    }

    public int depthCount(int depth) // This is the helper method
    {
        return depthCount(depth, root, 0);
    }
    private int depthCount(int depth, BSTNode<T> cur, int level) // Real method
    {
        if (cur != null)
        {
            int count = depthCount(depth, cur.left, level+1);
            if (depth == level)
                return 1 + count + depthCount(depth, cur.right, level+1);
            else
                return count + depthCount(depth, cur.right, level+1);
        }
        else
            return 0;
    }

    public int height(){
        return heights(root);
    }

    private int heights(BSTNode<T> root){
        if(root == null){
            return 0;
        }
        int leftSide = heights(root.left);
        int rightSide = heights(root.right);

        if(leftSide>rightSide) return leftSide + 1;

        return rightSide + 1;
    }
}


