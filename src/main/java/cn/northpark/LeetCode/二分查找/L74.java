package cn.northpark.LeetCode.二分查找;

/**
 * @author liuhouer
 * @date 2021年05月06日 17:13:38
 * 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 *
 */
public class L74 {

    public boolean searchMatrix(int[][] matrix, int target) {

        int minHeight = 0;
        int maxHeight = matrix.length-1;

        //高度二分
        while (minHeight<maxHeight){
            int midHeight = minHeight +(maxHeight-minHeight)/2;
            if(matrix[midHeight][matrix[midHeight].length-1]<target){
                minHeight = midHeight + 1;
            }else if(matrix[midHeight][0]>target){
                maxHeight = midHeight ;
            }else if(matrix[midHeight][0]==target){
                return true;
            }else{
                minHeight = midHeight;
                break;
            }

        }

        //左右二分
        int left = 0;
        int right = matrix[minHeight].length-1;
        while (left < right){
            int mid = left +(right-left)/2;
            if(matrix[minHeight][mid]<target){
                left = mid +1;
            }else if(matrix[minHeight][mid]> target){
                right = mid ;
            }else{
                return true;
            }
        }



        return  matrix[minHeight][left] == target;
    }

    public static void main(String[] args) {
        //[[1,3,5,7],[10,11,16,20],[23,30,34,60]]
        //13
        System.err.println(new L74().searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}},11));
    }
}
