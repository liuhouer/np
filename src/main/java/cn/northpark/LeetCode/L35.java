package cn.northpark.LeetCode.二分查找;

/**
 * @author liuhouer
 * @date 2021年05月06日 15:57:13
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 *
 */
public class L35 {

    public int searchInsert(int[] nums, int target) {



        int left = 0;
        int right = nums.length -1;
        //特殊情况处理
        if(target > nums[right]){
            return right + 1;
        }
        //特殊情况处理
        if(target < nums[left]){
            return 0;
        }

        while (left <= right){
            int mid = left +(right-left)/2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid +1;
            }else if(nums[mid] > target){
                right = mid - 1;
            }


        }

        return left;
    }

    public static void main(String[] args) {
        System.err.println(new L35().searchInsert(new int[]{1,3} ,2));
    }

}
