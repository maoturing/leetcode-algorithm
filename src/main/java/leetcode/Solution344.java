package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 344. 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 * 示例 1：
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 *
 * 链接：https://leetcode-cn.com/problems/reverse-string
 *
 * @author mao  2020/10/23 4:41
 */
public class Solution344 {
    /**
     * 难点在于空间复杂度必须为 O(1), 即必须原地修改输入数组
     * 思路: 两指针碰撞法
     * 交换左指针与右指针的元素, 然后左指针右移++, 右指针左移--
     */
    public void reverseString(char[] s) {
        int l = 0;          // 左指针
        int r = s.length - 1;       // 右指针
        while (l < r) {
            char tmp = s[l];
            s[l++] = s[r];
            s[r--] = tmp;
        }
    }

    /**
     * 这个并不好理解, 两指针碰撞终止条件是指针相遇更好理解一些
     */
    public void reverseString2(char[] s) {
        int r = s.length - 1;
        for (int i = 0; i < s.length / 2; i++) {
            char tmp = s[i];
            s[i] = s[r];
            s[r--] = tmp;
        }
    }

    @Test
    public void test2() {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString2(s);
        for (char c : s) {
            System.out.print(c);
        }
    }
}
