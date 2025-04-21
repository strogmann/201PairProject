package main.java;

public class BinarySearchTree {

    public interface Identity {
        public boolean match(Identity other);
        public boolean isLessThan( Identity other);

    }
    public interface IdentifiedObject {
        public Identity getIdentity();
    }

    private class TreeNode{
        IdentifiedObject data;
        TreeNode left, right;

        TreeNode(IdentifiedObject data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void add(IdentifiedObject obj){
        root = addNode(root, new TreeNode(obj));
    }

    public IdentifiedObject find(Identity id){
        TreeNode node = findNode(root, id);
        if(node!=null)
            return node.data;
        else
            return null;
    }

    private TreeNode addNode(TreeNode currentRoot, TreeNode newNode) {
        if (currentRoot == null)
            return newNode;
        else
            if (newNode.data.getIdentity().isLessThan(currentRoot.data.getIdentity()))
                currentRoot.left = addNode(currentRoot.right, newNode);
            else
                currentRoot.right = addNode(currentRoot.right, newNode);
            return currentRoot;
    }
    private TreeNode findNode(TreeNode currentRoot, Identity id){
        if(currentRoot==null)
            return null;
        else
            if (id.match(currentRoot.data.getIdentity()))
                return currentRoot;
            else
                if (id.isLessThan(currentRoot.data.getIdentity()))
                    return findNode(currentRoot.left, id);
                else
                    return findNode(currentRoot.right, id);
    }
}
