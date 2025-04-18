package main.java;

public class BinarySearchTree {

    interface Identity {
        public boolean match(Identity );
        public boolean isLessThan( Identity );
    }
    interface IdentifiedObject {
        public Identity getIdentity();
    }

    public void add (IdentifiedObject obj){
        root = addNode(root, new TreeNode(pat));
    }

    public IdentifiedObject find(Identity id){
        TreeNode node = findNode(root, id);
        if(node!=null)
            return node.data;
        else
            return null;
    }

    private class TreeNode{
        IdentifiedObject data;
        TreeNode left, right;
    }

    private TreeNode addNode(TreeNode root, TreeNode newNode) {
        if (root == null)
            return newNode;
        else
            if ()
                root.left = newNode;
            else
                root.right = newNode;
            return root;
    }
    private TreeNode findNode(TreeNode root, Identity id){
        if(root==null)
            return null;
        else
            if (id.match(root))
                return root;
            else
                if (id.isLessThan(root))
                    return root.left;
                else
                    return root.right;
    }
}
