import java.util.LinkedList;
import java.util.Queue;

public class btree_even {

    static int idx = -1;
    public static TreeNode buildTree(int[] nodes){
        idx++;
        if(nodes[idx]==-1) return null;
        TreeNode newNode = new TreeNode(nodes[idx]);
        newNode.left = buildTree(nodes);
        newNode.right = buildTree(nodes);

        return newNode;
    }

    public static int checkMaxEvenLevel(int[] arr){
        TreeNode root= buildTree(arr);
        Queue<TreeNode> q = new LinkedList<>();
        
        q.add(root);
        while(!q.isEmpty()){
            TreeNode curr = q.poll();
            System.out.print(curr.val+" ");

            if(curr.left != null) q.add(curr.left);
            if(curr.right != null) q.add(curr.right);
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr ={1,2,3,4,5,6,7,8};
        System.out.println(checkMaxEvenLevel(arr));
    }
}