package cn.northpark.LeetCode.树的遍历;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhangyang
 * @date 2021年07月07日
 *
 * 1111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *   3
 *  / \
 * 9  20
 *   /  \
 *  15   7
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 *
 *
 * 输出：2
 *
 *
 * 示例 2：
 *
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *
 *
 * 提示：
 *
 *
 *
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 *
 */
public class Leet111 {



    static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }
   }

    /**
     * 递归实现 -难以理解 -按照条件递归查找最小的叶子节点
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {

        if(root==null){
            return 0;
        }

        if(root.right==null && root.left==null){
            return 1;
        }

        int left  = -1;
        if(root.left!=null){

            left = 1 + minDepth(root.left);

        }
        int right = -1;
        if(root.right!=null){

            right = 1 + minDepth(root.right);
        }

        return (left == -1 || right== -1) ? (right== -1 ? left:right):Math.min(left,right) ;

    }




    /**
     * 【bfs】层序遍历实现-直接计数-比较好理解
     * @param root
     * @return
     */
    public static int minDepth_LevelOrder(TreeNode root) {

        if(root==null){
            return 0;
        }

        int level = 0;

        Queue<TreeNode> queue = new LinkedList();

        queue.add(root);

        while (!queue.isEmpty()){
            level ++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {

                TreeNode node = queue.poll();

                //如果是叶子节点就返回，这就是最小层的最小深度
                if(node.left==null && node.right==null){
                    return level;
                }

                if(node.left!=null){
                    queue.add(node.left);
                }

                if(node.right!=null){
                    queue.add(node.right);
                }

            }
        }


        return level;
    }






    /**
     * 深度遍历 - 写辅助函数更新外部变量 -比较好理解
     * @param root
     * @return
     */
    int minLevel = Integer.MAX_VALUE;

    public int minDepth_dfs(TreeNode root) {

        if(root==null){
            return 0;
        }

        dfs(root,1);



        return minLevel;
    }

    private void dfs(TreeNode root, int level) {
        if(root==null){
            return;
        }
        if(root.right==null && root.left==null){
            if(level<minLevel){
                minLevel = level;
            }
        }
        dfs(root.left,level+1);
        dfs(root.right,level+1);
    }


    public static void main(String[] args) {
        //3,9,20,null,null,15,7
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;


        System.err.println(minDepth(node1));
        System.err.println(minDepth_LevelOrder(node1));
        System.err.println(new Leet111().minDepth_dfs(node1));


    }
}
