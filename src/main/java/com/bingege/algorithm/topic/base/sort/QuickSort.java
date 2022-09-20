package com.bingege.algorithm.topic.base.sort;

/**
 * @Author: xiaob
 * @CreateTime: 2022-09-19  17:23
 * @Description: 快速排序，优化版本
 * @Version: 1.0
 */
public class QuickSort {

    /**
     * 思路：
     * 1、我们从数组中选择一个元素，我们把这个元素称之为中轴元素
     * 2、把数组中所有小于中轴元素的元素放在其左边，所有大于或等于中轴元素的元素放在其右边，显然，此时中轴元素所处的位置的是有序的。也就是说，我们无需再移动中轴元素的位置。
     * 3、从中轴元素那里开始把大的数组切割成两个小的数组(两个数组都不包含中轴元素)，接着我们通过递归的方式，让中轴元素左边的数组和右边的数组也重复同样的操作，直到数组的大小为1，此时每个元素都处于有序的位置。
     *
     * @description: 快速排序，1、时间复杂度：O(nlogn) 2、空间复杂度：O(logn) 3、非稳定排序 4、原地排序
     * @author: xiaob
     * @date: 2022/9/19 16:56
     **/
    public static void quickSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    //进阶版本，增加中轴元素选取逻辑
    private static int[] quickSort(int[] array, int start, int end) {
        // left和right指针分别指向array的第一个和最后一个元素
        int left = start, right = end;

        //取最左边、最右边和最中间的三个元素的中间值作为中轴元素，以此来尽量避免每次都取第一个值作为基准数据、时间复杂度可能退化为O(n^2)的情况出现
        int middleOf3Indexs = middleOf3Indexs(array, start, end);
        if (middleOf3Indexs != start) {
            swap(array, middleOf3Indexs, start);
        }

        // temp存放的是array中需要比较的基准数据
        int temp = array[start];

        while (left < right) {
            // 首先从right指针开始比较，如果right指针位置处数据大于temp，则将right指针左移
            while (left < right && array[right] >= temp) {
                right--;
            }
            // 如果找到一个right指针位置处数据小于temp，则将right指针处数据赋给left指针处
            if (left < right) {
                array[left++] = array[right];
            }
            // 然后从left指针开始比较，如果left指针位置处数据小于temp，则将left指针右移
            while (left < right && array[left] <= temp) {
                left++;
            }
            // 如果找到一个left指针位置处数据大于temp，则将left指针处数据赋给right指针处
            if (left < right) {
                array[right--] = array[left];
            }
        }
        // 当left和right指针相等时，此时循环跳出，将之前存放的基准数据赋给当前两个指针共同指向的数据处
        array[left] = temp;
        // 一次替换后，递归交换基准数据左边的数据
        if (start < left - 1) {
            array = quickSort(array, start, left - 1);
        }
        // 之后递归交换基准数据右边的数据
        if (right + 1 < end) {
            array = quickSort(array, right + 1, end);
        }
        return array;
    }

    //获取基准参数，确保每次选择不为第一个固定的值（最糟糕情况下为 n^2）；基于随机概率 时间复杂度退化为 nlogn，
    private static int middleOf3Indexs(int[] array, int start, int end) {
        int mid = start + ((end - start) >>> 1);
        if (array[start] < array[mid]) {
            if (array[mid] < array[end]) {
                return mid;
            } else {
                return array[start] < array[end] ? end : start;
            }
        } else {
            if (array[mid] > array[end]) {
                return mid;
            } else {
                return array[start] < array[end] ? start : end;
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
