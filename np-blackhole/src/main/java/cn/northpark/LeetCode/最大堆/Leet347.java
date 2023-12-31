package cn.northpark.LeetCode.最大堆;

import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author zhangyang
 * @date 2020年9月29日
 *
 * 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 *
 * 提示：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 */
public class Leet347 {

    private class Freq implements Comparable<Freq>{

        int e,freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) { //优先队列 >从大到小的堆二叉树 peek是最大的值，因此我们需要把比较符越小的值设置为优先级最高，先弹出
            if(this.freq < another.freq){
                 return -1;
            }else if(this.freq > another.freq){
                return 1;
            }
            return 0;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {

        TreeMap<Integer,Integer> map = new TreeMap<>();

        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if(map.containsKey(key)){

                map.put(key,map.get(key)+1);
            }else {

                map.put(key,1);
            }
        }

        PriorityQueue<Freq> queue = new PriorityQueue(); //优先队列 >从大到小的堆二叉树 peek是最大的值，因此我们需要把比较符越小的值设置为优先级最高，先弹出
        for (int key: map.keySet()) {
            if(queue.size()<k){
                queue.add(new Freq(key,map.get(key)));
            }else if(map.get(key) > queue.peek().freq){
                queue.poll();
                queue.add(new Freq(key,map.get(key)));
            }
        }

        int[] rs = new int[queue.size()];

        for (int i = 0; i < rs.length; i++) {

            if (!queue.isEmpty()){
                rs[i] = queue.poll().e;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        int[] ints = (new Leet347()).topKFrequent(new int[]{4,1,-1,2,-1,2,3}, 2);
        for (int anInt : ints) {
            System.err.println(anInt);
        }

    }



}
