package utils;

/**
 * @author mao  2020/10/22 0:22
 */
public class MyUtil {
    /**
     * 生成有序数组
     * @param n
     * @return
     */
    public static int[] generatedOrderedArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }
}
