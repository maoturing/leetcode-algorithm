package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 * <p>
 * 示例：
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * <p>
 * 真题: Facebook
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 *
 * @author mao  2020/10/26 12:14
 */
public class Solution209 {
    /**
     * 滑动窗口法
     * <p>
     * 1. 子数组的定义，在这里需要是连续的子数组
     * 2. 如果不存在解，该如何处理？ 返回 0
     * 3. 有多个解，是全部返回还是？
     * 4. 元素都是正整数
     *
     * 暴力法是分别遍历[0...j] [1...j] ...子数组, 当元素之和大于s时, 终止遍历, 返回子数组长度
     *
     * 滑动窗口法是将暴力法的外层循环, 化简为子数组左指针右移,
     * 滑动窗口法是遍历[l...r]，当元素之和小于s时，需要右移右指针r++, 使得元素和更接近s
     * 当元素之和大于s时, 需要右移左指针, 使得元素和更接近s
     *
     * 当元素之和大于s时, 为什么不左移右指针r--, 同样可以使得元素和更接近s ?
     * 因为右指针就是从最左边移动过来的, 左移右指针r--, 相当于回到了上一步
     *
     * 滑动窗口的思路有点难理解, 先理解暴力法, 滑动窗口是暴力法的升级, 将外层循环变成了子数组左指针右移, 降低了事件复杂度
     *
     * 时间: O(n)
     * 空间: O(1)
     * https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/209-chang-du-zui-xiao-de-zi-shu-zu-hua-dong-chua-7/
     */
    public int minSubArrayLen(int s, int[] nums) {
        int l = 0, r = -1;      // nums[l...r] 为滑动窗口
        int sum = 0;            // 子数组元素之和
        int result = nums.length + 1;       // 子数组最短值, 初始值设置为不可能的长度 length+1, 与MAX_VALUE作用类似

        while (l < nums.length) {
            if (r + 1 < nums.length && sum < s) {
                // 子数组之和小于s, 相当于暴力法的内层循环, 右移右指针增大元素之和, 使得元素和更接近s
                r++;
                sum += nums[r];
            } else {        // (r + 1 > nums.length || sum > s)
                // 子数组之和大于s, 相当于暴力法的外层循环, 右移左指针减小元素之和, 使得元素和更接近s
                sum -= nums[l];
                l++;
            }
            // 找到了符合条件的子数组, 保留更小的数组长度
            if (sum >= s) {
                result = Math.min(result, r - l + 1);
            }
        }
        // 没有符合条件的子数组, 返回0
        return result == nums.length + 1 ? 0 : result;
    }

    /**
     * 暴力解
     * 目标是最短的子数组, 条件是子数组元素之和大于 s
     * <p>
     * 时间: O(n^2)
     * 空间: O(1)
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int result = Integer.MAX_VALUE;         // 子数组长度, 目标是找最短的子数组, 所以这里设置为最大值

        // [i...j] 为子数组，筛选出元素之和大于s的子数组, 并从中找出最短的子数组
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;

            // 分别判断[0...j] [1...j] ...子数组, 当元素之和大于s时, 终止遍历, 返回子数组长度
            for (int j = i; j < nums.length; j++) {
                sum = sum + nums[j];
                // 对符合条件的子数组, 查看其是否是最短的
                if (sum >= s) {
                    result = Math.min(result, j - i + 1);

                    // 目标是找最短子数组, 接下来的子数组会越来越长, 所以直接break
                    break;
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
