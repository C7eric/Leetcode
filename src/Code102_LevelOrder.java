package Leetcode.src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code102_LevelOrder {
    public  class TreeNode {
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
class Solution {
    public List<List<Integer>> levelOrder(TreeNode head) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(head == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while(!queue.isEmpty()){

            List<Integer> help = new ArrayList<>();
            int curLevelSizes = queue.size();
            for(int  i = 1;i <= curLevelSizes;++i){
                TreeNode cur = queue.poll();
                help.add(cur.val);
                if(cur.left != null){
                    queue.add(cur.left);
                }
                if(cur.right != null){
                    queue.add(cur.right);
                }
            }
            res.add(help);
        }
        return res;
    }
}
}
