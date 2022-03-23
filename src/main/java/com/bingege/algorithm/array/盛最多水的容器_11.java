package com.bingege.algorithm.array;

/**
 * @author xiaob
 * @date 2022-03-23
 */
public class 盛最多水的容器_11 {

    /**
     * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
     * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * 说明：你不能倾斜容器。
     * 示例 1：
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
     * 提示：
     * n == height.length
     * 2 <= n <= 105
     * 0 <= height[i] <= 104
     */
    public static void main(String[] args) {

    }

    /**
     * 思路，面积 = 底 * min(h1,h2),底是两条线间距
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        //起始位
        int l = 0, r = height.length - 1;
        //记录最大面积
        int ans = 0;
        while (l < r) {
            //计算面积
            int area = Math.min(height[l], height[r]) * (r - l);
            //比较最大面积
            ans = Math.max(ans, area);
            //判断哪条线更短，进行位移操作
            if (height[l] <= height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }
}
