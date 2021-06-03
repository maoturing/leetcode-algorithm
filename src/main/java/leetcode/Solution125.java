package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 * <p>
 * 真题: Facebook Microsoft Uber Zenefits
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 *
 * @author mao  2020/10/23 4:08
 */
public class Solution125 {
    /**
     * 傻瓜版
     * 读题:
     * 1. 空白字符需要考虑吗? 该题中不需要考虑
     * 2. 字符的定义, 是字母和数字还是所有ascii, 该题中只考虑字母和数字
     * 3. 字母大小写问题, 该题中忽略大小写
     * <p>
     * 思路: 先去除非数字和字母, 再反转, 再比较
     */
    public boolean isPalindrome(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {         // 这几个方法都很少用, 但是很常用
                builder.append(Character.toLowerCase(c));
            }
        }
        System.out.println(builder);
        // 这里必须new, 否则会将builder本身反转并返回
        StringBuilder reverse = new StringBuilder(builder).reverse();
        System.out.println(reverse);
        return reverse.toString().equals(builder.toString());
    }

    /**
     * 优化版: 双指针对撞, 关键在于确定指针移动条件
     * 两个指针(i,j )分别从最左和最右出发,
     * 1. 当左元素等于右元素, 则左指针++, 右指针--, 比较下一个元素
     * 2. 当左元素非数字和字符, 则左指针右移++
     * 3. 当右元素非数字和字符, 则右指针左移--
     * 4. 当左元素不等于右元素, 说明不是回文, 返回false
     */
    public boolean isPalindrome2(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        int l = 0;      // 左指针,
        int r = chars.length - 1;       // 右指针

        // 终止条件是两指针对撞
        while (l < r) {
            // 当左元素与右元素相等, 则是回文, 继续比较下一个
            if(chars[l] == chars[r]) {
                l++;
                r--;
            }else if(!Character.isLetterOrDigit(chars[l])){
                l++;
            }else if(!Character.isLetterOrDigit(chars[r])) {
                // 若左元素与右元素不等, 且是右元素非数字和字符, 则右指针左移
                r--;
            }else {
                // 左元素与右元素不相等, 不是回文
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        String s = "race a car";
        boolean palindrome = isPalindrome(s);

        Assert.assertFalse(palindrome);
        System.out.println(palindrome);
    }
    @Test
    public void test2() {
        String s = "A man, a plan, a canal: Panama";
        boolean palindrome = isPalindrome2(s);

        Assert.assertTrue(palindrome);
        System.out.println(palindrome);
    }
}
