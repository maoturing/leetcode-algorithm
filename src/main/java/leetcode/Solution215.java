package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 数组中第 K 个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 真题: Facebook Microsoft Amazon Apple
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 *
 * @author mao  2020/10/23 2:42
 */
public class Solution215 {

    /**
     * 利用快排思想优化
     * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/javadai-ma-de-2chong-da-an-by-sdwwld/
      */
    public int findKthLargest2(int[] nums, int k) {
        return -1;
    }
    /**
     * 优化版
     * 利用优先队列解决 Top K问题
     * 优先队列使用小顶堆, 当元素大于堆中最小值时, 堆顶元素出队, 该元素入队
     * 遍历结束后, 堆中就存储了 k 个最大元素, 堆顶元素就为第 k 个最大元素
     *
     * 时间复杂度：O(nlogn)，建堆的时间代价是 O(n)，删除的总代价是 O(k logn)，因为 k < n，故渐进时间复杂为 O(n+k logn) = O(nlogn)。
     * 空间复杂度：O(logn)，即递归使用栈空间的空间代价。
     *
     */
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((v1, v2) -> v1 - v2);            // 小顶堆
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                priorityQueue.add(nums[i]);
            } else if (nums[i] > priorityQueue.peek()) {
                // 如果比小顶堆最小值大, 则移除堆顶最小值, 加入当前元素
                priorityQueue.remove();
                priorityQueue.add(nums[i]);
            }
        }

        // 堆中有k个元素, 从小到大排列
        return priorityQueue.peek();
    }

    /**
     * 利用优先队列解决 Top K问题
     * 优先队列使用大顶堆, 把所有元素入堆, 既浪费空间, 又浪费时间
     */
    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num : nums) {
            priorityQueue.add(num);
        }
        Integer result = -1;
        for (int i = 0; i < k; i++) {
            result = priorityQueue.poll();
        }
        return result;
    }

    /**
     * 傻瓜版: 先排序, 后查找
     */
    public int findKthLargest3(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    @Test
    public void test() {
        int[] nums = {3,2,1,5,6,4};
        int topk = findKthLargest(nums, 2);
        Assert.assertEquals(5, topk);
        System.out.println(topk);
    }
    @Test
    public void test2() {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int topk = findKthLargest(nums, 4);
        Assert.assertEquals(4, topk);
        System.out.println(topk);
    }
}
