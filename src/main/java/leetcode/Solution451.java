package leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * <p>
 * 示例 1:
 * 输入:
 * "tree"
 * 输出:
 * "eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * <p>
 * 示例 2:
 * 输入:
 * "cccaaa"
 * 输出:
 * "cccaaa"
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * <p>
 * 示例 3:
 * 输入:
 * "Aabb"
 * 输出:
 * "bbAa"
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 * @author mao  2020/10/31 2:27
 */
public class Solution451 {
    public String frequencySort(String s) {
        // 1. 将字符的出现次数保存在对应的索引位置
        int[] letters = new int[128];
        for (char c : s.toCharArray()) {
            letters[c]++;
        }

        // 2. 将出现过的字符放入优先队列, 队列顺序由出现次数 letters[] 决定
        PriorityQueue<Character> heap = new PriorityQueue<>(128, (a, b) -> Integer.compare(letters[b], letters[a]));
        for (char c : s.toCharArray()) {
            heap.offer(c);
        }

        // 3. 遍历优先队列, 根据出现次数拼接字符串
        StringBuilder res = new StringBuilder();
        while (!heap.isEmpty()) {
            char c = heap.poll();
            while (letters[c]-- > 0) {
                res.append(c);
            }
        }
        return res.toString();
    }

    // 未完成
    public String frequencySort2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            int count = map.getOrDefault(c, 0) + 1;
            map.put(c, count);


        }

        //第二部分：装桶
        List<Character>[] list = new List[s.length() + 1];

        int[][] arr = new int[s.length()][];        // 一维存次数, 二维存字符
        //装桶就是遍历所有key值装进去
        for (Character key : map.keySet()) {
            //通过key值返回找到频率赋给i
            int i = map.get(key);

            //i值是索引，代表出现的次数
            if (list[i] == null) {
                list[i] = new ArrayList();
            }
            list[i].add(key);
        }

        //第三部分，拆桶倒序输出
        char[] res = new char[s.length()];
        int index = 0;
        for (int i = list.length - 1; i > 0; i--) {
            if (list[i] == null) continue;
            //遍历所有需要输出的list[i]
            for (char p : list[i]) {
                for (int j = 0; j < i; j++) {
                    res[index++] = p;
                }
            }
        }
        return new String(res);
    }

    @Test
    public void test() {
        String s = "tree";
        String s1 = frequencySort(s);
        System.out.println(s1);
    }
}
