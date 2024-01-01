package cn.northpark.test;

import cn.northpark.utils.TimeUtils;

/**
 * @author zhangyang
 * @date 2021年04月21日 18:59:46
 */
public class test2 {


//    1345

//    2

        public int searchInsert(int[] nums, int target) {

            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if(target<=num){
                    return i;
                }
            }
            return nums.length-1;


        }

    public static void main(String[] args) {

        String date = "3周前 (09-03)";
        String dateCut = date.substring(date.indexOf("(") + 1, date.indexOf(")"));
        date = TimeUtils.getYear("2022") + "-" + dateCut;

        System.err.println(date);

    }
}
