package cn.northpark.LeetCode.树的遍历;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liuhouer
 * @date 2021年07月07日
 *
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 */
public class Leet104 {



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
     * 递归实现 -难以理解
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {

        if(root==null){
            return 0;
        }

        return root==null?0:1+Math.max(maxDepth(root.left),maxDepth(root.right));

    }




    /**
     * 【bfs】层序遍历实现-直接计数-比较好理解
     * @param root
     * @return
     */
    public static int maxDepth_LevelOrder(TreeNode root) {

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
    int maxLevel = 0;

    public int maxDepth_dfs(TreeNode root) {

        if(root==null){
            return 0;
        }

        dfs(root,1);



        return maxLevel;
    }

    private void dfs(TreeNode root, int level) {
        if(root==null){
            return;
        }
        if(level > maxLevel){
            maxLevel = level;
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


        System.err.println(maxDepth_LevelOrder(node1));
        System.err.println(new Leet104().maxDepth_dfs(node1));


    }
}
