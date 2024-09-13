import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class fact {

    public static void sieveOfEratosthenes(int n) {
        boolean[] arr = new boolean[n + 1];
        Arrays.fill(arr, true);

        arr[0] = arr[1] = false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (arr[i]) {
                for (int j = i + i; j <= n; j += i) {
                    arr[j] = false;
                }
            }
        }
        int c = 0;
        for (int i = 2; i <= n; i++) {
            if (arr[i])
                c++;
        }
        System.out.println(c);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // int n = sc.nextInt();
        sieveOfEratosthenes(100);
    }
}

// public static void simpleSieve(int n){
// boolean[] arr = new boolean[n+1];
// Arrays.fill(arr, true);
// arr[0] =arr[1] =false;

// for(int i=2; i <= Math.sqrt(n) ;i++){
// if(arr[i]){
// for(int j =i*i; j<= n; j+=i ){
// arr[j] = false;
// }
// }
// }
// for(int i=2;i<=n;i++){
// if(arr[i]){
// System.out.println(i);
// }
// }

// }