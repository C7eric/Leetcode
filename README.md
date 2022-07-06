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



#### 位运算

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



##### 异或的性质

- 任何数和 0做异或运算，结果仍然是原来的数，即 a ⊕ 0=a。

- 任何数和其自身做异或运算，结果是 0，即 a ⊕ a=0。
- 异或运算满足交换律和结合律，即  a ⊕ b ⊕ a = b ⊕ a ⊕ a = b ⊕ ( a ⊕ a ) = b ⊕ 0 = b



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







## [169. 多数元素](https://leetcode.cn/problems/majority-element/)

难度    简单

给定一个大小为 `n` 的数组 `nums` ，返回其中的多数元素。多数元素是指在数组中出现次数 **大于** `⌊ n/2 ⌋` 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 

**示例 1：**

```
输入：nums = [3,2,3]
输出：3
```

**示例 2：**

```
输入：nums = [2,2,1,1,1,2,2]
输出：2
```

 

**提示：**

- `n == nums.length`
- `1 <= n <= 5 * 104`
- `-109 <= nums[i] <= 109`

 

**进阶：**尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。



### 题解

---

最简单的暴力方法是，枚举数组中的每个元素，再遍历一遍数组统计其出现次数。该方法的时间复杂度是 O(n^2)O(n2)，会超出时间限制，因此我们需要找出时间复杂度小于 O(n^2)O(n）的优秀做法。



#### 题解代码

##### 哈希表

我们使用哈希映射（HashMap）来存储每个元素以及出现的次数。对于哈希映射中的每个键值对，键表示一个元素，值表示该元素出现的次数。

我们用一个循环遍历数组 nums 并将数组中的每个元素加入哈希映射中。在这之后，我们遍历哈希映射中的所有键值对，返回值最大的键。我们同样也可以在遍历数组 nums 时候使用打擂台的方法，维护最大的值，这样省去了最后对哈希映射的遍历。



###### 题解代码

```java
class Solution {
    private HashMap<Integer,Integer> countsNum(int[] nums){
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            if(!map.containsKey(num)){
                map.put(num,1);
            } else {
                map.put(num,map.get(num) + 1);
            }
        }
        return map;
    }
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map = countsNum(nums);
        Map.Entry<Integer,Integer> majorityEntry = null;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(majorityEntry == null || entry.getValue() > majorityEntry.getValue()){
                majorityEntry = entry;
            }
        }
        return majorityEntry.getKey();
    }
}
```

执行用时：13 ms      内存消耗：46.5 MB



##### 排序

如果将数组 nums 中的所有元素按照单调递增或单调递减的顺序排序，那么下标为 n / 2 的元素（下标从 0 开始）一定是众数。

对于每种情况，数组下面的线表示如果众数是数组中的最小值时覆盖的下标，数组下面的线表示如果众数是数组中的最大值时覆盖的下标。对于其他的情况，这条线会在这两种极端情况的中间。对于这两种极端情况，它们会在下标为 n \ 2 的地方有重叠。因此，无论众数是多少，返回  n \ 2 下标对应的值都是正确的。



###### 题解代码

```java
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
```

执行用时： 2 ms     内存消耗：44.9 MB  
