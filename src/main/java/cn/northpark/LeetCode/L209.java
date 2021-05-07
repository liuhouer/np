package cn.northpark.LeetCode.滑动窗口;

/**
 * @author liuhouer
 * @date 2021年05月07日 14:40:16
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * <p>
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class L209 {

    int i = 0;
    int sum = 0;
    int len = 0;

    public int minSubArrayLen(int target, int[] nums) {

        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            //只要>target就删减
            while (sum >= target) {

                //计算当前满足条件的长度
                if (len == 0) {
                    len = j - i + 1;
                } else {
                    len = Math.min(j - i + 1, len);
                }

                //从左边删减一个继续尝试
                sum = sum - nums[i++];

            }
        }
        return len;
    }


    public static void main(String[] args) {
        System.err.println(new L209().minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }
}
