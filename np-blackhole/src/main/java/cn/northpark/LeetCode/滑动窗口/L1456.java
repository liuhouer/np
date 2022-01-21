package cn.northpark.LeetCode.滑动窗口;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author liuhouer
 * @date 2021年05月07日 14:40:16
 *
 * 1456. 定长子串中元音的最大数目
 * 给你字符串 s 和整数 k 。
 *
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 *
 * 英文中的 元音字母 为（a, e, i, o, u）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abciiidef", k = 3
 * 输出：3
 * 解释：子字符串 "iii" 包含 3 个元音字母。
 * 示例 2：
 *
 * 输入：s = "aeiou", k = 2
 * 输出：2
 * 解释：任意长度为 2 的子字符串都包含 2 个元音字母。
 * 示例 3：
 *
 * 输入：s = "leetcode", k = 3
 * 输出：2
 * 解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
 * 示例 4：
 *
 * 输入：s = "rhythms", k = 4
 * 输出：0
 * 解释：字符串 s 中不含任何元音字母。
 * 示例 5：
 *
 * 输入：s = "tryhard", k = 4
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 * 1 <= k <= s.length
 *
 */
public class L1456 {

    int i = 0; //左边窗口的边界
    int size = 0;//元音的个数
    final List YUAN_YIN =   Collections.unmodifiableList(Arrays.asList("a","e","i","o","u"));


    /**
     * 题目解出来了，不过2层for会超时
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {

        for (int j = 0; j < s.length(); j++) {
            int width  = j - i + 1;

            //长度为k
            while(width==k && i<=j ){

                //判断窗口长度内的元音个数
                int len = 0;
                for (int z =i;z<=j;z++){
                    //
                    if(YUAN_YIN.contains(String.valueOf(s.charAt(z)))){
                        len++;
                    }
                }
                if(size==0){
                    size = len;
                }else {
                    size = Math.max(size,len);
                }
                //计算完删除最左边的元素
                i++;
                width--;
            }

        }

        return size;

    }


    public static void main(String[] args) {
//        System.err.println(new L1456().maxVowels("abciiidef",3));
//        System.err.println(new L1456().maxVowels("aeiou",2));
//        System.err.println(new L1456().maxVowels("leetcode",3));
//        System.err.println(new L1456().maxVowels("rhythms",4));
//        System.err.println(new L1456().maxVowels("tryhard",4));
        System.err.println(new L1456().maxVowels("novowels",1));

//        String str = "leetcode";
//        char[] chars = str.toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            System.err.println(new L1456().YUAN_YIN.contains(String.valueOf(chars[i])));
//        }

    }
}
