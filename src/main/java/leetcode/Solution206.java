package leetcode;


import org.junit.Test;
import utils.ListNode;

/**
 * 206. 反转链表
 * 反转一个单链表。
 * <p>
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * <p>
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * @author mao  2021/1/15 0:20
 */

public class Solution206 {
    /**
     * 思路: 1->2->3->4->5->NULL
     * 1. 要修改当前节点的 next 为前一个节点, 需要记录下后一个节点; 当前节点为2, 记录后一个节点3, 反转链表使得2->1
     * 2. 然后修改下一个节点3, 依次进行进行反转
     * 3. 处理边界, 第一个节点1反转后指向NULL, 1->NULL; 最后一个节点5, 最后返回节点5为链表头结点
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        // pre->cur->next
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            // 1. 提前记录next, 因为下面要反转链表
            ListNode next = cur.next;
            // 2. 反转链表
            cur.next = pre;
            // 将三个指针向右移动一个位置, 此时cur指向第2个元素
            pre = cur;
            // 3. 开始反转下一个节点
            cur = next;
        }
        // 最后一次, cur=null, 需要返回的是pre
        return head = pre;
    }

    @Test
    public void test() {
        int arr[] = {1, 2, 3, 4, 5};
        ListNode listNode = ListNode.createMyLinkedList(arr, arr.length);
        ListNode.print(listNode);

        // 反转链表
        ListNode result = reverseList(listNode);
        ListNode.print(result);
    }
}
