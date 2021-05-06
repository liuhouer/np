package cn.northpark.LeetCode;

import java.util.HashSet;

/**
 * @author liuhouer
 * @date 2021年04月29日
 * <p>
 * 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
 * <p>
 * 我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * <p>
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目范围是 [0, 104]
 * -105 <= Node.val <= 105
 * pos 为 -1 或者链表中的一个 有效索引 。
 *
 * 思路：利用set去重判断循环链表。双指针的写法有可能永远不会重合【死循环超时】
 */
public class L141 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();

        while (head!=null && head.next != null) {

            if(set.add(head)){
                head = head.next;
            }else{
                return true;
            }

        }

        return false;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);

        node1.next = node2;
        node2.next = node1;

        System.err.println(hasCycle(node1));
    }
}
