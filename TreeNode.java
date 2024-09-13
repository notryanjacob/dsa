public class TreeNode {
    int val;
    TreeNode right;
    TreeNode left;
    
    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode() {
    }

    public TreeNode(int val, TreeNode right, TreeNode left) {
        this.val = val;
        this.right = right;
        this.left = left;
    }
}
