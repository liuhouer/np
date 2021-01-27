package cn.northpark.LeetCode;


import java.util.*;

/**
 * @author zhangyang
 * @date 2020年09月28日 14:53:18
 * 350. 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 *
 * 说明：
 *
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class Leet350 {

    public int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer,Integer> map = new TreeMap();
        TreeMap<Integer,Integer> map2 = new TreeMap();
        for (int i : nums1) {
            if(map.containsKey(i)){

                map.put(i,map.get(i)+1);
            }else{

                map.put(i,1);
            }
        }

        for (int i : nums2) {
            //交集数值筛选
            if(map.containsKey(i)){
                if(map2.containsKey(i)){

                    map2.put(i,map2.get(i)+1);
                }else{

                    map2.put(i,1);
                }
            }
        }

        List<Integer> rs = new ArrayList<>();

        for(Map.Entry<Integer, Integer> m: map2.entrySet()) {
            Integer key = m.getKey();
            Integer minSize =0;;
            if( map.get(key) <= m.getValue()){
                minSize = map.get(key);
            }else {
                minSize = m.getValue();
            }
            if(minSize>0){
                for (Integer i = 0; i < minSize; i++) {

                    rs.add(key);
                }
            }

        }
        int[] rsi = new int [rs.size()];
        for (int i = 0; i < rs.size(); i++) {
            rsi[i] = rs.get(i);
        }



        return rsi;
    }

    public static void main(String[] args) {
        (new Leet350()).intersect(new int[]{1,2,2,1},new int[]{2,2});
    }



}
