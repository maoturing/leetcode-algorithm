package leetcode;

import java.util.*;

/**
 * 350. 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 * <p>
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * @author mao  2020/10/30 20:23
 */
public class Solution350 {

    /**
     * 与 349 类似
     * 关键点在于示例1中重复出现的元素也需要重复输出
     * <p>
     * 利用 map 的特性, key 记录元素, value 记录次数, 当 nums2 中的元素在 nums1 中出现了, 则为交集元素,
     * 并将该元素在 nums1 中的次数 -1
     * <p>
     * 时间: O(m+n)  两个数组都要遍历一次
     * 空间：O(m) 或 O(n), 只有一个数组需要额外的map来保存
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> recored1 = new HashMap<>();
        for (int n : nums1) {
            if (recored1.containsKey(n)) {
                recored1.put(n, recored1.get(n) + 1);
            } else {
                recored1.put(n, 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int n : nums2) {
            if (recored1.get(n) != null && recored1.get(n) > 0) {
                recored1.put(n, recored1.get(n) - 1);
                list.add(n);
            }
        }
        int[] result = new int[list.size()];
        int i = 0;
        for (Integer n : list) {
            result[i++] = n;
        }
        return result;
    }

    /**
     * 优化版: 对intersect()进行优化
     * 1. 如题目描述, 如果 nums1 远远大于 nums2 时, 应该将 nums2 保存到Map中
     * 2. 简化判断元素是否为交集的操作
     * <p>
     * 时间: O(m+n)  两个数组都要遍历一次
     * 空间：O(min(m,n))
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        // 较短的数组才存入 Map, 节省空间, 也节省contains, put 等操作的时间
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        Map<Integer, Integer> recored1 = new HashMap<>(nums1.length);
        for (int n : nums1) {
//            if (recored1.containsKey(n)) {
//                recored1.put(n, recored1.get(n) + 1);
//            } else {
//                recored1.put(n, 1);
//            }
            // 简化key不存在时视value为0的操作
            int count = recored1.getOrDefault(n, 0) + 1;
            recored1.put(n, count);
        }

        List<Integer> list = new ArrayList<>();
        for (int n : nums2) {
            // 避免 intersect() 重复 get() 操作
            Integer count = recored1.get(n);
            if (count != null && count > 0) {
                recored1.put(n, count - 1);
                list.add(n);
            }
        }

        // 处理返回值
        int[] result = new int[list.size()];
        int i = 0;
        for (Integer n : list) {
            result[i++] = n;
        }
        return result;
    }

    /**
     * 先排序, 快慢指针
     * <p>
     * 适用于数据量较小的情况, 先排序反而会快一些
     * <p>
     * 时间：O(mlogm+nlogn)，其中 m 和 n 分别是两个数组的长度。对两个数组进行排序的时间复杂度是 O(mlogm+nlogn)，
     * 遍历两个数组的时间复杂度是 O(m+n)，因此总时间复杂度是 O(mlogm+nlogn)。
     * <p>
     * 空间: O(1)
     */
    public int[] intersect3(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, idx = 0;      // [0...idx)中为交集元素
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {        // nums1[i] == nums2[j]
                // 元素相等说明是交集, 将该元素保存到 [0...idx) 中
                nums1[idx++] = nums2[j++];
                i++;
            }
        }
        return Arrays.copyOf(nums1, idx);
    }

    /**
     * 错误版
     * [1,2,2,1]， [2] 的输出结果为[2,2]， 预期为[2]
     */
    public int[] intersect4(int[] nums1, int[] nums2) {
        List<Integer> record1 = new ArrayList<>();
        for (Integer n : nums1) {
            record1.add(n);
        }
        List<Integer> record2 = new ArrayList<>();
        for (Integer n : nums2) {
            record2.add(n);
        }

        record1.retainAll(record2);

        int[] result = new int[record1.size()];
        int i = 0;
        for (int n : record1) {
            result[i++] = n;
        }
        return result;
    }
}
