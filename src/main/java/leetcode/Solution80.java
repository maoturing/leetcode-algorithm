package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 删除排序数组中的重复项 II
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * 给定 nums = [1,1,1,2,2,3],
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 *
 * @author mao  2020/10/22 23:07
 */
public class Solution80 {
    /**
     * 思路：双指针，与26思路类似, 注意是有序数组
     * k是慢指针, i是快指针, [0...k] 中没有重复两次以上的元素
     * 1. 当nums[i]与数组无重复部分的最后一个元素nums[k]不等, 就表示nums[i]没有重复两次, 就复制 nums[i]到 nums[k], 并同时自增 i 和 k,
     * 2. 当nums[i]与nums[k]相等, 但与倒数第二个元素num[k-1]不等时, 就表示nums[i]没有重复两次, 就复制 nums[i]到 nums[k], 并同时自增 i 和 k,
     * 3. 当nums[i]与nums[k]相等, 且与nums[k-1]相等, 则自增i跳过该元素 !(a || b) => (!a && !b)
     *
     * 重复这一过程, 直到 i 到达数组末尾, 该数组前k个元素都不为0
     * 时间: O(n)
     * 空间: O(1)
     *
     */
    public int removeDuplicates(int[] nums) {
        int k = 1;        // [0...k] 中没有重复两次以上的元素
        for (int i = 2; i < nums.length; i++) {

            // nums[i]不是重复元素, 将其拷贝到有序部分
            // 1. nums[i]与前一位不相等
            // 2.nums[i]与前一位相等但是与前前一位不相等
            if (nums[i] != nums[k] || nums[i] != nums[k - 1]) {
                k++;        // 维护[0...k] 中没有重复两次以上元素, k被称为循环不变量
                nums[k] = nums[i];
            }
        }
        return k + 1;       // k是无重部分的索引, 需要返回数组无重部分的长度
    }

    @Test
    public void test() {
        int[] nums = {1,1,1,2,2,3};
        int count = removeDuplicates(nums);
        Assert.assertEquals(5, count);
        System.out.println("无重复部分长度: " + count);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
    @Test
    public void test2() {
        int[] nums = {0,0,1,1,1,1,2,3,3};
        int count = removeDuplicates(nums);
        Assert.assertEquals(7, count);
        System.out.println("无重复部分长度: " + count);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
