package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 290. 单词规律
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 示例1:
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * <p>
 * 示例 3:
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * <p>
 * 示例 4:
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
 *
 * 真题: Dropbox  Uber
 * @author mao  2020/10/31 0:45
 */
public class Solution290 {
    /**
     * 利用map的特性, 以及pattern与str长度相等的特性, 保存映射关系, 然后遍历pattern与str, 检查映射关系是否一致
     * 遍历pattern和s, 保存映射关系到map, 如果不存在映射关系, 则添加映射到map;
     * 如果map中已存在映射关系, 则检查是否与前面的映射关系一致,
     * 如果一致, 则继续遍历, 如果不一致, 则说明不匹配, 返回false
     *
     * https://leetcode-cn.com/problems/word-pattern/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--53/
     */
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.toCharArray().length) {
            return false;
        }

        // 保存字符与字符串的映射关系
        Map<Character, String> map = new HashMap<>();
        // 遍历pattern中所有字符, 对每个字符从 map 中查找映射的字符串, 如果与 words 中字符串相等则匹配返回true
        for (int i = 0; i < words.length; i++) {
            String s1 = map.get(pattern.charAt(i));
            if (s1 != null && !s1.equals(words[i])) {
                return false;
            }

            // 添加映射关系
            if (!map.containsKey(pattern.charAt(i))) {
                //判断 value 中是否存在,
                if (map.containsValue(words[i])) {
                    return false;
                }
                map.put(pattern.charAt(i), words[i]);
            }
        }

        return true;
    }

    /**
     * 举个现实生活中的例子，一个人说中文，一个人说法语，怎么判断他们说的是一个意思呢？把中文翻译成英语，把法语也翻译成英语，然后看最后的英语是否相同即可。
     * "abba" 翻译为 1221, "dog cat cat dog" 翻译为 1221, 然后判断两个数字是否相等
     *
     * 这种思路也能够很容易解决 pattern = "abba"; s = "dog dog dog dog" 的case
     *
     * 这种思路效率好低啊!!!
     * 经过验证, 耗时点在于 String.split() 操作, 如果修改为 String.toCharArray() 会好很多
     */
    public boolean wordPattern2(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.toCharArray().length) {
            return false;
        }
        String intPattern = strToIntPattern(pattern.split(""));
        return strToIntPattern(words).equals(intPattern);

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

    @Test
    public void test() {
        String pattern = "abba";
        String s = "dog cat cat dog";
        Assert.assertTrue(wordPattern2(pattern, s));
    }

    @Test
    public void test2() {
        String pattern = "abba";
        String s = "dog dog dog dog";
        Assert.assertFalse(wordPattern2(pattern, s));
    }
}
