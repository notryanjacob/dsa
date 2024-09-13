import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;


class sts_cat1{
    public static void reverse(double n){
        //321 => 321%10 = 1; 1<5 => 32 +0 = 320; 328=> 8>=5 -> 320+10 = 330
        DecimalFormat df = new DecimalFormat("#.###");
        System.out.println(df.format(n));
        //321 => math.round(321/10)*10
        System.out.println(Math.round(n/10)*10);
    }
    public static void sieveEratosthenes(int n){
        boolean[] arr = new boolean[n+1];
        Arrays.fill(arr, true);
        arr[0] = arr[1] = false;

        for(int i=2;i <= Math.sqrt(n);i++){
            if(arr[i]){
                for(int j=i*i; j<=n;j+=i){
                    arr[j] = false; 
                }
            }
        }
        int c=0;
        for(int i=2;i<=n;i++){
            if(arr[i]) c++;
        }
        System.out.println("Number of primes : "+c);
    }

    public static int fact(int n){
        if(n==0 || n==1) return 1;
        return n* fact(n-1);
    }
    public static int gcd(int a, int b){ //euclidiean algo for gcd

        while(b != 0){
            int temp = b;
            b = a % b; //b get modded value
            a = temp;  //a gets b's prev value
        }
        // System.out.println(a);
        return a;
    }
    public static int gcdRecursive(int a, int b){
        if(b==0) return a;
        return gcd(b, a%b);
    }
    public static void relativelyPrime(int x){ // Euler's Phi -> number of pos int <= n that are relatively prime
        int count=0;
        for(int i=1;i<=x;i++){
            if(gcd(i,x)==1){
                count++;
            }
        }
        System.out.println(count);
        // n: prime; phi(n) = n-1
        // n: prime * comp; phi(n) = p^k - p^(k-1)
        //n: prime * prime; phi(n) = phi(p1) * phi(p2)
        //n: general : phi(n) = n * ∏(1- 1/p)
    }

    public static boolean strobogrammatic(int n){
        int len = String.valueOf(n).length();
        String num = String.valueOf(n);
        HashMap<Character, Character> set= new HashMap<>();
        set.put('0', '0');
        set.put('1','1');
        set.put('6','9');
        set.put('9','6');
        set.put('8','8');

        if(len ==1) return (n == 1 || n== 0 || n==8);
        int l = 0, r = len-1;
        while(l <= r){
            if(set.get(num.charAt(l)).equals(num.charAt(r))){
                l++; r--;
            }else{
                return false;
            }
        }
        return true;
    }
    //REMAINDER THEOREM: FOR P(X) / (X-C) -> F(C) = REM; HORNERS METHOD=> rem= rem* c+ poly[i]; fori
    public static void main(String[] args) {
        // reverse(325.5236);
        // System.out.println(fact(5));
        System.out.println(gcdRecursive(12,48));
        // relativelyPrime(7); //5*2
        // relativelyPrime(5);
        // relativelyPrime(35);
        relativelyPrime(18);
        System.out.println(strobogrammatic(962)? "yes" : "no");
        sieveEratosthenes(100);
    }
}