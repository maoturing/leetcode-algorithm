package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *  
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * <p>
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 *
 * @author mao  2020/10/23 5:10
 */
public class Solution11 {
    /**
     * 双指针法, 此题的难点不在于思路, 而在于读懂题, 关键点在于面积计算方式和指针移动条件
     * 面积的计算方式:  Math.min(height[l], height[r]) * (r - l)
     * <p>
     * 假设当前左指针和右指针指向的数分别为 x 和 y，不失一般性，我们假设 x≤y。
     * 同时，两个指针之间的距离为 t。那么，它们组成的容器的容量为：min(x,y)*t = x*t
     * 如果我们保持左指针的位置不变，那么无论如何左移右指针，这个容器的容量都不会超过 x*t 了
     * 因为左移右指针, t 在减小, 而 y 即使增大也没有用, 因为面积的最大值取决于 min(x,y), 而x<y取x
     * <p>
     * 时间：O(N)，双指针总计最多遍历整个数组一次。
     * 空间：O(1)，只需要额外的常数级别的空间。
     * <p>
     * 链接：https://leetcode-cn.com/problems/container-with-most-water/solution/sheng-zui-duo-shui-de-rong-qi-by-leetcode-solution/
     */
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;          // (r-l) * height[i]
        int maxArea = 0;

        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            maxArea = Math.max(maxArea, area);

            if (height[l] < height[r]) {
                l++;
            } else if (height[l] > height[r]) {
                r--;
            } else {     // height[l] = height[r]
                // 当二者相等,height[l] = height[r] = h 无论如何左移右指针，这个容器的容量都不会超过 h * t 了
                // 同样, 无论如何右移左指针, 这个容器的容量都不会超过 h * t 了, 所以同时移动两个指针
                l++;
                r--;
            }
        }
        return maxArea;
    }

    @Test
    public void test() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxArea = maxArea(height);
        System.out.println(maxArea);
        Assert.assertEquals(49, maxArea);
    }
}

