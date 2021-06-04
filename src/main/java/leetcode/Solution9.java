package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 9. 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 * <p>
 * 示例 1：
 * 输入：x = 121
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 示例 3：
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 * <p>
 * 示例 4：
 * 输入：x = -101
 * 输出：false
 * 提示：
 * -231 <= x <= 231 - 1
 * <p>
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 *
 * @author mao  2021/6/3 15:22
 */
public class Solution9 {

    /**
     * 工程版:
     * 1. 直接利用 StringBuilder#reverse() 反转, 然后与原数字比较
     * 2. 特点是写法简单明了, 原理易懂.
     *
     * @param x
     * @return
     */
    public boolean isPalindrome0(int x) {
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reversedStr);
    }

    /**
     * 双指针碰撞法, 参考了 Solution125 的解法
     * 1. 当左元素等于右元素, 则左指针++, 右指针--, 比较下一个元素
     * 2. 当左元素不等于右元素, 则说明不是回文, 返回false
     * 3. 当左指针大于等于右指针, 则说明两指针碰撞, 返回true
     * <p>
     * 时间: O(n), 循环了半轮, n 是数字长度
     * 空间: O(n), 需要char数组保存数字
     *
     * @param x
     * @return
     * @see Solution125
     */
    public boolean isPalindrome1(int x) {
        // 负数不可能是回文数
        // 个位数为0不可能是回文数, 不包括0
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        // 一位数一定是回文数
        if (x < 10 && x >= 0) {
            return true;
        }

        String str = String.valueOf(x);
        char[] chars = str.toCharArray();

        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            if (chars[left] == chars[right]) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 双指针碰撞法: 从数字两边开始, 判断高位与低位是否相等
     * 1. 首先求出数字x的数量级, 如1221的数量级为1000
     * 2. x/level得到首位数字, x%10得到末位数字, 若二者不等, 则不是回文数
     * 3. 去除x的首尾数字, 继续重复第2步
     *
     * 思路: 这种思路是最直接简单粗暴的, 如果觉得利用一次循环求出level效率较低, 而放弃了这条思路
     * 那么后面的思路也不好想, 首先保证题做出来, 然后再优化思路, 这个很重要
     *
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        //边界判断
        if (x < 0) {
            return false;
        }
        int level = 1;
        // 求出数字数量级
        while (x / level >= 10) {
            level *= 10;
        }

        while (x > 0) {
            int left = x / level;
            int right = x % 10;
            // 若首位数字不等于末位数字, 则不是回文数
            if (left != right) {
                return false;
            }

            // 去除x的首位数字和末位数字
            x = (x % level) / 10;
            level /= 100;
        }
        return true;
    }

    /**
     * 反转比较法:
     * 1. 题目要求必须是整数, 不考虑小数
     * 2. 整数包括负数, 负数不可能是回文数
     * 3. 个位是0一定不是回文数
     * 4. 1位数一定是回文数
     * <p>
     * 思路: 反转后的数字, 与原数字相等, 一定是回文数
     * 通过计算 1221 / 1000, 得首位 1
     * 通过计算 1221 % 10, 可得末位 1
     * <p>
     * 改进: 数字只需要反转一半, 就可以确定是否为回文数了
     * 反转的方式存在溢出问题, 原数字是整型, 反转后整型溢出
     * <p>
     * 时间: O(n), 循环了一次, n 是数字长度
     * 空间: O(n)
     *
     * @param x
     * @return
     */
    public boolean isPalindrome3(int x) {
        // 负数不可能是回文数
        if (x < 0) {
            return false;
        }
        // 一位数一定是回文数
        if (x < 10 && x >= 0) {
            return true;
        }
        // 个位是0一定不是回文数, 0除外
        if (x % 10 == 0) {
            return false;
        }

        int num = x;
        int digits;
        int reversedNum = 0;

        // 利用取余数的方式, 反转数字
        // 若首位为0, 则反转结束
        while (num > 0) {
            digits = num % 10;          // 取末尾数字
            reversedNum = reversedNum * 10 + digits;

            num = num / 10;
        }

        // 若反转后的数字与原数字x是否相等, 则是回文数
        if (reversedNum == x) {
            return true;
        }

        return false;
    }


    /**
     * 优化版
     * 1. 反转比较法中会将数字完全反转, 才与原值比较, 确定是否为回文数
     * 2. 其实可以将数字反转一半, 数字两边若相等, 则为回文数了, 若过了一半还不相等, 则一定不是回文数
     * 3. 怎么确定数字反转了一半呢 ?
     * 其实不用确定数字一半, 每次将反转的部分, 与为反转剩余的部分进行比较, 直至二者相等, 则为回文数
     * 若反转的部分, 开始大于未反转的部分, 则说明已经过了数字长度的一半了.
     * 4. 优化版节省了一半的时间
     * 5. 避免了溢出问题
     *
     * 思路: 应该从易到难, 当时想明白了要优化为反转一半, 对于偶数长度的如1221, 反转部分与剩余部分比较相等即可.
     * 但是没想明白奇数长度的如121, 反转部分与剩余部分应该怎么比较, 此时应该只考虑偶数长度, 写完之后, 再针对奇数
     * 长度的数进行处理, 这里是将反转的部分 12/10 再与 未反转部分比较. 如果想着全想明白了再写难度会比较大
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        // 负数不可能是回文数
        // 个位数为0不可能是回文数, 不包括0
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        // 一位数一定是回文数
        if (x < 10 && x >= 0) {
            return true;
        }

        // 反转数字的一半长度
        // (优化) 反转部分reversedNum大于未反转部分num, 则说明过了已经反转了数字的一半长度, 不用继续反转了
        int reversedNum = 0;        // 省略了变量 num, x
        while (reversedNum < x) {
            reversedNum = reversedNum * 10 + x % 10;

            x = x / 10;
        }
        // 数字长度为双数, 如 1221. 若反转后的半边数字12与未反转的半边数字12相等, 则是回文数,
        // 数字长度为单数, 如 121.  若反转后的半边数字12/10与未反转的半边数字1相等, 则是回文数,
        return x == reversedNum || x == reversedNum / 10;
    }

    @Test
    public void test() {
        int x = 121;
        boolean palindrome = isPalindrome2(x);

        Assert.assertTrue(palindrome);
        System.out.println(palindrome);
    }
}
