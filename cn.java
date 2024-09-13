import java.util.ArrayList;
import java.util.List;


public class cn{
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> subset = new ArrayList<>();
        dfs(0, s, subset, res);
        return res;
    }

    public static void dfs(int start, String s, List<String> subset,
            List<List<String>> res) {
        if (start == s.length()) {
            for(String str : subset){
                if(!isPalindrome(str)){
                    return;
                }
            }
            res.add(new ArrayList<>(subset));
            return;
        }

        for(int i= start;i < s.length();i++){
            String substr = s.substring(start, i+1);{
                if(!isPalindrome(substr)) continue;
                subset.add(substr);
            }
            dfs(i + 1, s, subset, res);
            subset.remove(subset.size() - 1);
        }

    }

    public static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l <= r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        List<List<String>> res = partition("aab");
        System.out.println(res);
    }
}