package com.bingege.algorithm.topic.base.sort;

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
        for (int i = 1; i<array.length;i++) {
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
     * @description: 堆排序
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
            if(child + 1 <= n && arr[child] < arr[child + 1])
                child++;
            // 如果孩子节点小于或等于父节点，则下沉结束
            if (arr[child] <= temp ) break;
            // 父节点进行下沉
            arr[parent] = arr[child];
            parent = child;
            child = 2 * parent + 1;
        }
        arr[parent] = temp;
    }

    /**
     * @description: 归并排序
     * @author: xiaob
     * @date: 2022/9/19 09:56
     **/
    public static void mergeSort(int[] array) {


    }

    public static void quickSort(int[] array) {

    }



    public static void main(String[] args) {
        int[] val = new int[]{1,2,5,3,6,0,8,7,9,4};
        //1、冒泡排序
        //bubblingSort(val);
        //2、选择排序
        //chooseSort(val);
        //3、插入排序
        //insertSort(val);
        //4、插入进阶版 希尔排序
        //hillSort(val);
        //5、堆排序
        heapSort(val);
        //6、归并排序
        //7、快速排序
        //8、

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