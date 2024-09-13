import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Backtracking {
    public static void main(String[] args) {
        String str = "ABC";
        int[] nums = new int[] {1,2,3};
        // printPermutation(str, "");
        List<List<Integer>> res= listPermute(nums);
        for(List<Integer> x : res){
            System.out.println(x);
        }
        // List<String> res= new ArrayList<>();
        // List<Boolean> used = new ArrayList<>();
        // Permutation(res, str, "", used);
        // for(String c : res){
        //     System.out.println(c);
        // }
    }
    
    public static void printPermutation(String str, String perm){
        if(str.length()==0){
            System.out.println(perm);
            return;
        }
        for(int i=0;i<str.length();i++){
            char currChar = str.charAt(i);
            String newStr = str.substring(0,i) + str.substring(i+1);
            System.out.println(i+ " substring : "+newStr );
            printPermutation(newStr, perm+currChar);
        }
    }   
    public static void Permutation(List<String> res, String nums, String perm, List<Boolean> used){
        if(perm.length() == nums.length()){
            res.add(perm);
            return;
        }
        for(int i=0;i < nums.length();i++){
            if(!used.get(i)){
                used.set(i, true);
                perm+=nums.charAt(i);
                Permutation(res, nums, perm, used);
                used.set(i, false);
                perm.substring(0, i-1);
            }
        }

    }
    public static List<List<Integer>> listPermute(int[] nums){
        if(nums.length ==0 ){
            return List.of(List.of());
        }
        List<List<Integer>> res= new ArrayList<>();
        List<List<Integer>> permute = listPermute(Arrays.copyOfRange(nums, 1, nums.length)); // nums[1:]
        for(List<Integer> p : permute){
            for(int i=0; i<= p.size();i++){
                List<Integer> pCopy = new ArrayList<>(p);
                pCopy.add(i, nums[0]);
                res.add(pCopy);
            }
        }
        return res;
    }

}
