package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 真题: Amazon Adobe Yelp BloomBerg
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 *
 * @author mao  2020/10/26 15:15
 */
public class Solution3 {
    /**
     * 1. 大小写是否敏感?  大小写敏感
     * 2.
     */
    /**
     * 暴力解法
     * <p>
     * 使用一个 O(n) 的 subString 保存无重复字符的子串,
     * 分别遍历字符串[0...len) [1..len)..., 从[i...len)中查找不重复的子串,
     * 维护这个list, 当出现重复子串时, 遍历结束, 就可以得到[i...len) 中最长的不重复子串
     * <p>
     * 时间: O(n^3), 从 subString 中查找当前字符是否出现过, 也需要一次循环
     * 空间: O(n)
     */
    public int lengthOfLongestSubstring2(String s) {
        int len = s.length();
        int result = 0;
        List<Character> subString = new ArrayList<>();   // 保存无重复字符的子串

        // 分别从[0...len) [1...len) ... 中遍历所有子串, 筛选出无重复字符的子串
        for (int i = 0; i < len; i++) {
            // 找出[i...len]中最长的不重复子串
            for (int j = i; j < len; j++) {
                // 如果当前字符之前出现过, 则直接跳过
                if (subString.contains(s.charAt(j))) {
                    break;
                } else {
                    // 没有出现重复字符, 维护 subString 不重复子串
                    subString.add(s.charAt(j));
                }
            }
            // [i...len)中找出来的最长不重复子串为subString, 保留较长者的子串长度
            result = Math.max(result, subString.size());

            // 当result大于剩下的字符串[i...len-1]长度时, 不需要再遍历, 因为不可能找出比result更长的子串了
            if (result > len - 1 - i) {
                break;
            }
            // 清空不重复子串 不变量, 供下一次遍历使用
            subString.clear();
        }
        return result;
    }

    /**
     * 滑动窗口法
     * 从字符串s中查找无重复字符的子串, 人们最直觉的做法就是做一个可滑动的窗口,
     * 然后观察窗口内的元素是否重复, 如果没有重复元素, 就扩大窗口边界
     *
     * 时间: O(n) 效果并不好, 瓶颈在哪里?
     * 空间: O(n)
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();   // 保存无重复子串

        int l = 0, r = 0;       // 窗口[l...r]
        int result = 0;

        // 类似暴力解法的外层循环, 终止条件是遍历结束
        while (r < s.length()) {
            // 当前滑动窗口内无重复元素，右边界r一直右移
            if (!set.contains(s.charAt(r))) {
                set.add(s.charAt(r));
                r++;            // 维护滑动窗口[l...r]
            } else { // 遇到窗口内已有的元素，左边界l一直右移直到重复元素不在Set内
                while (set.contains(s.charAt(r))) {
                    set.remove(s.charAt(l));
                    l++;        // 维护滑动窗口[l...r]
                }
            }
            result = Math.max(result, r - l);
        }

        return result;
    }

    /**
     * 优化版
     */
    public int lengthOfLongestSubstring3(String s) {
        char[] charArray = s.toCharArray();
        int[] freq = new int[256];      // 保存与索引相等的ascii字符, 用1标记出现了一次
        int l = 0, r = -1;              // [l...r]为滑动窗口
        int result = 0;
        while (l < s.length()) {
            if (r + 1 < charArray.length && freq[charArray[r + 1]] == 0) {
                // 不存在重复字符, 右移右指针++r, 并保存字符到freq中
                r++;        // 维护滑动窗口[l...r]
                freq[charArray[r]]++;
            } else {
                // 存在重复字符, 删除左边界的那个字符, 右移左指针l++, 直至子串中不存在重复字符
                freq[charArray[l]]--;
                l++;        // 维护滑动窗口[l...r]
            }
            result = Math.max(result, r - l + 1);
        }
        return result;
    }

    @Test
    public void test2() {
        String s = "pwwkew";
        int count = lengthOfLongestSubstring(s);
        Assert.assertEquals(3, count);
        System.out.println("无重复子串: " + count);
    }
}
