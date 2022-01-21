package cn.northpark.LeetCode;


/**
 * @author zhangyang
 * @date 2021.01.27
 *283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class Leet283 {

    public void  moveZeroes(int[] nums) {

        int nonZIndex = 0;//非0索引，用于和0索引替换

        for (int i =0 ;i<nums.length;i++) {
            //1.不是零 ，（不和自己）替换位置，非零索引+1
            //2.是零，跳过，非零索引不变
            if(nums[i]!=0) {

                if (nonZIndex != i) {
                    int swap = nums[nonZIndex];
                    nums[nonZIndex] = nums[i];
                    nums[i] = swap;
                    nonZIndex++;
                }else{
                    nonZIndex++;
                }
            }

        }

    }

    public static void main(String[] args) {
        int a[] = new int[]  {1,0,2,0,3,4,5};
//        a = moveZeroes(a);
        for (int i = 0; i < a.length; i++) {
            System.err.print(a[i] +" " );
        }
        System.err.println();
    }


}
