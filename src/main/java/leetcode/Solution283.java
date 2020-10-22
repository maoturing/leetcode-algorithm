package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 删除元素 0
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 真题: Facebook Bloomberg
 * @author mao  2020/10/22 0:34
 */
public class Solution283 {

    /**
     * 两次循环, 第一次将非0元素移动到数组前面, k用来标识非0元素的范围
     * 数组[0...length) 中 [0...k) 存储非零元素, [k...length) 存储0元素
     * 只需要将每一个非0元素移动到第k个位置即可
     * <p>
     * 思路: 双指针
     * k是慢指针, i是快指针, 当nums[i]为0时, 自增i跳过该元素,
     * 当nums[i]!=0 时, 就复制 nums[i]到 nums[k], 并同时自增 i 和 k,
     * 重复这一过程, 直到 i 到达数组末尾, 该数组前k个元素都不为0
     * 参考《玩转leetcode》 3-4 动画演示
     *
     * 时间: O(n)
     * 空间: O(1)
     */
    public void moveZeroes(int[] nums) {
        int k = 0;      // [0...k) 中元素均为非0元素

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 当前元素非0, 将该元素放入nums[k]
                nums[k] = nums[i];
                // 维护 [0...k) 中元素均为非0元素
                k++;
            }
        }

        // [k....length) 均为 0
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 优化答案
     * 对moveZeroes() 进行优化, 在移动非0数字时进行交换, 将数字0交换到数组最后,
     * 省去了再次循环赋值的麻烦. 另外还对全是非零数字的用例进行优化
     *
     * @param nums
     */
    public void moveZeroes1(int[] nums) {
        int k = 0;      // [0...k) 中元素均为非0元素

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 对全是非零数字的用例进行优化, 不需要赋值和交换
                if (i != k) {
                    // 当前元素非0, 将该元素放入nums[k]
                    nums[k] = nums[i];
                    // 交换 k 与 i 元素, 不需要再次为[k....length)元素赋值为0
                    nums[i] = 0;
                }
                // 维护 [0...k) 中元素均为非0元素
                k++;
            }
        }
    }

    /**
     * 类似冒泡排序的思想, 但是无法解决 [0,0,1] 测试用例, 这个算法有问题
     *
     * @param nums
     */
    @Deprecated
    public void moveZeroes2(int[] nums) {
        // 遍历nums, 将第n个零移动到倒数第n个位置 length-k
        int k = 0;      // 0 的数量, [0....length-k-1]中都是非零数字, [length-k...length-1]都是0
        for (int i = 0; i < nums.length - k; i++) {
            if (nums[i] == 0) {
                k++;
                // 遇到0后, 将[i+1....length-k]的所有数字前移一位,
                for (int j = i + 1; j <= nums.length - k; j++) {
                    nums[j - 1] = nums[j];
                }
                // 将倒数第n个元素修改为0
                nums[nums.length - k] = 0;
            }
        }
    }

    @Test
    public void test() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes1(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }

        int[] nums2 = {0, 0, 1};
        moveZeroes1(nums2);
        for (int i = 0; i < nums2.length; i++) {
            System.out.println(nums2[i]);
        }
    }
}
