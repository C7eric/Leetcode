package Leetcode.src;

import javax.swing.tree.TreeNode;

public class Offer068_LowestCommonAncesotor {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root,p,q).ans;
    }

    public static class Info{
        public boolean findA;
        public boolean findB;
        public TreeNode ans;

        public Info(boolean fA,boolean fB,TreeNode an){
            this.findA = fA;
            this.findB = fB;
            this.ans = an;
        }
    }

    public static Info process(TreeNode x,TreeNode a,TreeNode b){
        if(x == null){
            return new Info(false,false,null);
        }

        Info leftInfo = process(x.left,a,b);
        Info rightInfo = process(x.right,a,b);

        boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
        boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;

        TreeNode ans = null;
        if(leftInfo.ans != null){
            ans = leftInfo.ans;
        } else if(rightInfo.ans != null){
            ans = rightInfo.ans;
        } else {
            if(findB && findA){
                ans = x;
            }
        }
        return new Info(findA,findB,ans);
    }
}
