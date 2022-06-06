int cmp(int* a,int* b){
    return *a - *b;
}

int singleNumber(int* nums, int numsSize){

    int i = 0;
    int j = 1;
    qsort(nums,numsSize,sizeof(int),cmp);
    while(j <= numsSize - 1){
        if(nums[i] != nums[j]){
            return nums[i];
        }
        i += 2;
        j += 2;
    }
    return nums[numsSize - 1];
}

/*
136. 只出现一次的数字
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1
示例 2:

输入: [4,1,2,1,2]
输出: 4
*/