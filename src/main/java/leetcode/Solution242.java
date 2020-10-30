package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * 真题: Amazon Uber yelp
 * @author mao  2020/10/30 23:04
 */
public class Solution242 {


    /**
     * 利用Map的特性, 所谓的异位词就是判断两个字符串的所有字符是否相等, 字符是可以重复的
     *
     * 时间: O(2n), 14 ms , 在所有 Java 提交中击败了 21.55% 的用户
     * 空间: O(n), 39.5 MB , 在所有 Java 提交中击败了 15.50% 的用户
     */
    public boolean isAnagram2(String s, String t) {
        // 异位词必须长度相同
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (Character ch : s.toCharArray()) {
            Integer count = map.getOrDefault(ch, 0) + 1;
            map.put(ch, count);
        }

        for (Character ch  : t.toCharArray()) {
            Integer count = map.get(ch);
            if (count != null && count > 0) {
                map.put(ch, count - 1);
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * 优化版
     * 将字符保存在数组中, 由于字符串元素只能是26个英文字母, 所以只需要26个位置即可
     *
     * 没想到 map 的效率这么低
     * 时间: O(2n), 5 ms , 在所有 Java 提交中击败了 48.88% 的用户
     * 空间: O(1), 38.5 MB , 在所有 Java 提交中击败了 91.63% 的用户
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
