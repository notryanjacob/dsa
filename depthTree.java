import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class depthTree {
    //recursive dfs
    // public int maxDepth(TreeNode root){
    //     if(root==null) return 0;
    //     return 1+ Math.max( 
    //         maxDepth(root.left),
    //         maxDepth(root.right)
    //     );
    // }

    //iterative bfs
    // public int maxDepth(TreeNode root){
    //     if(root==null) return 0;
        
    //     int level =0;
    //     Queue<TreeNode> q = new LinkedList<>();
    //     q.add(root);

    //     while(!q.isEmpty()){
    //         for (int i = 0; i < q.size(); i++) {
    //             TreeNode node = q.poll();
    //             if(node.left!=null) q.add(node.left);
    //             if(node.right != null) q.add(node.right);
    //         }
    //         level++;
    //     }
    //     return level;
    // }

    //iterative dfs
    public int maxDepth(TreeNode root){
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 0));
        int res =0;
        while(!stack.isEmpty()){
            Pair<TreeNode, Integer> current = stack.pop();
            TreeNode node  = current.getKey();
            int depth = current.getValue();

            if(node!=null){
                res = Math.max(res, depth);
            }
            if(node.left!=null) stack.push(new Pair<TreeNode,Integer>(node.left, depth+1));
            if(node.right!=null) stack.push(new Pair<>(node.right, depth +1));
            
        }

        return res;
    }
}
