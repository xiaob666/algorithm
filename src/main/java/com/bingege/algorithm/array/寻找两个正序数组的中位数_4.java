package com.bingege.algorithm.array;

/**
 * @author xiaob
 * @date 2022-03-22
 */
public class 寻找两个正序数组的中位数_4 {

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * <p>
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     * <p>
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     * <p>
     * 提示：
     * <p>
     * nums1.length == m
     * nums2.length == n
     * 0 <= m <= 1000
     * 0 <= n <= 1000
     * 1 <= m + n <= 2000
     * -106 <= nums1[i], nums2[i] <= 106
     */
    public static void main(String[] args) {

    }

    /**
     * 二分查找
     * 找到第 k = (m+n) / 2 的数，每次从数组中查找 k/2 个数
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    /**
     * 思路：二分查找
     * O(log(min(m,n)))
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        //确保nums1 数组长度大于 nums2
        int m = nums1.length;
        int n = nums2.length;

        if (m < n) {
            findMedianSortedArrays(nums2, nums1);
        }
        int max = m;
        int min = 0;

        while (min <= max) {
            //找到中位数的下标，每次
            //长数组的中位数下标
            int i = (min + max) / 2;
            //短数组的中位数下标
            int j = (m + n + 1) / 2 - i;
            // i 需要增大
            if (j != 0 && i != m && nums2[j - 1] > nums1[i]) {
                min = i + 1;
            }
            // i 需要减小
            else if (i != 0 && j != n && nums1[i - 1] > nums2[j]) {
                max = i - 1;
            } else { // 达到要求，并且将边界条件列出来单独考虑
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                // 奇数的话不需要考虑右半部分
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }

                //如果是偶数的话返回结果
                return (maxLeft + minRight) / 2.0;
            }
        }

        return 0.0;
    }
}
