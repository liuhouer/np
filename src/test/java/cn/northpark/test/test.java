package cn.northpark.test;

import cn.northpark.utils.EmailUtils;

/**
 * @author zhangyang
 * @date 2021年04月21日 18:59:46
 */
public class test {


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
    	
        EmailUtils.getInstance().resFeedBack(String.valueOf("{\"spanID\":\"706015\",\"href\":\"https://northpark.cn/movies/post-706015.html\",\"title\":\"墨家机关术【国产】【2021】\"}"));
    
    }
}
