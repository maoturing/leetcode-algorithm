package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 *  你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *  示例:
 *  给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 *  Related Topics 数组 哈希表
 *
 *  https://leetcode-cn.com/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-solution/
 * @author mao  2020/10/21 1:29
 */

class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        return fun3(nums, target);
    }

    /**
     * 1. 暴力破解法
     * 时间: O(N^2)
     * 对于每个元素，我们试图通过遍历数组的其余部分来寻找它所对应的目标元素，这将耗费 O(n) 的时间。因此时间复杂度为O(n^2)。
     * 空间: O(1)
     *
     * 外层循环从数组中取出下标为i的元素num[i]，内层循环取出i之后的元素nums[j] 一一与下标为i的元素进行相加操作，判断结果是否为target。
     */
    private int[] fun1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 2. 两边hash表
     *
     * 时间：O(n)
     * 我们把包含有n个元素的列表遍历两次。由于哈希表将查找时间缩短到O(1)，所以时间复杂度为O(2n)*O(1)。
     *
     * 空间：O(n)
     * 所需的额外空间取决于哈希表中存储的元素数量，该表中存储了n个元素。
     *
     * 通过以空间换取时间的方式，我们可以将查找时间从O(n)降低到O(1)。哈希表正是为此目的而构建的，它支持以 近似 恒定的时间进行快速查找。我用“近似”来描述，是因为一旦出现冲突，查找用时可能会退化到 O(n)。但只要你仔细地挑选哈希函数，在哈希表中进行查找的用时应当被摊销为O(1)。
     * 一个简单的实现使用了两次迭代。在第一次迭代中，我们将每个元素的值和它的索引添加到表中。然后，在第二次迭代中，我们将检查每个元素所对应的目标元素（target - nums[i]）是否存在于表中。注意，该目标元素不能是 nums[i]本身！
     *
     * 注意: 无法通过nums=[2,2,3], target=4的测试
     */
    private int[] fun2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if (map.containsKey(num) && map.get(num) != i) {
                return new int[] { i, map.get(num) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 3. 一遍hash表
     * 时间：O(n)
     * 遍历了包含有n个元素的列表一次。在表中进行的每次查找只花费O(1)的时间。相比方法2减少了一次遍历
     *
     * 空间：O(n)
     * 所需的额外空间取决于哈希表中存储的元素数量，该表最多需要存储n个元素。
     *
     * 进行迭代并将元素插入到表中的同时，我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素。
     * 如果它存在，那我们已经找到了对应解，并立即将其返回。
     */
    private int[] fun3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if (map.containsKey(num)) {
                return new int[] { map.get(num), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
