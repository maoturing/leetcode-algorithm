package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 删除目标元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 1:
 * 给定 nums = [3,2,2,3], val = 3,
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 2:
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * <p>
 * 注意这五个元素可为任意顺序。
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 链接：https://leetcode-cn.com/problems/remove-element
 *
 * @author mao  2020/10/22 14:01
 */
public class Solution27 {
    /**
     * 与 283 问题解决思路类似
     * 首先要读题, 确定问题:
     * 1. 如何定义删除, 从数组去除? 还是放在数组末尾?
     * 2. 剩余元素的排列是否要保证原有的相对顺序?
     * 3. 是否有空间复杂度的要求
     *
     * 思路: 双指针
     * 要求不适用额外空间, 必须用 O(1)空间处理
     * k 是慢指针, i是快指针, 当nums[i]与目标值val相等时, 自增i跳过该元素,
     * 当nums[i]!=val时, 就复制 nums[i]到nums[k], 并同时自增 i 和 k,
     * 重复这一过程, 直到 i 到达数组末尾, 该数组前k个元素没有目标值
     * https://leetcode-cn.com/problems/remove-element/solution/yi-chu-yuan-su-by-leetcode/
     *
     * 时间: O(n)  执行用时 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 空间: O(1)  内存消耗 37 MB , 在所有 Java 提交中击败了 94.66% 的用户
     */
    public int removeElement(int[] nums, int val) {
        int k = 0;      // [0...k) 是非val元素
        for (int i = 0; i < nums.length; i++) {
            // 遇到不等val的元素, 则放到数组第k位
            if (nums[i] != val) {
                nums[k] = nums[i];
                // 维护[0...k) 是非val元素
                k++;
            }
        }
        return k;
    }

    /**
     * 错误答案
     * @param nums
     * @param val
     * @return
     */
    @Deprecated
    public int removeElement2(int[] nums, int val) {
        int k = 0;      // [0...k) 是非val元素
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                // 当前元素等于val. 将最后一个元素移动到当前位置
                // 这种思路存在一个问题, 最后一个元素也可能等于val, 这个元素将不会再被检查到
                nums[i] = nums[nums.length - 1 - k];
                k++;
            }
        }
        return k;
    }

    @Test
    public void test() {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;

        int counts = removeElement(nums, val);
        Assert.assertEquals(5, counts);
        // 输出数组新长度
        System.out.println("新数组长度: "+counts);
        // 输出数组
        for (int i = 0; i < counts; i++) {
            System.out.println(nums[i]);
        }
    }

    @Test
    public void test2() {
        int[] nums = {3,2,2,3};
        int val = 3;

        int counts = removeElement(nums, val);
        Assert.assertEquals(2, counts);
        // 输出数组新长度
        System.out.println("新数组长度: "+counts);
        // 输出数组
        for (int i = 0; i < counts; i++) {
            System.out.println(nums[i]);
        }
    }
}
