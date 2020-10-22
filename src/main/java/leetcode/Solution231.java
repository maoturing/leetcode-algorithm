package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: true
 * 解释: 2^0 = 1
 * 示例 2:
 * <p>
 * 输入: 16
 * 输出: true
 * 解释: 2^4 = 16
 * 示例 3:
 * <p>
 * 输入: 218
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-two
 *
 * @author mao  2020/10/21 0:34
 */
public class Solution231 {
    /**
     * 最佳答案:
     * 利用 2 进制数的特点，与(n-1)进行与运算，得到结果永远是0
     * https://leetcode-cn.com/problems/power-of-two/solution/power-of-two-er-jin-zhi-ji-jian-by-jyd/
     * <p>
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 35 MB , 在所有 Java 提交中击败了 99.96% 的用户
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }

    /**
     * 自己的解法, 判断二进制数 n-1 的每一位是不是1, 如果不是, 则n不是2的幂
     * <p>
     * 注意处理边界值 n=1
     * <p>
     * 效率一般，转二进制需要进行循环，遍历二进制char也需要循环，时间复杂度与参数 n 的二进制长度有关系
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 8.98% 的用户
     * 内存消耗： 35.2 MB , 在所有 Java 提交中击败了 99.73% 的用户
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo2(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }

        char[] binaryChar = Integer.toBinaryString(n - 1).toCharArray();
        System.out.println(binaryChar);

        for (char c : binaryChar) {
            if (c == '0') {
                return false;
            }
        }
        return true;
    }

    /**
     * Integer.bitCount(n) 返回整数 n 的二进制中 1 的个数, 2的n次幂中'1'的个数永远为1, 实现原理如下:
     * https://blog.csdn.net/Yohohaha/article/details/72744434
     */
    public boolean isPowerOfTwo3(int n) {
        return n > 0 && (Integer.bitCount(n) == 1);
    }

    @Test
    public void test() {
        Assert.assertEquals(true, isPowerOfTwo3(16));
        Assert.assertEquals(false, isPowerOfTwo3(218));
    }
}
