package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * <p>
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * <p>
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 * <p>
 * https://leetcode-cn.com/problems/zigzag-conversion/
 *
 * @author mao  2021/6/4 15:04
 */
public class Solution6 {

    /**
     * 读题:
     * 1. 字符串s长度最大为1000
     * 2. 行数numRows范围[1,1000]
     * 3. 字符串s由大小写英文字母, 逗号, 句号 组成
     * <p>
     * 思路:
     * 1. 当行数numRows为1, 原样输出
     * 2. 当行数numRows为2, 间隔着输出, 如 HELLO, 输出 HLOEL
     * H L O
     * E L
     * 3. 当行数numRows为3时, 斜线上的字符, 只加在对应行, 非斜线字符每行都加. 见 {@link Solution6#convertRows3(java.lang.String)}
     * 4. 当行数numRows为n时, 与行数numRows为3时类似, 需要修改判断当前字符是否为Z字上顶点
     * 字符串为ABCDEFGHIJKLMNOP, numRows为4
     * A     G     M
     * B   F H   L N
     * C E   I K   O
     * D     J     P
     *
     * @see Solution6#convertRows3(java.lang.String)
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        char[] chars = s.toCharArray();
        // 初始化lines, 行数与numRows相同
        StringBuilder[] lines = new StringBuilder[numRows];

        for (int i = 0; i < lines.length; i++) {
            lines[i] = new StringBuilder();
        }

        // 处理 numRows为n的情况
        // 字符串为ABCDEFGHIJKLMNOP, numRows为4
        //        A     G     M
        //        B   F H   L N
        //        C E   I K   O
        //        D     J     P
        for (int i = 0; i < chars.length; ) {
            // Z字的斜线         // 4E  10K           // 5F,  11L
            // Z字的顶点         // 0A,  6G,  12M     // 3D,  9J,  15P
            // Z字的上顶点       i%6 ==0          i%(2*numRows-2) ==0
            if (i % (2 * numRows - 2) == 0) {
                // 当前字符chars[i]为Z字形的上顶点, 将当前列的字符添加到对应行
                for (StringBuilder line : lines) {
                    // 处理数组越界
//                    String c = i++ < chars.length ? String.valueOf(chars[i]) : "";
//                    line.append(c);
                    if (i < chars.length) {
                        line.append(chars[i++]);
                    } else {
                        i++;
                    }
                }
            } else {
                // 当前字符为Z字的斜线, 将斜线上的字符, 依次添加到对应行
                // 斜线上的字符, 肯定不在首尾行
                for (int n = lines.length - 2; n > 0; n--) {
                    // 处理数组越界
                    if (i < chars.length) {
                        lines[n].append(chars[i++]);
                    } else {
                        i++;
                    }
                }
            }
        }

        // 合并每行的字符
        StringBuilder result = new StringBuilder();
        for (StringBuilder line : lines) {
            System.out.println(line);
            result.append(line);
        }
        return result.toString();
    }

    /**
     * 优化版
     * 思路:
     * 1.设 numRows 行字符串分别为 line1, line2...lineN
     * 2.按照顺序遍历字符串 s, 每个字符 c 在Z字形对应的行从 1 增大到 numRows, 再减小到 1, 如此反复
     * 3.将每个字符c, 填充到对应的 line,
     * <p>
     * 总结: 思路和规律比代码重要1W倍
     * 参考: https://leetcode-cn.com/problems/zigzag-conversion/solution/zzi-xing-bian-huan-by-jyd/
     */
    public String convertAdvanced(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> lines = new ArrayList<>(numRows);
        // 初始化 lines
        for (int i = 0; i < numRows; i++) {
            lines.add(new StringBuilder());
        }

        int n = 0;      // lines的行数
        int flag = -1;  // 遍历的方向
        // 字符串为ABCDEFGHIJKLMNOP, numRows为4
        //        A     G     M
        //        B   F H   L N
        //        C E   I K   O
        //        D     J     P
        // 遍历字符串s中所有字符, 将字符c添加到对应的行
        for (char c : s.toCharArray()) {
            lines.get(n).append(c);
            // 当到达Z字顶点时, 说明需要反向了
            // 3D, n==3, n == numRows -1 时需要转向
            // 0G, n==1, n == 0 时需要转向
            if (n == 0 || n == numRows - 1) {
                flag = -flag;
            }
            // flag为-1, 表示遍历Z字的斜线, 向上
            // flag为1,  表示遍历Z字的直线, 向下
            n += flag;
        }

        // 合并 lines
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : lines) {
            result.append(row);
        }
        return result.toString();
    }

    /**
     * 处理numRows为2的情况
     */
    private String convertRows2(int numRows, char[] chars) {
        if (numRows == 2) {
            StringBuilder line1 = new StringBuilder();
            StringBuilder line2 = new StringBuilder();

            for (int i = 0; i < chars.length; ) {
                line1.append(i);
                line2.append(i + 1);

                i = i + 2;
            }
            return line1.append(line2).toString();
        }
        return null;
    }

    /**
     * 只处理numRows=3的情况
     * <p>
     * 字符串为ABCDEFGHIJKLMNO, numRows=3
     * A   E   I   M
     * B D F H J L N
     * C   G   K   O
     * <p>
     * 思路:
     * 1. numRows为3, 所以有3行line
     * 2. Z字形斜线部分的字符, 需要添加到第2行line2, 使用(i+1)%4==0 判断是否为斜线部分
     * 斜线部分的字符 3D, 7H, 11L, 特点是 (i+1)%4==0
     * 3. Z字形非斜线部分的字符, 需要依次添加到对应的行  // 0A,  1B,  2C
     */
    private String convertRows3(String s) {
        char[] chars = s.toCharArray();

        // 先考虑numRows为3的情况
        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        StringBuilder line3 = new StringBuilder();
        // 字符串为ABCDEFGHIJKLMNO, numRows=3
        //        A   E   I   M
        //        B D F H J L N
        //        C   G   K   O
        for (int i = 0; i < chars.length; ) {
            // Z字形斜线部分的字符, 添加到第2行line2
            if ((i + 1) % 4 == 0) {
                line2.append(chars[i++]);      // 3, D     // 7, H     // 11, L
            } else {
                // Z字形非斜线部分的字符, 添加到对应的行
                line1.append(chars[i++]);      // 0, A
                line2.append(chars[i++]);      // 1, B
                line3.append(chars[i++]);      // 2, C
            }
        }
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        return line1.append(line2).append(line3).toString();
    }


    @Test
    public void testRows1() {
        String result = convert("ABCD", 2);
        Assert.assertEquals(result, "ACBD");
        System.out.println(result);
    }

    /**
     * 测试3行的情况
     */
    @Test
    public void testRows3() {
        String result = convertRows3("ABCDEFGHIJKLMNO");
        Assert.assertEquals(result, "AEIMBDFHJLNCGKO");
        System.out.println(result);
    }

    /**
     * 测试4行的情况
     */
    @Test
    public void testRows4() {
        String result = convert("ABCDEFGHIJKLMNOP", 4);
        Assert.assertEquals(result, "AGMBFHLNCEIKODJP");
        System.out.println(result);
    }

    /**
     * 测试数组越界的情况
     */
    @Test
    public void testRows4_2() {
        String result = convert("ABCDEFGHIJKLMNO", 4);
        Assert.assertEquals(result, "AGMBFHLNCEIKODJ");
        System.out.println(result);
    }

    @Test
    public void testConvert() {
        String result = convert("PAYPALISHIRING", 3);
        Assert.assertEquals(result, "PAHNAPLSIIGYIR");
        System.out.println(result);
    }

    /**
     * 测试优化版
     */
    @Test
    public void testConvertAdvanced() {
        String result = convertAdvanced("PAYPALISHIRING", 3);
        Assert.assertEquals(result, "PAHNAPLSIIGYIR");
        System.out.println(result);
    }
}
