package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mao  2020/10/21 2:12
 */
public class Solution925 {

    /**
     * 慢指针 i 指向 name，快指针 j 指向 typed，如果name[i]等于typed[j]，则两个指针都后移一位；
     * 如果发现两者不等，则比较 typed[j] 与 typed[j-1] 字符是否相等，相等则快指针j后移一位, 不相等则返回false
     *
     * 时间复杂度：O(N+M)，其中 M, N 分别为两个字符串的长度。
     * 空间复杂度：O(1)。
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        int i = 1;      // 慢指针, 标记 name
        int j = 1;      // 快指针, 标记 typed
        // 1. 如果name[0]不等于typed[0]，说明一定false了
        if (name.charAt(0) != typed.charAt(0)) {
            return false;
        }
        // 2. 在name[i]不等于typed[j]的情况下，如果当前的typed[j]不等于j指针前一次指的字母, 即不是重复字母, 则返回false
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {    // 避免产生 name 越界错误
                // name 与 typed 对应的字符相等, 则继续比较下一个字符
                i++;
                j++;
            } else if (typed.charAt(j - 1) == typed.charAt(j)) {
                // typed 字符重复, 则继续比较 typed 下一个字符与当前字符是否相等
                j++;
            } else {
                return false;
            }
        }

        // 3. name 没有被输入完整, 如果最后比对结束了，i指针都还没有移到name的最后，说明name的末尾没有被输入完，直接判false。
        return i == name.length();
    }

    @Test
    public void test() {
        Assert.assertEquals(true, isLongPressedName("alex", "aaleex"));
    }
}
