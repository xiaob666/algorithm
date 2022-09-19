package com.bingege.algorithm.topic.base.sort;

/**
 * @Author: xiaob
 * @CreateTime: 2022-09-19  10:32
 * @Description: 堆排序，最大堆
 * @Version: 1.0
 */
public class HeapMaxSort {

    /**
     * 思路：堆排序的过程是首先构建一个大顶堆，大顶堆首先是一棵完全二叉树，其次它保证堆中某个节点的值总是不大于其父节点的值。
     * 因为大顶堆中的最大元素肯定是根节点，所以每次取出根节点即为当前大顶堆中的最大元素，取出后剩下的节点再重新构建大顶堆，再取出根节点，再重新构建…重复这个过程，直到数据都被取出，最后取出的结果即为排好序的结果。
     *
     * @description: 堆排序
     * @author: xiaob
     * @date: 2022/9/19 09:55
     **/
    public static int[] heapSort(int[] array) {
        MaxHeap maxHeap = new MaxHeap();
        return maxHeap.heapSort(array);
    }
}

class MaxHeap {

    /**
     * 排序数组
     */
    private int[] nodeArray;
    /**
     * 数组的真实大小
     */
    private int size;

    private int parent(int index) {
        return (index - 1) >>> 1;
    }

    private int leftChild(int index) {
        return (index << 1) + 1;
    }

    private int rightChild(int index) {
        return (index << 1) + 2;
    }

    private void swap(int i, int j) {
        nodeArray[i] = nodeArray[i] ^ nodeArray[j];
        nodeArray[j] = nodeArray[i] ^ nodeArray[j];
        nodeArray[i] = nodeArray[i] ^ nodeArray[j];
    }

    private void siftUp(int index) {
        //如果index处节点的值大于其父节点的值，则交换两个节点值，同时将index指向其父节点，继续向上循环判断
        while (index > 0 && nodeArray[index] > nodeArray[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void siftDown(int index) {
        //左孩子的索引比size小，意味着索引index处的节点有左孩子，证明此时index节点不是叶子节点
        while (leftChild(index) < size) {
            //maxIndex记录的是index节点左右孩子中最大值的索引
            int maxIndex = leftChild(index);
            //右孩子的索引小于size意味着index节点含有右孩子
            if (rightChild(index) < size && nodeArray[rightChild(index)] > nodeArray[maxIndex]) {
                maxIndex = rightChild(index);
            }
            //如果index节点值比左右孩子值都大，则终止循环
            if (nodeArray[index] >= nodeArray[maxIndex]) {
                break;
            }
            //否则进行交换，将index指向其交换的左孩子或右孩子，继续向下循环，直到叶子节点
            swap(index, maxIndex);
            index = maxIndex;
        }
    }

    private void add(int value) {
        nodeArray[size++] = value;
        //构建大顶堆
        siftUp(size - 1);
    }

    private void extractMax() {
        /*
        将堆顶元素和最后一个元素进行交换
        此时并没有删除元素，而只是将size-1，剩下的元素重新构建成大顶堆
         */
        swap(0, --size);
        //重新构建大顶堆
        siftDown(0);
    }

    public int[] heapSort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }

        nodeArray = new int[array.length];
        for (int value : array) {
            add(value);
        }
        for (int ignored : array) {
            extractMax();
        }
        return nodeArray;
    }
}