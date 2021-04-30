package cn.northpark.LeetCode;

import java.util.*;

/**
 * @author liuhouer
 * @date 2021年04月29日
 *
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。符串是否有效。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 *
 * 输出: 5
 * 示例 2:
 *
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 *
 * 解题思路
 * 最大堆比较好理解，效率肯定不是最好的，易于理解，全部塞进去，然后依次取出堆顶元素就是结果了
 *
 */
public class L215 {

    public int findKthLargest(int[] nums, int k) {

        //获取一个最大堆
        PriorityQueue<Integer> queue = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.valueOf(String.valueOf(o2)) - Integer.valueOf(String.valueOf(o1));
            }
        });
        for (int num : nums) {
            queue.add(num);
        }

        int res = -1;
        for (int i = 0; i < k; i++) {
            res = queue.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        System.err.println(
            new L215().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6},4)
        );
    }
}
