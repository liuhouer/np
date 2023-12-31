package cn.northpark.LeetCode.快速排序;

/**
 * @author bruce
 * @date 2021年11月19日 17:19:59
 */
public class QuickSort {

    public void sortIntegers(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        quickSort(A, 0, A.length - 1);
    }

    private void quickSort(int[] A, int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start, right = end;
        // 1. pivot, A[start], A[end]
        // get value not index
        int pivot = A[(start + end) / 2];
        // 2. left <= right 不是 left < right
        while (left <= right) {
            // 3. A[left] < pivot not <=
            while (left <= right & A[left] < pivot) {
                left++;
            }
            while (left <= right & A[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                left++;
                right--;
            }
        }
        quickSort(A, start, right);
        quickSort(A, left, end);
    }


    public static void main(String[] args) {
        int[] nums = {9, 1, 4, 6, 7, 5, 2};

        new QuickSort().sortIntegers(nums);

        for (int i = 0; i < nums.length; i++) {
            System.err.println(nums[i]);
        }

    }

}
