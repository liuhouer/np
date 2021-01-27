package cn.northpark.LeetCode;


import com.google.common.base.Supplier;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangyang
 * @date 2020年09月28日 14:53:18
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *
 *
 * 说明：
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class Leet349 {

    public int[] intersection(int[] nums1, int[] nums2) {
        return Arrays.stream(nums2).filter(t ->   Arrays.stream(nums1).boxed().collect(Collectors.toSet()).contains(t)).distinct().toArray();
    }


}
