/**
 * Note: The returned array must be malloced, assume caller calls free().
 */

int* twoSum(int* nums, int numsSize, int target, int* returnSize)
{
    for(int i = 0; i < numsSize; ++i)
    {//主循环遍历第0 ~ numSize个元素num[i]
        for(int j = i + 1; j < numsSize; ++j)
        {//次循环遍历第j ~ numSize个元素num[j]
            if(nums[i] + nums[j] == target)
            {//判断前后两元素的和是否为目标值
                int* ret = malloc(sizeof(int) * 2);//开辟2个int类型长度的堆空间，交给一个int*指针维护
                ret[0] = i, ret[1] = j;//存储数组下标
                *returnSize = 2;//返回数为2
                return ret;//找到返回指向下标数组的指针
            }
        }
    }
    *returnSize = 0;//返回数为0
    return NULL;//找不到返回空指针
}



/*
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

 

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
示例 2：

输入：nums = [3,2,4], target = 6
输出：[1,2]
示例 3：

输入：nums = [3,3], target = 6
输出：[0,1]
 

提示：

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
只会存在一个有效答案

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/two-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/