package cn.northpark.LeetCode;

/**
 * @author zhangyang
 * @date 2020年11月02日 10:56:51
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 * <p>
 * 从前往后跳，每个记录最大值，如果最后最大值>=最后的index 就满足条件
 * <p>
 * 也可以用并查集 连接开始和最后节点 来测试连通性
 */
public class Leet55 {

    public boolean canJump(int[] nums) {
        int maxIndex = 0;
        int length = nums.length;

        for (int i = 0; i < length - 1; i++) {
            int num = nums[i];

            //保证 当前序号不能大于最大的序号下标， 如果出现这种场景 ， 肯定走不到最后
            if (maxIndex >= i) {

                //获取num可以跳到的所有位置 【i+nums[i]】为i往右跳的最大坐标

                maxIndex = Math.max(maxIndex, i + nums[i]);
            }
        }

        if (maxIndex >= length - 1) {
            return true;
        }


        return false;


    }
}
