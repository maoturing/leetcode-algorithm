package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * <p>
 * 真题: Facebook Microsoft Pocket
 * 链接：https://leetcode-cn.com/problems/sort-colors
 *
 * @author mao  2020/10/23 0:00
 */
public class Solution75 {
    public void sortColors(int[] nums) {
        int[] counts = {0, 0, 0};
        for (int i = 0; i < nums.length; i++) {
            assert (nums[i] >= 0 && (nums[i] <= 2));
            counts[nums[i]]++;
        }
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                nums[index++] = i;
            }
        }
    }

    @Test
    public void test2() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
