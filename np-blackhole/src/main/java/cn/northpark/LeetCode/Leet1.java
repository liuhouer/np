package cn.northpark.LeetCode;

import java.util.HashMap;

/**
 * @author zhangyang
 * @date 2020年11月02日 15:18:38
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class Leet1 {

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target- nums[i])){
                return  new int[]{map.get(target-nums[i]),i};
            }

            map.put(nums[i],i);//{2,0} {7,1} {11,2} {15,3}
        }


            return null;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] ints = new Leet1().twoSum(nums, target);
        for (int anInt : ints) {
            System.err.print(anInt +"->");
        }
    }

}
