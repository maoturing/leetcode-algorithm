package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * <p>
 * 示例 1:
 * 输入: s = "egg", t = "add"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 *
 * @author mao  2020/10/31 1:45
 */
public class Solution205 {
    /**
     * 与290思路类似
     * 遍历s和t, 保存映射关系到map, 如果不存在映射关系, 则添加映射到map;
     * 如果map中已存在映射关系, 则检查是否与前面的映射关系一致,
     * 如果一致, 则继续遍历, 如果不一致, 则说明不是同构字符串, 返回false
     *
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        String[] s1 = s.split("");
        String[] t1 = t.split("");

        // 保存字符与字符串的映射关系
        Map<String, String> map = new HashMap<>();
        // 遍历pattern中所有字符, 对每个字符从 map 中查找映射的字符串, 如果与 words 中字符串相等则匹配返回true
        for (int i = 0; i < s1.length; i++) {
            String str = map.get(t1[i]);
            if (str != null && !str.equals(s1[i])) {
                return false;
            }

            // 添加映射关系
            if (!map.containsKey(t1[i])) {
                //判断 value 中是否存在, 为了解决测试用例"ab" 与 "cc"
                if (map.containsValue(s1[i])) {
                    return false;
                }
                map.put(t1[i], s1[i]);
            }
        }

        return true;
    }

    /**
     * 仅仅将 isIsomorphic() 中 String.split() 修改为 String.toCharArray()
     *
     * 总结: String.split() 是非常耗时的操作, 谨慎使用
     *
     * String.split() : 用时：45 ms , 在所有 Java 提交中击败了 5.02% 的用户
     * String.toCharArray: 用时：11 ms , 在所有 Java 提交中击败了 64.62% 的用户
     */
    public boolean isIsomorphic3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();

        // 保存字符与字符串的映射关系
        Map<Character, Character> map = new HashMap<>();
        // 遍历pattern中所有字符, 对每个字符从 map 中查找映射的字符串, 如果与 words 中字符串相等则匹配返回true
        for (int i = 0; i < s1.length; i++) {
            Character ch = map.get(t1[i]);
            if (ch != null && !ch.equals(s1[i])) {
                return false;
            }

            // 添加映射关系
            if (!map.containsKey(t1[i])) {
                //判断 value 中是否存在,
                if (map.containsValue(s1[i])) {
                    return false;
                }
                map.put(t1[i], s1[i]);
            }
        }

        return true;
    }

    /**
     * 与290类似
     * 举个现实生活中的例子，一个人说中文，一个人说法语，怎么判断他们说的是一个意思呢？把中文翻译成英语，把法语也翻译成英语，然后看最后的英语是否相同即可。
     * "egg" 翻译为 122, "add" 翻译为 122, 然后判断两个数字是否相等
     *
     * 这种思路也能够很容易解决 s = "abc"; t = "ddd" 的case
     */
    public boolean isIsomorphic2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        String[] s1 = s.split("");
        String[] t1 = t.split("");

        String intPattern = strToIntPattern(s1);
        return strToIntPattern(t1).equals(intPattern);
    }

    private String strToIntPattern(String[] array) {
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            Integer n = map.getOrDefault(array[i], i);
            // 将key映射为索引,
            map.put(array[i], n);
            sb.append(n);
        }
        System.out.println(sb);
        return sb.toString();
    }
}
