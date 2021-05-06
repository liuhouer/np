package cn.northpark.LeetCode.链表;

/**
 * @author zhangyang
 * @date 2021年04月27日 09:24:01
 203. 移除链表元素
给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 
 */
public class L203 {

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

    //保留源链表数据
    ListNode dummyHead = new ListNode(Integer.MIN_VALUE);

    //在纸上画一下，很简单就可以答出来，debug可以很好理解。不要只看别人的代码，容易困惑
    public ListNode removeElements(ListNode head, int val) {
        //保留源链表数据
        dummyHead.next = head;
        //上一个链表数据，用于清除删除的元素
        ListNode prev = dummyHead;
        while (head != null) {
            //判断head值是否等于删除值
            if (head.val != val) {
                //前一个待指向节点赋值
                prev = head;
                head = head.next;
            }else{
                //前一个待指向节点下一个等于当前删除节点的下一个
                prev.next = head.next;
                head = head.next;
            }
        }

        return dummyHead.next;

    }

    public static void main(String[] args) {
        L203 l203 = new L203();
        ListNode node1 = l203.new ListNode(1);
        ListNode node2 = l203.new ListNode(2);
        ListNode node3 = l203.new ListNode(6);
        ListNode node4 = l203.new ListNode(3);
        ListNode node5 = l203.new ListNode(4);
        ListNode node6 = l203.new ListNode(5);
        ListNode node7 = l203.new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        l203.removeElements(node1,6);
    }
}
