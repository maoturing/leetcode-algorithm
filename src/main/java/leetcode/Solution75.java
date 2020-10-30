package leetcode;

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
    /**
     * 思路: 统计数组中 0,1,2 元素的个数, 然后将其依次填充到数组中, 就完成了排序
     *
     * 时间: O(n)
     * 空间: O(1)
     */
    public void sortColors(int[] nums) {
        int[] counts = {0, 0, 0};
        for (int num : nums) {
            assert (num >= 0 && (num <= 2));
            counts[num]++;
        }
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                nums[index++] = i;
            }
        }
    }

    /**
     * 优化版
     * 思路: 类似于三路快排序, [0...zero...two...length-1],
     * 将数组分为三部分, 左部分元素都为0, [0...zero]; 中部分元素都为1, (zero...two); 右部分元素都为2, (two...length-1)
     * 1. 当元素等于1, 即处在(zero...two)中, 不需要维护不变量, i++处理下一个元素
     * 2. 当元素等于2, 将其交换到右部分, 即 two-1 的位置, 并将two--, 维护(two...length-1)都是2, 因为新元素并不确定, 需要再次循环判断, 所以没有i++
     * 3. 当元素等于0, 将其交换到左部分, 即 zero+1 的位置, 并将 zero++, 维护[0...zero]都为0, 交换过来的元素为1, 然后处理下一个元素 i++
     * 遍历到two位置, 三部分元素都已经归位
     *
     * 时间: O(n), 只需要遍历一遍数组, 上一种思路需要 4 次数组, 时间复杂度为 O(4n)
     * 空间: O(1)
     */
    public void sortColors2(int[] nums) {
        int zero = -1;               // nums[0...zero] 所有元素都为0
        int two = nums.length;       // nums[two...length-1] 所有元素都为2

        for (int i = 0; i < two;) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                two--;      // 维护循环不变量, [two...length-1] 中所有元素都为2
                // 将元素2交换到右部分, nums[i] 的数值不一定是1, 所以不能i++, 需要再循环处理一次
                int tmp = nums[two];
                nums[two] = nums[i];
                nums[i] = tmp;
            } else {     // nums[i]==0
                assert (nums[i] == 0);

                zero++;
                // 将元素0交换到左部分
                int tmp = nums[zero];
                nums[zero] = nums[i];
                nums[i] = tmp;
                i++;
            }
        }
    }

    @Test
    public void test2() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors2(nums);

        for (int num : nums) {
            System.out.println(num);
        }
    }
}
