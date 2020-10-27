package cn.northpark.LeetCode;

import java.util.ArrayList;
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

    public static void main(String[] args) {
        Leet39 leet39 = new Leet39();
        int[] candidates = {3,5,8,11};
        int target = 11;
        List<List<Integer>> rs = leet39.combinationSum(candidates, target);
        rs.stream().forEach(i -> {
            System.err.println(i.toString());
        });
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> rs = new ArrayList<>();
        //每次处理一个元素位置
        int startIndex = 0;
        get_rs_of_index(startIndex, rs, candidates, target, 0);
        return rs;
    }


    /**
     * @param startIndex
     * @param rs
     * @param candidates
     * @param target
     * @param lastAddRS  -上次累加结果
     */
    //每次处理一个元素位置
    private void get_rs_of_index(int startIndex, List<List<Integer>> rs, int[] candidates, int target, int lastAddRS) {

        //终止条件
        if (startIndex == candidates.length) {
            return;
        }

        int candidate = candidates[startIndex];
        if (candidate == target) {
            List<Integer> itemIndex = new ArrayList<>();
            itemIndex.add(candidate);
            rs.add(itemIndex);
        } else if (candidate < target) {
            //1> lastAddRS==0
            if(lastAddRS==0){
                //1_1. 处理整除
                //lastAddRS!=0整除 ，单个数整除
                if ( (target) % candidate == 0 && (target) / candidate >= 1 && (target) / candidate <= 30) {
                    List<Integer> itemIndex = new ArrayList<>();
                    for (int i = 0; i < target / candidate; i++) {
                        itemIndex.add(candidate);
                    }
                    rs.add(itemIndex);

                }
            }else{
            //2> lastAddRS!=0
                //2_1>. 处理整除
                //lastAddRS!=0整除 ，单个数整除
                if ( (target) % candidate == 0 && (target) / candidate >= 1 && (target) / candidate <= 30) {
                    List<Integer> itemIndex = new ArrayList<>();
                    for (int i = 0; i < target / candidate; i++) {
                        itemIndex.add(candidate);
                    }
                    rs.add(itemIndex);

                }
                //  lastAddRS!=0 差值整除  candidate
                if ((target - lastAddRS) % candidate == 0 && (target - lastAddRS) / candidate >= 1 && (target - lastAddRS) / candidate <= 30) {
                    List<Integer> itemIndex = new ArrayList<>();
                    if (lastAddRS > 0) {
                        itemIndex.add(lastAddRS);
                    }
                    for (int i = 0; i < target / candidate; i++) {
                        itemIndex.add(candidate);
                    }
                    rs.add(itemIndex);
                    //    lastAddRS!=0 差值整除  lastAddRS
                }else if ((target - candidate) % lastAddRS == 0 && (target - candidate) / lastAddRS >= 1 && (target - candidate) / lastAddRS <= 30) {
                    List<Integer> itemIndex = new ArrayList<>();
                    for (int i = 0; i < target / candidate; i++) {
                        itemIndex.add(lastAddRS);
                    }
                    if (candidate > 0) {
                        itemIndex.add(candidate);
                    }
                    rs.add(itemIndex);
                }
                //2_2> .非整除
                if ((target - lastAddRS) % candidate > 0) {
                    //candidates = [2,3,5], target = 8,
                    // * 所求解集为：
                    // * [
                    // *   [2,2,2,2],
                    // *   [2,3,3],
                    //     [3,5]

                    //2_3 处理遍历相邻元素到end 判断相加
                    for (int t=startIndex;t<candidates.length;t++){
                        if (lastAddRS + candidates[t] == target) {
                            List<Integer> itemIndex = new ArrayList<>();
                            if (lastAddRS > 0)
                                itemIndex.add(lastAddRS);
                            itemIndex.add(candidates[t]);
                            rs.add(itemIndex);
                        }
                    }

                }
            }



            startIndex++;
            get_rs_of_index(startIndex, rs, candidates, target, candidate);
        }


    }
}
