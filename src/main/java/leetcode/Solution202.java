package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 * <p>
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 * <p>
 * 示例：
 * 输入：19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * <p>
 * 真题: Uber Airbnb Twitter
 *
 * @author mao  2020/10/30 23:47
 */
public class Solution202 {
    /**
     * 这个问题, 读懂题是关键, 读懂 HappyNumber 数字变化的规律是关键, 参考链接:
     * https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
     * <p>
     * 数字不断进行递归, 最终可能有以下三种情况
     * 1. 最终会得到 1。
     * 2. 最终会进入循环。
     * 3. 值会越来越大，最后接近无穷大。 (不存在这种可能)
     *
     */
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();

        // 第2种情况, 进入了死循环, 如果 n 出现过, 则这个数不是 HappyNumber
        // 终止条件是等于 1, 或者进入死循环
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        // 第1种情况, 最终等于1, 这个数是HappyNumber
        return n == 1;
    }

    /**
     * 递归写法
     */
    public boolean isHappy2(int n) {
        Set<Integer> seen = new HashSet<>();      // 用于保存n的转换数, 检测是否进入循环
        boolean x = isHappyNumber(n, seen);
        return x;
    }

    private boolean isHappyNumber(int n, Set<Integer> seen) {
        // 第1种情况, 最终等于1, 这个数是HappyNumber
        if (n == 1 ) {
            return true;
        }
        // 第2种情况, 进入了死循环, 如果 n 出现过, 则这个数不是 HappyNumber
        if(seen.contains(n)) {
            return false;
        }
        seen.add(n);
        int next = getNext(n);

        // 递归调用
        return isHappyNumber(next, seen);
    }

    /**
     * 得到数字 n 的经过 Happy 之后的结果
     */
    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            // 得到 n 的个位数字 d
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }


    @Test
    public void test() {
        boolean happy = isHappy2(19);
        Assert.assertTrue(happy);

        System.out.println(happy);
    }

    @Test
    public void test2() {
        boolean happy = isHappy(116);
        Assert.assertFalse(happy);

        System.out.println(happy);
    }

    @Test
    public void test3() {
        boolean happy = isHappy2(2);
        Assert.assertFalse(happy);
        System.out.println(happy);
    }
}
