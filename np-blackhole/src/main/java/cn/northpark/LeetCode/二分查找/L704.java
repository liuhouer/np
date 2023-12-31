package cn.northpark.LeetCode.二分查找;

/**
 * @author zhangyang
 * @date 2021年05月06日 15:35:39
 * 704. 二分查找
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * 示例 1:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *  
 *
 * 提示：
 *
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 *
 * 123456
 *
 * 5
 *
 * 思路；定义左右边界，不断缩小左右边界，中间边界就会一直变
 *
 * 二分查找的while边界判断，
 *
 * 如果是仅仅查找下标，直接left < right,最后按照left下表取值判断。
 * 如果是判断取值相等 ，可以添加上left <=right
 *
 */
public class L704 {

    /**
     * 按照下标找 不写等号
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        //起始点
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid  ;
            }
        }


        return nums[left]==target?left:-1;
    }

    /**
     * 直接查找所有值情况 写=号
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        //起始点
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid -1 ;
            }
        }


        return -1;
    }

    public static void main(String[] args) {
        System.err.println(new L704().search(new int[]{2,5},5));;
    }
}
