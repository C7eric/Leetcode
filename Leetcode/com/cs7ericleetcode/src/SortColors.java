package Leetcode.Leetcode.com.cs7ericleetcode.src;

/**
 * @author C77eric
 * @version 1.0
 */
public class SortColors {
    public void sortColors(int[] nums) {
        if(nums.length == 0)    return;

        int n = nums.length;
        int compareNum = 1;
        int less = 0;
        int more = n;
        int index = 0;
        while(index < more){
            if (nums[index] < compareNum){
                swap(nums,++less,index++);
            } else if(nums[index] > compareNum){
                swap(nums,--more,index);
            } else {
                index++;
            }
        }
    }

    public void swap(int[] nums, int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
