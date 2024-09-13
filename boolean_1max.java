import java.util.*;

public class boolean_1max {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            int n= sc.nextInt();
            binaryPalindrome(n);
            String nb = Integer.toBinaryString(n);

            int count=0,maxCount =0;
            for(int i=0;i<nb.length();i++){
                char c = nb.charAt(i);
                if(c=='1'){
                    count++;
                }else{
                    count=0;
                }
                maxCount = Math.max(maxCount, count);
            }
            System.out.println(nb +": "+ maxCount);
        }
    }
    public static void toggleCase(String s){
        String res="";
        for(char c: s.toCharArray()){
            if(Character.isLowerCase(c)){
                res+=Character.toUpperCase(c);
            }else{
                res+=Character.toLowerCase(c);
            }
        }
        System.out.println(res);
    }
    public static void binaryPalindrome(int n){
        String nb = Integer.toString(n,2);
        int l=0,r=nb.length()-1;
        boolean flag = true;
        while(l<=r){
            if(nb.charAt(l)!=nb.charAt(r)){
                flag=false;
                break;
            }
            l++;
            r--;
        }
        System.out.println(nb+ ": "+ (flag? "Binary Palindrome" : "Not BP")+"\n");
    }
    public static String binary(int n){
        String binaryString="";
        while(n>0){
            int remainder = n%2;
            binaryString = remainder + binaryString;
            n/=2;
        }
        return binaryString;
    }
}
