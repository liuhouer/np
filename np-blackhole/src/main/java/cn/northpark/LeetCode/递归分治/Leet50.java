package cn.northpark.LeetCode.递归分治;


/**
 * @author zhangyang
 * @date 2021年07月07日
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 *
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 *
 * 提示：
 *
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 */
public class Leet50 {

    /**
     * n:奇数 x ^ n/2 * x ^ n/2  *x
     * n:偶数 x ^ n/2 * x ^ n/2
     * n:负数 求导数
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {

        if(n==0){
            return 1;
        }

        if(n>0 && n%2==0){
            double rs = myPow(x, n / 2);
            return  rs * rs;
        }else if(n>0){
            double rs = myPow(x, n / 2);
            return  rs * rs * x;
        }else if(n<0){
            return 1 / myPow( x,  -n);
        }

        return -1;
    }
}
