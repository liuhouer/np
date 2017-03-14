package cn.northpark.test;

import java.util.Scanner;

public class AAA {
	public static  int NthPrime(int n){
        int i = 2, j = 1;
        while (true) {
            j = j + 1;
            if (j > i / j) {
                n--;
                if (n == 0)
                    break;
                j = 1;
            }
            if (i % j == 0) {
                i++;
                j = 1;
            }
        }
        return i;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
         System.out.print("请输入N的值:");
         Scanner sc = new Scanner(System.in);
         int n = sc.nextInt();
         int result = NthPrime(n);
         sc.close();
         System.out.println("第N个素数的值是:"+result);
    }

}
