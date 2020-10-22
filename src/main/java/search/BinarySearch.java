package search;

import org.junit.Assert;
import utils.MyUtil;

/**
 * 如何写出正确的程序?
 * 1. 明确变量 l, r 的含义,
 * 2. 在循环中不断改变变量, 但要保持其含义不变, 即改变 l=mid+1, 仍在[l....r]中查找target
 * 3. 使用小数据集测试
 * 4. 使用大数据集测试
 * @author mao  2020/10/22 0:07
 */
public class BinarySearch {

    /**
     * 二分查找的前提是有序数组
     *
     * @param arr
     * @param length
     * @param target
     * @return
     */
    public static int BinarySearch(int[] arr, int length, int target) {
        int l = 0, r = length - 1;      // 在集合[l...r]中查找target, 左闭右闭

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
     * 修改边界，算法的难点在于边界处理，要维持[l...r)的意义
     * @param arr
     * @param length
     * @param target
     * @return
     */
    public static int BinarySearch2(int[] arr, int length, int target) {
        int l = 0, r = length;      // 在集合[l...r)中查找target, 左闭右闭

        while (l < r) {        // 在[l...r)的范围里寻找target, 当l==r时这个区间没有元素
            int mid = (l + r) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                l = mid + 1;        // 在[mid+1....r)中继续查找
            }
            if (arr[mid] > target) {
                r = mid;        // 在[l...mid)中继续查找
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 1000000;
        int[] data = MyUtil.generatedOrderedArray(n);
        long start = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            Assert.assertEquals(i, BinarySearch.BinarySearch(data, n, i));
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(n + "个有序元素的二分查找花费时间：" + time);
    }
}
