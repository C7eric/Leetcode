int cmp(const void* _a, const void* _b) {
    int *a = _a, *b = (int*)_b;
    return *a == *b ? 0 : *a > *b ? 1 : -1;
}

int* intersect(int* nums1, int nums1Size, int* nums2, int nums2Size,int* returnSize) {
    qsort(nums1, nums1Size, sizeof(int), cmp);
    qsort(nums2, nums2Size, sizeof(int), cmp);
    *returnSize = 0;
    int* intersection = (int*)malloc(sizeof(int) * fmin(nums1Size, nums2Size));
    int index1 = 0, index2 = 0;
    while (index1 < nums1Size && index2 < nums2Size) {
        if (nums1[index1] < nums2[index2]) {
            index1++;
        } else if (nums1[index1] > nums2[index2]) {
            index2++;
        } else {
            intersection[(*returnSize)++] = nums1[index1];
            index1++;
            index2++;   
        }
    }
    return intersection;
} 

/*
给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。

 

示例 1：

输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
示例 2:

输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]
 

提示：

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
 
排序 + 双指针
如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。

首先对两个数组进行排序，然后使用两个指针遍历两个数组。

初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，
如果两个数字不相等，则将指向较小数字的指针右移一位，如果两个数字相等，将该数字添加到答案，
并将两个指针都右移一位。当至少有一个指针超出数组范围时，遍历结束。

*/