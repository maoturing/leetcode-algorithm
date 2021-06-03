package leetcode;

import org.junit.Test;
import utils.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 * <p>
 * 示例 2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 * @author mao  2021/1/17 1:40
 */
public class Solution83 {
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode cur = head;
        ListNode after = head.next;

        while (cur.next != null) {
            if (cur.equals(after)) {            // 在leetcode中建议修改为 if (cur.val == after.val) {    多调试不要硬想
                after = after.next;
                continue;
            }
            cur.next = after;
            cur = after;
            after = after.next;
        }
        return head;
    }
    public ListNode deleteDuplicates3(ListNode head) {
        ListNode cur = head;
        ListNode after = head.next;

        while (cur.next != null) {
            if (cur.val != after.val) {
                cur.next = after;
                cur = cur.next;
                after = after.next;
                continue;
            }
            after = after.next;
        }
        return head;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    @Test
    public void test() {
        int arr[] = {2, 3, 3};
        ListNode listNode = ListNode.createMyLinkedList(arr, arr.length);
        ListNode.print(listNode);

        // 反转链表
        ListNode result = deleteDuplicates(listNode);
        ListNode.print(result);
    }
}
