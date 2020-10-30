package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * <p>
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * <p>
 * 说明：
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 * @author mao  2020/10/30 18:54
 */
public class Solution349 {
    /**
     * 使用内置函数 retainAll 求交集
     * 相比 intersection2 稍微多耗费了一点空间, 但是更加易读简洁, retainAll 就是求交集
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> record1 = new HashSet<>();
        for (Integer n : nums1) {
            record1.add(n);
        }

        Set<Integer> record2 = new HashSet<>();
        for (Integer n : nums2) {
            record2.add(n);
        }
        //  retainAll 求交集
        record1.retainAll(record2);

//        int[] result = new int[record1.size()];
//        int i = 0;
//        for (int integer : record1) {
//            result[i++] = integer;
//        }
//        return result;
        // 使用lambda 效率会明显降低
        return record1.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 当集合2元素在集合1中出现时, 说明为交集, 保存起来即可
     * 利用Set去重和 Set查询元素O(1)的特性
     *
     * 时间: O(n+m), 判断交集(是否包含)的时间复杂度为 O(1)
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> record1 = new HashSet<>();
        for (Integer n : nums1) {
            record1.add(n);
        }

        Set<Integer> resultSet = new HashSet<>();
        // 当nums2中元素在nums1中出现过, 即为交集, 保存到resultSet中, 顺便去重
        // .contains() 时间复杂度为O(1), 如果换做List时间复杂度就变为O(n)
        for (Integer n : nums2) {
            if (record1.contains(n)) {
                resultSet.add(n);
            }
        }
        // 为什么用了lambda耗时明显增加 ?
        // Integer::intValue 是拆箱,
        return resultSet.stream().mapToInt(Integer::intValue).toArray();
     }

    /**
     * 本身想对 2 进行优化, 但是效率并没有明显提升, 反而增加了理解难度
     * list 中保存交集, 为了避免重复出现的元素, 还需要将 recored1 中的元素移移除
     */
    public int[] intersection3(int[] nums1, int[] nums2) {
        Set<Integer> record1 = new HashSet<>();
        for (Integer n : nums1) {
            record1.add(n);
        }

        List<Integer> list = new ArrayList<>();
        // 当nums2中元素在nums1中出现过, 即为交集, 保存到resultSet中, 顺便去重
        // .contains() 时间复杂度为O(1), 如果换做List时间复杂度就变为O(n)
        for (Integer n : nums2) {
            if (record1.contains(n)) {
                record1.remove(n);
                list.add(n);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    public void test() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] result = intersection(nums1, nums2);
        Arrays.stream(result).forEach(System.out::println);
    }
}
