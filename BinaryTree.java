import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
    static int idx=-1;

    public static TreeNode buildTree(int[] nodes){
        idx++;
        if(nodes[idx]==-1) return null;
        TreeNode newNode = new TreeNode(nodes[idx]);
        newNode.left = buildTree(nodes);
        newNode.right = buildTree(nodes);
        return newNode;
    }
    public static void preorder(TreeNode root){
        if(root==null) {
            return;
        }

        System.out.print(root.val+" ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(TreeNode root){
        if(root==null) return;
        inorder(root.left);
        System.out.print(root.val+ " ");
        inorder(root.right);
    }
    public static void postorder(TreeNode root){
        List<Integer> lvl= new ArrayList<>();

        if(root==null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val+ " ");
    }
    public static void levelorder(TreeNode root){
        if(root==null) return;
        Queue<TreeNode> q = new LinkedList<>(); 
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            System.out.print(node.val + " ");
            if(node.left!=null) q.add(node.left);
            if(node.right!=null) q.add(node.right);
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        q.add(root);

        while(!q.isEmpty()){
            for(int i=0, len = q.size(); i < len;i++){
                TreeNode node = q.poll();   
                if(i == len -1){
                    res.add(node.val);
                }
                if(node.left!=null) q.add(node.left);
                if(node.right!=null) q.add(node.right);
            }
        }   
        return res;
    }
    public static int countNode(TreeNode root){
        if(root==null) return 0;

        return 1 + countNode(root.left)+ countNode(root.right);
    }
    public static int sumOfNodes(TreeNode root){
        if(root==null) return 0;

        return root.val + sumOfNodes(root.left)+ sumOfNodes(root.right);
    }
    public static int heightOfTree(TreeNode root){
        if(root==null) return 0;

        return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
    }

    public static int diameter(TreeNode root){
        int[] res = new int[1];
        dfs(root, res);
        return res[0];
    }
    public static int dfs(TreeNode root, int[] res){
        if(root==null) return 0;
        int left = dfs(root.left, res);  //keeping track of height of lst
        int right = dfs(root.right, res);  //keeping track of height of rst
        res[0] = Math.max(res[0], left+right); //keeping track of diameter

        return 1 + Math.max(left, right); 
    }
    
    public static boolean isIdentical(TreeNode root, TreeNode subRoot){
        if(root==null && subRoot==null) return true;

        if(root==null || subRoot==null) return false;

        if(root.val == subRoot.val){
            return isIdentical(root.left, subRoot.right) && isIdentical(root.right, subRoot.right);
        }
        return false;
    }
    public static boolean subTree(TreeNode root, TreeNode subRoot){
        if(subRoot==null) return true;
        if(root==null) return false;

        if(root.val == subRoot.val){
            if(isIdentical(root, subRoot)) return true;
        }

        return subTree(root.left, subRoot.left) || subTree(root.right, subRoot.right);
    }

    public boolean isBalanced(TreeNode root) { //O(n) solution //bottom-up solution
        return dfsBalance(root)[0]==1;
    }
    public int[] dfsBalance(TreeNode root){  //int[balanced, height]
        if(root==null) return new int[]{1,0};

        int[] left = dfsBalance(root.left);
        int[] right = dfsBalance(root.right);

        boolean balanced = left[0]==1 && right[0]==1 && Math.abs(left[1]-right[1])<=1;
        int height = 1 + Math.max(left[1], right[1]);

        return new int[]{balanced?1:0, height};
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p ==null && q==null) return true;
        if(p==null || q==null) return false;
        if(p.val!=q.val) return false;
         return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root ==null && subRoot == null) return true;
        if(root==null || subRoot==null) return false;
        if(root.val == subRoot.val) return isSameTree(root, subRoot);
        return isSubtree(root.left, subRoot.left) && isSubtree(root.right, subRoot.right);
    }

    

    public static void main(String[] args) {
        int[] nodes = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        TreeNode root= buildTree(nodes);
        int[] subNodes = {2,4,-1,-1,5,-1,-1};
        TreeNode subtree = buildTree(subNodes);
        // System.out.println(root.val);
        levelorder(root);
        System.out.println("\n"+sumOfNodes(root));
        System.out.println(diameter(root));
        System.out.println(subTree(root, subtree));
    }
}
