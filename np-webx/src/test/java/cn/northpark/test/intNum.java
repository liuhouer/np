package cn.northpark.test;

/**
 * @author zhangyang
 * @date 2021年04月21日 18:59:46
 * n是18的整数
 * （1）5n/36是整数
 * （2）2n/9 是整数
 */
public class intNum {


    public static void main(String[] args) {

//        for (int n = 0; n <1000 ; n++) {
//            int A = 5*n;
//            int B = 2*n;
//
//            if(A % 36==0 && B % 9 ==0){
//
//                System.out.println(n%18==0?n+"可以整除18":n+"不能整除18");
//            }
//        }

        for (int n = 0; n <1000 ; n++) {
            int A = 5*n;

            if(A % 36==0 ){

                System.out.println(n%18==0?n+"可以整除18":n+"不能整除18");
            }
        }

//        for (int n = 0; n <1000 ; n++) {
//            int B = 2*n;
//
//            if( B % 9 ==0 ){
//
//                System.out.println(n%18==0?n+"可以整除18":n+"不能整除18");
//            }
//        }
    }
}
