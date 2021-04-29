/**
 * @author liuhouer
 * @date 2021年04月23日 
 *
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 */
public class L206 {
    /**
     * Definition for singly-linked list.
     */

    public class ListNode {
      int val;
      ListNode next;

      ListNode() {
      }

      ListNode(int val) {
          this.val = val;
      }

      ListNode(int val, ListNode next) {
          this.val = val;
          this.next = next;
      }
    }

    /**
     * 一个一个往前移动
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        ListNode dummy = new ListNode(0);

        dummy.next = head;
        //1 2 3 4 5

        while (head!=null && head.next!=null){
            dummy.next = head.next;//2
            head.next = head.next.next;//1->3
            head.next.next = dummy.next ; //3->2

        }
        return dummy.next;

    }




    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {

//        递归结束的条件，以及到了最后的位置
        if (head == null || head.next == null) {
//        直接返回该结点
            return head;
        }
        ListNode newHead = reverseList3(head.next);// 先反转后续节点head.getNext()
        head.next.next = head;// 将当前节点的下一节点指向当前节点，完成翻转
        head.next = null;// 将当前节点的下一节点位置置为空
        return newHead;

    }


    /**
     * 最好理解的，类似栈，取出一个，就设置一个next值
     * 1
     * 2->1
     * 3->2->1
     * ....
     *
     * @param head
     * @return
     */
    public ListNode reverseList4(ListNode head) {
        ListNode tailNode = null;
        while(head != null) {
            tailNode = new ListNode(head.val, tailNode);
            head = head.next;
        }
        return tailNode;
    }



    public static void main(String[] args) {
        L206 l203 = new L206();
        ListNode node1 = l203.new ListNode(1);
        ListNode node2 = l203.new ListNode(2);
        ListNode node3 = l203.new ListNode(3);
        ListNode node4 = l203.new ListNode(4);
        ListNode node5 = l203.new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode listNode = l203.reverseList3(node1);

        while (listNode!=null){
            System.err.print(listNode.val+"--->");
            listNode = listNode.next;
        }


    }
}
