package utils;


import java.util.Objects;

/**
 * @author mao  2021/1/17 1:20
 */
public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode listNode = (ListNode) o;
        return val == listNode.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    public static ListNode createMyLinkedList(int arr[], int n) {
        if (n == 0) {
            return null;
        }

        // 创建头结点
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        // 依次创建链表结点
        for (int i = 1; i < n; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * 打印链表
     * @param listNode
     */
    public static void print(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + "->");
            listNode = listNode.next;
        }
        System.out.println("NULL");
    }
}
