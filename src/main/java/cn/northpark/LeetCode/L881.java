package cn.northpark.LeetCode;

import org.apache.curator.framework.api.BackgroundEnsembleable;

import java.util.Arrays;

/**
 * @author liuhouer
 * @date 2021年05月06日 14:57:01
 * <p>
 * 881. 救生艇
 * 第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
 * <p>
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 * <p>
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：people = [1,2], limit = 3
 * 输出：1
 * 解释：1 艘船载 (1, 2)
 * 示例 2：
 * <p>
 * 输入：people = [3,2,2,1], limit = 3
 * 输出：3
 * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
 * 示例 3：
 * <p>
 * 输入：people = [3,5,3,4], limit = 5
 * 输出：4
 * 解释：4 艘船分别载 (3), (3), (4), (5)
 * 提示：
 * <p>
 * 1 <= people.length <= 50000
 * 1 <= people[i] <= limit <= 30000
 * <p>
 * 1 2 3 3 4 5  limit = 5
 *
 * 思路：对数组进行排序，然后 瘦子和胖子体重进行判断，双指针移动，【左 = 右 跳出循环】
 */
public class L881 {
    public int numRescueBoats(int[] people, int limit) {

        int result = 0;

        int start = 0, end = people.length - 1;

        if (people.length == 0) {
            return 0;
        }
        //排序
        Arrays.sort(people);

        while (start <= end) {
            if (start < end) {
                if (people[start] + people[end] <= limit) {
                    //小+大的走一个
                    start++;
                    end--;
                    result++;
                } else {
                    //大的自己走
                    end--;
                    result++;
                }
            } else if (start == end) {
                result++;//自己走
                break;
            }

        }


        return result;
    }

    public static void main(String[] args) {

        new L881().numRescueBoats(new int[]{3,2,2,1} ,3);
    }
}
