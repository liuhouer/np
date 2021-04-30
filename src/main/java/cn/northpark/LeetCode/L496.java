package cn.northpark.LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author liuhouer
 * @date 2021年04月29日
 *
 * 496. 下一个更大元素 I
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 *
 * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
 *
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 *     对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 *     对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 *     对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * 示例 2:
 *
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 *     对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 *
 *
 * 提示：
 *
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 *
 *
 * 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
 *
 * 解题思路
 * 下一个更大的元素这一类型题的思路：
 * 遍历数组元素，用一个栈集合去存放上一个元素，只要【栈上一个】<【最新的】，就从栈取出来，放到map中去。
 * 这样操作一遍下来，每个数组元素都能查到他的下一个更大的映射了。
 *
 * 作者：liuhouer
 * 链接：https://leetcode-cn.com/problems/next-greater-element-i/solution/xu-yao-qu-chu-shang-yi-ge-yao-bi-jiao-de-wk1l/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 */
public class L496 {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> mapMax = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            //2
            while (!stack.isEmpty()){
                Integer peek = stack.peek(); //1
                if(peek < nums2[i]){
                    //拿出来存到map
                    mapMax.put(stack.pop(),nums2[i]);
                }else{
                    break;
                }
            }

            //存入stack --1
            stack.add(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = mapMax.getOrDefault(nums1[i],-1);
        }

        return  nums1;
    }

    public static void main(String[] args) {

    }
}
