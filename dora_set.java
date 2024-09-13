import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class dora_set {
    public static void main(String[] args) {
        int[] nums = new int[] {2,3,6,7};
        List<List<Integer>> res = combinationSum(nums, 7);
        for(List<Integer> r : res){
            System.out.println(r);
        }
    }
    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res= new ArrayList<>();
        List<Integer> sum = new ArrayList<>();
        // dfs(sum, 0, res, 0, target, nums);
        dfs2(0, sum, res, 0, target, nums);
        return res;
    }

    public static void dfs(List<Integer> combination ,int start ,List<List<Integer>> res, int total,int target, int[] nums){
        if(total == target){
            res.add(new ArrayList<>(combination));
            return;
        }
        if(total > target){
            return;
        }
        
        for(int i =start; i < nums.length;i++){
            combination.add(nums[i]);
            dfs(combination, i, res, total + nums[i], target, nums);
            combination.remove(combination.size()-1);
        }
    }
    public static void dfs2(int i, List<Integer> cur, List<List<Integer>> res, int total, int target, int[] nums){
        if(total == target){
            res.add(new ArrayList<>(cur));
            return;
        }
        if(i >= nums.length || total > target){
            return;
        }

        cur.add(nums[i]);
        dfs2(i, cur, res, total + nums[i], target, nums);

        cur.remove(cur.size()-1);
        dfs2(i+1, cur, res, total, target, nums);

    }
}
