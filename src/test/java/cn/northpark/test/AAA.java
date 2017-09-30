package cn.northpark.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	 * @param str
	 * @param x
	 * @return
	 * 定义一个char［］，无限大，但是遇到0，则停止，写一个程序，
	 * 使用递归的方式，计算某个字符在这个数组中出现的次数
	 */
	int n=0;
	int i=0;
	public int count(String str[],String x){
		do{
			if(str[i].equals(x)){
				n++;
				String[] temp=Arrays.copyOfRange(str, i+1, str.length);
				count(temp, x);
			}
			i++;
		}while(str[i]=="0");
		
		return n;
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
         sc.getClass().getName();
         System.out.println(sc.getClass());
         System.out.println(sc.getClass().getName());
         
         ArrayList<String> a = new ArrayList<String>();
         List<String> b = (List<String> )a ;
    }

}
