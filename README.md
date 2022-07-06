**Cs7eric LeetCode** 

​																																																			Java Leetcode 刷题笔记

# Leetcode 

## [136. 只出现一次的数字](https://leetcode.cn/problems/single-number/)

难度  简单

给定一个**非空**整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

**说明：**

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

**示例 1:**

```
输入: [2,2,1]
输出: 1
```

**示例 2:**

```
输入: [4,1,2,1,2]
输出: 4
```



### 题解

---

如果不使用额外的空间（例如哈希表），我们可以使用 位运算（异或）来实现



#### 题解代码

```java
class Solution {
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
```

执行用时： 1 ms                       内存消耗:41.7 MB          



#### 异或的性质

- 任何数和 0做异或运算，结果仍然是原来的数，即 a ⊕ 0=a。

- 任何数和其自身做异或运算，结果是 0，即 a ⊕ a=0。
- 异或运算满足交换律和结合律，即  a ⊕ b ⊕ a = b ⊕ a ⊕ a = b ⊕ ( a ⊕ a ) = b ⊕ 0 = b



### 其它解法

---

如果不考虑时间复杂度和空间复杂度，其他解法思路如下



#### 集合存储数字

遍历数组中的每个数字，如果集合中没有该数字，则将该数字加入集合，如果集合中已经有该数字，则将该数字从集合中删除，最后剩下的数字就是只出现一次的数字。



##### 代码示例

```java
class Solution {
    public int singleNumber(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])){
                list.add(nums[i]);
            } else {
                list.remove((Integer) nums[i]);
            }
        }
        return list.get(0);
    }
}
```

执行用时：545 ms    		内存消耗：42.4 MB



#### 集合

使用集合存储数组中出现的所有数字，并计算数组中的元素之和。由于集合保证元素无重复，因此计算集合中的所有元素之和的两倍，即为每个元素出现两次的情况下的元素之和。由于数组中只有一个元素出现一次，其余元素都出现两次，因此用集合中的元素之和的两倍减去数组中的元素之和，剩下的数就是数组中只出现一次的数字。



#### 哈希表

使用哈希表存储每个数字和该数字出现的次数。遍历数组即可得到每个数字出现的次数，并更新哈希表，最后遍历哈希表，得到只出现一次的数字。



##### 代码演示

```java
class Solution {
    public int singleNumber(int[] nums) {
         HashMap<Integer,Integer> map = new HashMap<>();
         for(int num : nums){
             if(!map.containsKey(num)){
                map.put(num,0);
             } else {
                map.put(num,map.get(num) + 1);
             }
         }
         Set<Integer> set = map.keySet();
         for(int key : set){
             if(map.get(key) == 0){
                 return key; 
             }
         }
         return -1;
    }
}
```

执行用时：13 ms         内存消耗：42.5 MB

