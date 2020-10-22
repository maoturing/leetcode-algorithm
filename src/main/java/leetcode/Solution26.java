package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 删除有序数组中的重复元素
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 真题: Facebook Microsoft Bloomberg
 *
 * @author mao  2020/10/22 21:52
 */
public class Solution26 {
    /**
     * 与27, 283 问题解决思路类似
     * 注意 nums 是有序数组, 即重复元素总是相邻的
     * <p>
     * 思路: 双指针
     * k是慢指针, i是快指针, [0...k] 中没有重复元素
     * 当nums[i]与数组无重复部分的最后一个元素nums[k]相等时, 即num[i]为重复元素, 自增i跳过该元素,
     * 当nums[i]与数组无重复部分的最后一个元素nums[k]不等时, 就复制 nums[i]到 nums[k], 并同时自增 i 和 k,
     * 重复这一过程, 直到 i 到达数组末尾, 该数组前k个元素都不为0
     * <p>
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution/
     *
     * 时间: O(n)
     * 空间: O(1)
     */
    public int removeDuplicates(int[] nums) {
        int k = 0;        // [0...k] 中没有重复元素
        for (int i = 1; i < nums.length; i++) {

            // nums[i]不是重复元素, 将其拷贝到无重复部分
            if (nums[i] != nums[k]) {
                k++;        // 维护[0...k] 中没有重复元素
                nums[k] = nums[i];
            }
        }
        return k + 1;       // k是无重复部分的索引, 需要返回数组无重复部分的长度
    }

    /**
     * 优化版: 对有序数组nums没有重复元素的情况进行优化
     */
    public int removeDuplicates2(int[] nums) {
        int k = 0;        // [0...k] 中没有重复元素
        for (int i = 1; i < nums.length; i++) {

            // nums[i]不是重复元素, 将其拷贝到无重复部分
            if (nums[i] != nums[k]) {
                // 对nums没有重复元素的用例进行优化
                if (i != k + 1) {
                    nums[++k] = nums[i];    // 维护[0...k] 中没有重复元素
                }
            }
        }
        return k + 1;       // k是无重复部分的索引, 需要返回数组无重复部分的长度
    }

    @Test
    public void test() {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int count = removeDuplicates2(nums);
        Assert.assertEquals(5, count);
        System.out.println("无重复部分长度: " + count);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
    @Test
    public void test2() {
        int[] nums = {1, 1, 2};
        int count = removeDuplicates2(nums);
        Assert.assertEquals(2, count);
        System.out.println("无重复部分长度: " + count);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
