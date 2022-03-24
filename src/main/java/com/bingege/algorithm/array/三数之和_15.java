package com.bingege.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 *
 * @author xiaob
 * @date 2022-03-24
 */
public class 三数之和_15 {

    /**
     * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * <p>
     * 0 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     */
    public static void main(String[] args) {

    }

    /**
     * 算法流程：
     * 1、特判，对于数组长度 n，如果数组为 null 或者数组长度小于 3，返回 []。
     * 2、对数组进行排序。
     * 3、遍历排序后数组：
     *      若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 0，直接返回结果。
     *      对于重复元素：跳过，避免出现重复解
     *      令左指针 L=i+1，右指针 R=n−1，当 L<R 时，执行循环：
     *      当 nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,R 移到下一位置，寻找新的解
     *          若和大于 0，说明 nums[R] 太大，R 左移
     *          若和小于 0，说明 nums[L] 太小，L 右移
     *
     * 复杂度分析
     * 时间复杂度：O(n^2) 数组排序 O(NlogN)，
     * 遍历数组 O(n)，
     * 双指针遍历 O(n)，
     * 总体 O(NlogN)+O(n)∗O(n)，O(n2)
     * 空间复杂度：O(1)
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if (nums == null || len < 3) {
            return ans;
        }
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去重
            }
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++; // 去重
                    }
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--; // 去重
                    }
                    L++;
                    R--;
                } else if (sum < 0) {
                    L++;
                } else if (sum > 0) {
                    R--;
                }
            }
        }
        return ans;
    }
}
