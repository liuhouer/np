package cn.northpark.LeetCode;

import java.util.*;

/**
 * @author liuhouer
 * @date 2021年04月29日
 *
 * 692. 前K个高频单词
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 *
 *
 *
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 *
 *
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 *
 * 最大堆+hashmap计数，比前K个数组元素要高效，因为遍历的K个元素都需要
 *
 *
 */
public class L692 {

    public List<String> topKFrequent(String[] words, int k) {

        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for (int i = 0; i < words.length; i++) {
            if(map.containsKey(words[i])){
                Integer value = map.get(words[i]);
                map.put(words[i], value+1);
            }else {
                map.put(words[i], 1);
            }
        }


        //最大堆
        PriorityQueue<String> maxHeap = new PriorityQueue<String>(new Comparator<String>() {
            @Override
            public int compare( String  o1,  String  o2) {
                Integer value2 = map.get(o2);
                Integer value1 = map.get(o1);
                //数大优先
                if(value2 > value1 || value2 < value1){
                    return value2 - value1;
                }else {//==  字母小优先
                    return o1.compareTo(o2);
                }
            }
        });

        //put
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String mapKey = entry.getKey();
            maxHeap.add(mapKey);
        }

        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            list.add(maxHeap.poll());
        }

        return list;
    }



    public static void main(String[] args) {
        System.err.println(
            new L692().topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"},2)
        );
    }
}
