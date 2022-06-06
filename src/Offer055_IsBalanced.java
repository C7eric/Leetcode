package Leetcode.src;

public class Offer055_IsBalanced {
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
  }

    public boolean isBalanced(TreeNode root) {
        return process(root).isBalance;
    }

    public static class Info{
        public int height;
        public boolean isBalance;

        public Info(boolean isBalance,int height){
            this.height = height;
            this.isBalance = isBalance;
        }
    }

    public static Info process(TreeNode x){
        if(x == null){
            return new Info(true,0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        boolean isBalance = true;

        if(!leftInfo.isBalance){
            isBalance = false;
        }
        if(!rightInfo.isBalance){
            isBalance = false;
        }
        if(Math.abs(leftInfo.height - rightInfo.height) > 1){
            isBalance = false;
        }
        return new Info(isBalance,height);
    }
}
