package cn.northpark.LeetCode.回溯法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuhouer
 * @date 2020年10月27日 19:33:54
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2：
 * <p>
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class Leet39 {

    //解法：回溯法，就和树的深度遍历类似,不过先从小到大排序，可以直接省略很多遍历节点。添加了break退出循环的剪枝。
    //                7
    //           5  /  | \ \
    //             2   3  6  7
    //         3 / |\ \
    //    (x)   2  3 6 7
    //    (x)1 /|\\
    //        2 3 6 7 
    //
    List<List<Integer>> rs = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        helper(candidates, 0, target);
        return rs;
    }

    private void helper(int[] candidates, int start, int target) {

        if (target == 0) {
            rs.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break;
            list.add(candidates[i]);
            helper(candidates, i, target - candidates[i]);
            list.remove(list.size()-1);
        }
    }
    
   public static void main(String[] args) {
        Leet39 leet39 = new Leet39();
        int[] candidates = {3,5,8,11};
        int target = 11;
        List<List<Integer>> rs = leet39.combinationSum(candidates, target);
        rs.stream().forEach(i -> {
            System.err.println(i.toString());
        });
    }
    
}
