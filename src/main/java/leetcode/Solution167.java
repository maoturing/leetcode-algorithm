package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * 真题: Amazon
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 *
 * @author mao  2020/10/23 3:29
 */
public class Solution167 {
    /**
     * 读题, 以下问题需要和面试官沟通
     * 1. 不能使用一个数字两次;
     * 2. 如果没有解怎么办?
     * 3. 如果有多个解怎么办?
     * <p>
     * 数组是有序的
     * 思路: 遍历整个数组, 使用二分搜索查找与第i个元素相加等于目标target的元素
     * 时间: O(nlogn), 每一次遍历使用一次O(lgn)的二分搜索
     */
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            // 在[i+1...length)中查找匹配的元素
            int index = binarySearch(numbers, i + 1, numbers.length, target - numbers[i]);

            if (index != -1) {
                // 因为要返回的是第几个元素而不是索引, 从1开始
                return new int[]{i + 1, index + 1};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 优化版: 双指针碰撞法, 关键在于确定指针移动条件
     * 数组是有序的, 两个指针(i,j )分别从最左和最优逐渐逼近 target,
     * 当二者和大于 target, 需要减小右元素(j左移), 这种情况是不是可以减小左元素呢? 不可以, 因为左元素就是因为太小才右移的
     * 当二者和小于 target, 需要增大左元素(i右移), 直至二者和等于target
     * 终止条件是 i 和 j 相遇
     */
    public int[] twoSum1(int[] numbers, int target) {
        int l = 0;          // 左指针, 表示求得结果中较小值的索引
        int r = numbers.length - 1;     // 右指针, 表示求得结果中较大值的索引

        while (l < r) {
            if (target == numbers[l] + numbers[r]) {
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] > target) {
                // 数组是有序的, 一定有num[j]>num[i], 相加大于目标值肯定要减小右指针
                r--;
            } else {       // target > numbers[i] + numbers[j]
                l++;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 二分搜索
     */
    public static int binarySearch(int[] arr, int start, int length, int target) {
        int l = start, r = length - 1;      // 在集合[l...r]中查找target, 左闭右闭

        while (l <= r) {        // 在[l...r]的范围里寻找target, 当l==r时这个区间仍然有元素
            int mid = (l + r) / 2;      // 防止整型溢出改为 l+(r-l)/2
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                l = mid + 1;        // 在[mid+1....r]中继续查找
            }
            if (arr[mid] > target) {
                r = mid - 1;        // 在[l...mid-1]中继续查找
            }
        }
        return -1;
    }

    /**
     * 傻瓜版
     * 双重循环查找
     */
    public int[] twoSum2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i; j < numbers.length; j++) {
                if (numbers[j] == target - numbers[i]) {

                    // 返回的是第几个元素, 不是索引, 所以要加1
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return new int[]{-1, -1};
    }

    @Test
    public void test() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] twoSum = twoSum1(nums, target);
        Assert.assertEquals(1, twoSum[0]);
        Assert.assertEquals(2, twoSum[1]);

        System.out.println(nums[0]);
        System.out.println(nums[1]);
    }
}
