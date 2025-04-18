package main.java;

public class BinarySearchTree {

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
        else if (newNode = root)

            root.left = newNode;
        else
            root.right = newNode;
        return root;
    }
    private TreeNode findNode(TreeNode root, Identity id){
        if(root==null)
            return null;
        else
            if (root.data=id)
                return root.data;
            else
                if ()
                    return root.left;
                else
                    return root.right;
    }
}
