package com.bingege.algorithm.topic.base.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: xiaob
 * @CreateTime: 2022-09-18  20:36
 * @Description: 常用十种排序算法
 * @Version: 1.0
 */
public class SortClass {

    /**
     * 思想： 每次循环都比较前后两个元素的大小，如果前者大于后者，则将两者进行交换；
     * * 这样做会将每次循环中最大的元素替换到末尾，逐渐形成有序集合；
     * * 将每次循环中的最大元素逐渐由队首转移到队尾的过程形似“冒泡”过程，故因此得名。
     *
     * @description: 简单冒泡排序, 1、时间复杂度：O(n2) 2、空间复杂度：O(1) 3、稳定排序 4、原地排序
     * @author: xiaob
     * @date: 2022/9/18 20:37
     **/
    public static void bubblingSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int val = array[i];
                    array[i] = array[j];
                    array[j] = val;
                }
            }
        }
    }

    /**
     * 思想： 每次循环都会找出当前循环中最小的元素，然后和此次循环中的队首元素进行交换。
     *
     * @description: 选择排序, 1、时间复杂度：O(n2) 2、空间复杂度：O(1) 3、非稳定排序 4、原地排序
     * @author: xiaob
     * @date: 2022/9/18 20:37
     **/
    public static void chooseSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            if (min != i) {
                int val = array[i];
                array[i] = array[min];
                array[min] = val;
            }
        }
    }

    /**
     * 思想： 插入排序的精髓在于每次都会在先前排好序的子集合中插入下一个待排序的元素，
     * * 每次都会判断待排序元素的上一个元素是否大于待排序元素，如果大于，则将元素右移，然后判断再上一个元素与待排序元素...以此类推。
     * * 直到小于等于比较元素时就是找到了该元素的插入位置。这里的等于条件放在哪里很重要，因为它是决定插入排序稳定与否的关键。
     *
     * @description: 插入排序，1、时间复杂度：O(n2) 2、空间复杂度：O(1) 3、稳定排序 4、原地排序
     * @author: xiaob
     * @date: 2022/9/18 20:40
     **/
    public static void insertSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int index = i - 1;
            while (index >= 0 && array[index] > temp) {
                //已经排好顺序的子集合，进行位点偏移
                array[index + 1] = array[index];
                index--;
            }
            //把比较值替换到 排好序的位点上
            array[index + 1] = temp;
        }
    }

    /**
     * 思想： 希尔排序可以认为是插入排序的改进版本。
     * * 首先按照初始增量来将数组分成多个组，每个组内部使用插入排序。
     * * 然后缩小增量来重新分组，组内再次使用插入排序...重复以上步骤，直到增量变为1的时候，
     * * 这个时候整个数组就是一个分组，进行最后一次完整的插入排序即可结束。
     *
     * @description: 希尔排序，插入进阶版
     * @author: xiaob
     * @date: 2022/9/18 20:40
     **/
    public static void hillSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        //每次 /2，直到系数为1
        int gap = array.length >>> 1;
        while (gap > 0) {
            //实现上，和插入排序差异为 把 1改为了 gap
            for (int i = gap; i < array.length; i++) {
                int temp = array[i];
                int j = i - gap;
                while (j >= 0 && array[j] > temp) {
                    array[j + gap] = array[j];
                    j = j - gap;
                }
                array[j + gap] = temp;
            }
            // gap = gap / 2;
            gap >>>= 1;
        }
    }

    /**
     * 思路：堆排序的过程是首先构建一个大顶堆，大顶堆首先是一棵完全二叉树，其次它保证堆中某个节点的值总是不大于其父节点的值。
     * 因为大顶堆中的最大元素肯定是根节点，所以每次取出根节点即为当前大顶堆中的最大元素，取出后剩下的节点再重新构建大顶堆，再取出根节点，再重新构建…重复这个过程，直到数据都被取出，最后取出的结果即为排好序的结果。
     *
     * @description: 堆排序，1、时间复杂度：O(nlogn) 2、空间复杂度：O(1) 3、非稳定排序 4、原地排序
     * @author: xiaob
     * @date: 2022/9/19 09:55
     **/
    public static void heapSort(int[] array) {
        int n = array.length;
        //构建大顶堆
        for (int i = (n - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, n - 1);
        }
        //进行堆排序
        for (int i = n - 1; i >= 1; i--) {
            // 把堆顶元素与最后一个元素交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            // 把打乱的堆进行调整，恢复堆的特性
            downAdjust(array, 0, i - 1);
        }
    }

    //下沉操作
    public static void downAdjust(int[] arr, int parent, int n) {
        //临时保存要下沉的元素
        int temp = arr[parent];
        //定位左孩子节点的位置
        int child = 2 * parent + 1;
        //开始下沉
        while (child <= n) {
            // 如果右孩子节点比左孩子大，则定位到右孩子
            if (child + 1 <= n && arr[child] < arr[child + 1])
                child++;
            // 如果孩子节点小于或等于父节点，则下沉结束
            if (arr[child] <= temp) break;
            // 父节点进行下沉
            arr[parent] = arr[child];
            parent = child;
            child = 2 * parent + 1;
        }
        arr[parent] = temp;
    }

    /**
     * 思路：归并排序使用的是分治的思想，首先将数组不断拆分，直到最后拆分成两个元素的子数组，将这两个元素进行排序合并，再向上递归。
     * * 不断重复这个拆分和合并的递归过程，最后得到的就是排好序的结果。
     * 1、通过递归的方式将大的数组一直分割，直到数组的大小为 1，此时只有一个元素，那么该数组就是有序的了，
     * 2、之后再把两个数组大小为1的合并成一个大小为2的，再把两个大小为2的合并成4的 ..... 直到全部小的数组合并起来。
     *
     * @description: 归并排序，1、时间复杂度：O(nlogn) 2、空间复杂度：O(n) 3、稳定排序 4、非原地排序
     * @author: xiaob
     * @date: 2022/9/19 09:56
     **/
    public static void mergeSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            //这里没有选择“(left + right) / 2”的方式，是为了防止数据溢出
            int mid = left + ((right - left) >>> 1);
            // 拆分子数组
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            // 对子数组进行合并
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        // p1和p2为需要对比的两个数组的指针，k为存放temp数组的指针
        int p1 = left, p2 = mid + 1, k = 0;
        while (p1 <= mid && p2 <= right) {
            if (array[p1] <= array[p2]) {
                temp[k++] = array[p1++];
            } else {
                temp[k++] = array[p2++];
            }
        }
        // 把剩余的数组直接放到temp数组中
        while (p1 <= mid) {
            temp[k++] = array[p1++];
        }
        while (p2 <= right) {
            temp[k++] = array[p2++];
        }
        // 复制回原数组
        for (int i = 0; i < temp.length; i++) {
            array[i + left] = temp[i];
        }
    }

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
    public static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            //获取中轴元素所处的位置
            int mid = partition(arr, left, right);
            //进行分割
            arr = quickSort(arr, left, mid - 1);
            arr = quickSort(arr, mid + 1, right);
        }
        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
        //选取中轴元素
        int pivot = arr[left];
        int i = left + 1;
        int j = right;
        while (true) {
            // 向右找到第一个小于等于 pivot 的元素位置
            while (i <= j && arr[i] <= pivot) i++;
            // 向左找到第一个大于等于 pivot 的元素位置
            while(i <= j && arr[j] >= pivot ) j--;
            if(i >= j)
                break;
            //交换两个元素的位置，使得左边的元素不大于pivot,右边的不小于pivot
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        arr[left] = arr[j];
        // 使中轴元素处于有序的位置
        arr[j] = pivot;
        return j;
    }


    /**
     * 思路：计数排序会创建一个临时的数组，里面存放每个数出现的次数。
     * * 比如一个待排序的数组是[3, 3, 5, 2, 7, 4, 2]，
     * * 那么这个临时数组中记录的数据就是[2, 2, 1, 1, 0, 1]。
     * * 表示2出现了两次、3出现了两次、4出现了一次、5出现了一次、6出现了零次、7出现了一次。
     * * 那么最后只需要遍历这个临时数组中的计数值就可以了。
     *
     * * @description: 计数排序，最大最小值差异大，不适用
     * @author: xiaob
     * @date: 2022/9/19 17:44
     * @param: [array]
     * @return: void
     **/
    public static void countSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        //获取最大最小值
        int max = array[0];
        int min = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        //创建数组大小，注意当 最大最小值差异比较大时，数组会很大
        int[] temp = new int[max - min + 1];
        //记录每个数出现的次数
        for (int i : array) {
            temp[i - min]++;
        }
        int index = 0;
        for (int i = 0; i < temp.length; i++) {
            //当输出一个数之后，当前位置的计数就减一，直到减到0为止
            while (temp[i]-- > 0) {
                array[index++] = i + min;
            }
        }
    }

    /**
     * 思路：桶排序就是把最大值和最小值之间的数进行瓜分，
     * * 例如分成 10 个区间，10个区间对应10个桶，我们把各元素放到对应区间的桶中去，
     * * 再对每个桶中的数进行排序，可以采用归并排序，也可以采用快速排序之类的。
     *
     * * @description: 桶排序，分区间排序在合并；降低数组大小 (针对海量数据+区间小的情况)
     * @author: xiaob
     * @date: 2022/9/19 18:15
     **/
    public static void bucketSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        //记录待排序数组中的最大值
        int max = array[0];
        //记录待排序数组中的最小值
        int min = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        //计算桶的数量（可以自定义实现）
        int bucketNumber = (max - min) / array.length + 1;
        List<Integer>[] buckets = new ArrayList[bucketNumber];
        //计算每个桶存数的范围（可以自定义实现或者不用实现）
        int bucketRange = (max - min + 1) / bucketNumber;

        for (int value : array) {
            //计算应该放到哪个桶中（可以自定义实现）
            int bucketIndex = (value - min) / (bucketRange + 1);
            //延迟初始化
            if (buckets[bucketIndex] == null) {
                buckets[bucketIndex] = new ArrayList<Integer>();
            }
            //放入指定的桶
            buckets[bucketIndex].add(value);
        }
        int index = 0;
        for (List<Integer> bucket : buckets) {
            //对每个桶进行内部排序，我这里使用的是快速排序，也可以使用别的排序算法，当然也可以继续递归去做桶排序
            Collections.sort(bucket);
            if (bucket == null) {
                continue;
            }
            //将不为null的桶中的数据按顺序写回到array数组中
            for (Integer integer : bucket) {
                array[index++] = integer;
            }
        }
    }

    /**
     * 思路：基数排序不是根据一个数的整体来进行排序的，而是将数的每一位上的数字进行排序。
     * * 比如说第一轮排序，我拿到待排序数组中所有数个位上的数字来进行排序；
     * * 第二轮排序我拿到待排序数组中所有数十位上的数字来进行排序；
     * * 第三轮排序我拿到待排序数组中所有数百位上的数字来进行排序...以此类推。
     * * 每一轮的排序都会累加上一轮所有前几位上排序的结果，最终的结果就会是一个有序的数列。
     *
     * * @description: 基数排序，1、时间复杂度：O(kn) 2、空间复杂度：O(n+k) 3、稳定排序 4、非原地排序
     * @author: xiaob
     * @date: 2022/9/19 18:15
     **/
    public static void radixSort(int[] arr) {
        if(arr == null || arr.length < 2) return;

        int n = arr.length;
        int max = arr[0];
        // 找出最大值
        for (int i = 1; i < n; i++) {
            if(max < arr[i]) max = arr[i];
        }
        // 计算最大值是几位数
        int num = 1;
        while (max / 10 > 0) {
            num++;
            max = max / 10;
        }
        // 创建10个桶
        ArrayList<LinkedList<Integer>> bucketList = new ArrayList<LinkedList<Integer>>(10);
        //初始化桶
        for (int i = 0; i < 10; i++) {
            bucketList.add(new LinkedList<Integer>());
        }
        // 进行每一趟的排序，从个位数开始排
        for (int i = 1; i <= num; i++) {
            for (int j = 0; j < n; j++) {
                // 获取每个数最后第 i 位是数组
                int radio = (arr[j] / (int)Math.pow(10,i-1)) % 10;
                //放进对应的桶里
                bucketList.get(radio).add(arr[j]);
            }
            //合并放回原数组
            int k = 0;
            for (int j = 0; j < 10; j++) {
                for (Integer t : bucketList.get(j)) {
                    arr[k++] = t;
                }
                //取出来合并了之后把桶清光数据
                bucketList.get(j).clear();
            }
        }
    }

    public static void main(String[] args) {
        int[] val = new int[]{1, 2, 11, 5, 3, 6, 0, 8, 7, 9, 4};
        //七种比较排序，通过值比较的方式进行
        //1、冒泡排序
        //bubblingSort(val);
        //2、选择排序
        //chooseSort(val);
        //3、插入排序
        //insertSort(val);
        //4、插入进阶版 希尔排序
        //hillSort(val);
        //5、堆排序
        //heapSort(val);
        //6、归并排序
        //mergeSort(val);
        //7、快速排序
        //quickSort(val);

        //非比较排序
        //8、计数排序
        //countSort(val);
        //9、桶排序
        //bucketSort(val);
        //10、基数排序
        radixSort(val);
        print(val);
    }

    private static void print(int[] val) {
        String result = "";
        for (int v : val) {
            result = result + v + ",";
        }
        System.out.println(result);
    }
}