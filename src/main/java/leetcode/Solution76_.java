package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
 * <p>
 * 示例：
 * 输入：S = "ADOBECODEBANC", T = "ABC"
 * 输出："BANC"
 * <p>
 * 提示：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 真题: Facebook LinkedIn Uber Snapchat
 *
 * @author mao  2020/10/27 2:09
 */
public class Solution76_ {
    /**
     * 1. 有多个解需要全部输出吗? 题目保证只有一个解
     * 2. S包含T所有字符, 即T中每个字符, 都能在S中找到, 区分大小写
     * 3. 什么叫包含所有字符, S="a"  T="aa"  S不包含T所有字符
     *
     */
    public String minWindow(String s, String t) {
        char[] targetArray = t.toCharArray();
        List<String> minString = new LinkedList();
        int l = 0, r = -1;              // [l...r]为滑动窗口

        return "";
    }

    private boolean contains(List minString, char[] targetArray) {
        for (int i = 0; i < targetArray.length; i++) {
            if(!minString.contains(targetArray[i])) {
                return false;
            }
        }
        return true;
    }
}
