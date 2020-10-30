package leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 345. 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * <p>
 * 示例 1：
 * 输入："hello"
 * 输出："holle"
 * 示例 2：
 * 输入："leetcode"
 * 输出："leotcede"
 * <p>
 * 真题: Google
 * 链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string
 *
 * @author mao  2020/10/23 4:54
 */
public class Solution345 {
    /**
     * 双指针碰撞法
     */
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int l = 0;          // 左指针
        int r = s.length() - 1;       // 右指针
        // 终止条件, 左右指针相撞
        while (l < r) {
            if (isVowel(arr[l]) && isVowel(arr[r])) {
                // 左右元素都是元音, 交换
                char tmp = arr[l];
                arr[l++] = arr[r];
                arr[r--] = tmp;
            } else if (!isVowel(arr[l])) {
                // 左元素不是元音, 左指针右移
                l++;
            }else{      // !isVowel(arr[r])
                // 右元素不是元音, 右指针左移
                r--;
            }
        }
        return new String(arr);
    }

    /**
     * 判断是不是元音, 比 ArrayList.contains() 效率更高
     */
    private boolean isVowel(char ch) {
        // 这里要直接用 return 语句返回，不要返回 true 或者 false
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'
                || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }

}
