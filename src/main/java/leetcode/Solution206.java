package leetcode;

import org.junit.Test;

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
    public ListNode reverseList(ListNode head) {
        // pre->cur->next
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            // 反转链表
            cur.next = pre;
            // 将三个指针向右移动一个位置, 此时cur指向第2个元素
            pre = cur;
            cur = next;
        }
        // 最后一次, cur=null, 需要返回的是pre
        return head = pre;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
