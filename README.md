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





## [706. 设计哈希映射](https://leetcode.cn/problems/design-hashmap/)

难度  简单

不使用任何内建的哈希表库设计一个哈希映射（HashMap）。

实现 `MyHashMap` 类：

- `MyHashMap()` 用空映射初始化对象
- `void put(int key, int value)` 向 HashMap 插入一个键值对 `(key, value)` 。如果 `key` 已经存在于映射中，则更新其对应的值 `value` 。
- `int get(int key)` 返回特定的 `key` 所映射的 `value` ；如果映射中不包含 `key` 的映射，返回 `-1` 。
- `void remove(key)` 如果映射中存在 `key` 的映射，则移除 `key` 和它所对应的 `value` 。

 

**示例：**

```
输入：
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
输出：
[null, null, null, 1, -1, null, 1, null, -1]

解释：
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
```

 

**提示：**

- `0 <= key, value <= 106`
- 最多调用 `104` 次 `put`、`get` 和 `remove` 方法



### 题解

---

#### 题解代码

##### 链地址法

底层用数组 + 链表 实现维护 HashMap

```java
class MyHashMap {

    public class Pair{
        private int key;
        private int value;

        public Pair(int key,int value){
            this.key = key;
            this.value = value;
        }

        public void setKey(int key){
            this.key = key;
        }
        
        public int getKey(){
            return key;
        }

        public void setValue(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    private static final int BASE = 769;
    private LinkedList[] data;

    public MyHashMap() {
        data = new LinkedList[BASE];
        for(int i = 0;i < BASE;++i){
            data[i] = new LinkedList<Pair>();
        }
    }
    
    public void put(int key, int value) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while(iterator.hasNext()){
            Pair pair = iterator.next();
            if(pair.getKey() == key){
                pair.setValue(value);
                return;
            }
        }
        data[h].offerLast(new Pair(key,value));
    }
    
    public int get(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while(iterator.hasNext()){
            Pair pair =  iterator.next();
            if(pair.getKey() == key){
                return pair.value;
            }
        }
        return -1;
    }
    
    public void remove(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while(iterator.hasNext()){
            Pair pair = iterator.next();
            if(pair.getKey() == key){
                data[h].remove(pair);   
                return;
            }
        }
    }

    public int hash(int key){
        return key % BASE;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
```

执行用时:17 ms   内存消耗：48.1 MB





`private static final BASE = 769;` 

**为什么 BASE 的值为 769**

因为769是质数。简单的来说，使用质数就是为了减少冲突。 哈希表的大小取决于一组质数，原因是在hash函数中，要用这些质数来做模运算(%)。而分析发现，如果不是用质数来做模运算的话，很多生活中的数据分布，会集中在某些点上，从而导致冲突率增加。所以这里最后采用了质数做模的除数。

使用质数做了模的除数，自然存储空间的大小也用质数了，因为模完之后，数据是在[0-所选质数)之间。

当然也可以使用其他的质数，保证大小合适就行。



## [56. 合并区间](https://leetcode.cn/problems/merge-intervals/)

难度  中等

以数组 `intervals` 表示若干个区间的集合，其中单个区间为 `intervals[i] = [starti, endi]` 。请你合并所有重叠的区间，并返回 *一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间* 。

 

**示例 1：**

```
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
```

**示例 2：**

```
输入：intervals = [[1,4],[4,5]]
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
```

 

**提示：**

- `1 <= intervals.length <= 104`
- `intervals[i].length == 2`
- `0 <= starti <= endi <= 104`



### 题解

---

#### 题解代码

##### 排序

如果我们按照区间的左端点排序，那么在排完序的列表中，可以合并的区间一定是连续的。

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0){
            return new int[0][2];
        }

        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] interval1,int[] interval2){
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<>();
        for(int i = 0;i < intervals.length;++i){
            int L = intervals[i][0];
            int R = intervals[i][1];
            if(merged.size() == 0 || merged.get(merged.size() - 1)[1] < L){
                merged.add(new int[]{L,R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1],R);
            }
        }
        return merged.toArray(new int[merged.size()][0]);
    }
}
```

执行用时：7 ms   内存消耗：46.6 MB



`merged.size() == 0 || merged.get(merged.size() - 1)[1] < L`

当 merged size == 0 时，或者 前一区间的 R 小于当前 L 值时，区间之间无交集。直接 将区间 {L，R}`add` 到 merged 中   --- `new int[]{L,R}`

反之，区间有交集，将其合并

重新确定 区间的 右边界 R `merged.get(merged.size() - 1)[1]`   比较 前一个区间的 R 与 现区间 R 的大小，取大者为新区间 右边界 `Math.max(merged.get(merged.size() - 1)[1],R)`





## [75. 颜色分类](https://leetcode.cn/problems/sort-colors/)

难度  中等

给定一个包含红色、白色和蓝色、共 `n` 个元素的数组 `nums` ，**[原地](https://baike.baidu.com/item/原地算法)**对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

我们使用整数 `0`、 `1` 和 `2` 分别表示红色、白色和蓝色。



必须在不使用库的sort函数的情况下解决这个问题。

 

**示例 1：**

```
输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]
```

**示例 2：**

```
输入：nums = [2,0,1]
输出：[0,1,2]
```

 

**提示：**

- `n == nums.length`
- `1 <= n <= 300`
- `nums[i]` 为 `0`、`1` 或 `2`

 

**进阶：**

- 你可以不使用代码库中的排序函数来解决这道题吗？
- 你能想出一个仅使用常数空间的一趟扫描算法吗？





### 题解

---

#### 题解代码

##### 快速排序

将问题转化为在 0，1，2 之间排序，且仅使用常数空间完成一趟扫描

将 1 视为比较值 `compareNum`，设置 less,more 边界，通过 一个 partition ，将 0，1，2 排序完成

```java
public class SortColors {
    public void sortColors(int[] nums) {
        if(nums.length == 0)    return;

        int n = nums.length;
        int compareNum = 1;
        int less = 0 - 1;
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
```

执行用时: 0 ms    内存消耗：40.0 MB



###### 广泛 partition 函数

```java
public static int[] partition(int[] arr,int L,int R){
     int less = L - 1;  // 左边界
     int more = R;  // 右边界
     while(L < more){   // L 表示当前数的位置 arr[R] -> 划分值
         if(arr[L] < arr[R]){  // 当前数 < 划分值
             swap(arr,++less,L++);
         } else if(arr[L] > arr[R]){  // 当前数 > 划分值
             swap(arr,--more,L);
         } else {
             L++;
         }
     }
     swap(arr,more,R);
     return new int[]{less + 1,more};
 }
```





## [119. 杨辉三角 II](https://leetcode.cn/problems/pascals-triangle-ii/)

难度  简单

给定一个非负索引 `rowIndex`，返回「杨辉三角」的第 `rowIndex` 行。

在「杨辉三角」中，每个数是它左上方和右上方的数的和。

![img](https://pic.leetcode-cn.com/1626927345-DZmfxB-PascalTriangleAnimated2.gif)

 

**示例 1:**

```
输入: rowIndex = 3
输出: [1,3,3,1]
```

**示例 2:**

```
输入: rowIndex = 0
输出: [1]
```

**示例 3:**

```
输入: rowIndex = 1
输出: [1,1]
```

 

**提示:**

- `0 <= rowIndex <= 33`

 

**进阶：**

你可以优化你的算法到 `*O*(*rowIndex*)` 空间复杂度吗？



### 题解

---

#### 递推

##### 心路历程

由杨辉三角性质可知，每个数字等于上一行的左右两个数字之和，可用此性质写出整个杨辉三角。即第 n 行的第 i 个数等于第 n−1 行的第 i−1 个数和第 i 个数之和。

定义 `List<List<Integer>> list` 作为 `@return ` ,利用循环将每一层 的元素 `add`  进 tmp，外循环将 tmp `add` 进 res

##### 代码

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for(int i = 0;i <= rowIndex;++i){
            List<Integer> tmp = new ArrayList<>();
            for(int j = 0;j <= i;++j){
                if(j == 0 || j == i){
                    tmp.add(1);
                } else {
                    tmp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(tmp);
        }
        return res.get(rowIndex);
    }
}
```

执行用时： 1 ms  内存消耗：39.2 MB



`tmp.add(res.get(i - 1).get(j) + res.get(i - 1).get(j - 1))`

每个元素等于上一行左右两个数字之和





## [59. 螺旋矩阵 II](https://leetcode.cn/problems/spiral-matrix-ii/)

难度  中等

给你一个正整数 `n` ，生成一个包含 `1` 到 `n2` 所有元素，且元素按顺时针顺序螺旋排列的 `n x n` 正方形矩阵 `matrix` 。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/11/13/spiraln.jpg)

```
输入：n = 3
输出：[[1,2,3],[8,9,4],[7,6,5]]
```

**示例 2：**

```
输入：n = 1
输出：[[1]]
```

 

**提示：**

- `1 <= n <= 20`



### 题解

---

#### 模拟

##### 心路历程

按照矩阵旋转方向，每当到达边界时，就按照旋转方法旋转矩阵



##### 代码

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int curNum = 1;
        int maxNum = n * n;
        int[][] matrix = new int[n][n];
        int row = 0,column = 0;
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
        int directionIndex = 0;
        while(curNum <= maxNum){
            matrix[row][column] = curNum;
            curNum++;
            int nextRow = row + directions[directionIndex][0],nextColumn = column + directions[directionIndex][1];
            if(nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n || matrix[nextRow][nextColumn] != 0){
                directionIndex = (directionIndex + 1) % 4;
            }
            row = row + directions[directionIndex][0];
            column = column + directions[directionIndex][1];
        }
        return matrix;
    }
}
```

执行用时：0 ms  内存消耗：39.1 MB



 `int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};`  方向 ： 右下左上

 `int nextRow = row + directions[directionIndex][0],nextColumn = column + directions[directionIndex][1]; ` 定义 nextRow / Column 判断是否越界，若越界，则顺时针旋转至下一个方向  `direcitonIndex = (dierctionIndex + 1) % 4`  

 `row = row + directions[directionIndex][0];
 column = column + directions[directionIndex][1];` 重新赋值给 row , column





## [48. 旋转图像](https://leetcode.cn/problems/rotate-image/)

难度  中等

给定一个 *n* × *n* 的二维矩阵 `matrix` 表示一个图像。请你将图像顺时针旋转 90 度。

你必须在**[ 原地](https://baike.baidu.com/item/原地算法)** 旋转图像，这意味着你需要直接修改输入的二维矩阵。**请不要** 使用另一个矩阵来旋转图像。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/08/28/mat1.jpg)

```
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[[7,4,1],[8,5,2],[9,6,3]]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/08/28/mat2.jpg)

```
输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
```

 

**提示：**

- `n == matrix.length == matrix[i].length`
- `1 <= n <= 20`
- `-1000 <= matrix[i][j] <= 1000`

 

### 题解

---

#### 使用辅助数组

##### 心路历程

借助辅助数组将旋转后的图像元素各位置记录下来，再重新给原数组赋值



##### 代码

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] tmpMatrix = new int[n][n];
        for(int i = 0;i < n;++i){
            for(int j = 0;j < n;++j){
                tmpMatrix[j][n - i - 1] = matrix[i][j]; 
            }
        }

        for(int i = 0;i < n;++i){
            for(int j = 0;j < n;++j){
                matrix[i][j] = tmpMatrix[i][j];
            }
        }
    }
}
```

执行用时：0ms  内存消耗：41.3 MB



由题可知，旋转后的各元素的位置为 `[j][n - i - 1]`  , 所以遍历整个数组 拿到值，按照旋转后的位置赋值，再通过循环将值重新赋给原数组



#### TODO 原地旋转



## [435. 无重叠区间](https://leetcode.cn/problems/non-overlapping-intervals/)

难度  中等

给定一个区间的集合 `intervals` ，其中 `intervals[i] = [starti, endi]` 。返回 *需要移除区间的最小数量，使剩余区间互不重叠* 。

 

**示例 1:**

```
输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
输出: 1
解释: 移除 [1,3] 后，剩下的区间没有重叠。
```

**示例 2:**

```
输入: intervals = [ [1,2], [1,2], [1,2] ]
输出: 2
解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
```

**示例 3:**

```
输入: intervals = [ [1,2], [2,3] ]
输出: 0
解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
```

 

**提示:**

- `1 <= intervals.length <= 105`
- `intervals[i].length == 2`
- `-5 * 104 <= starti < endi <= 5 * 104`



### 题解

---

#### 动态规划

##### 心路历程

题目等价于 选出最多的区间，并且他们互不重叠，所以先按照端点的大小进行排序，然后使用 dp 去求区间的最大值





##### 代码

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0)   return 0;

        Arrays.sort(intervals,(a,b) ->(a[1] - b[1]));

        int n = intervals.length;
        int[] f = new int[n];
        Arrays.fill(f,1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (intervals[j][1] <= intervals[i][0]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        return n - Arrays.stream(f).max().getAsInt();
    }
}
```

执行用时： 超时（数据量太大）时间复杂度 是 O(n ^ 2)



转移状态方程为

<img src="C:\Users\CSQ-PC\AppData\Roaming\Typora\typora-user-images\image-20220709172807693.png" alt="image-20220709172807693" style="zoom:50%;" />

![image-20220709174337407](C:\Users\CSQ-PC\AppData\Roaming\Typora\typora-user-images\image-20220709174337407.png)

#### 贪心

##### 心路历程





##### 代码

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0)   return 0;

        Arrays.sort(intervals,(a,b) ->(a[1] - b[1]));

        int n = intervals.length;
        int right = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            if (intervals[i][0] >= right) {
                ++ans;
                right = intervals[i][1];
            }
        }
        return n - ans;
    }
}
```

执行用时：47 ms  内存消耗：97.7 MB





## [240. 搜索二维矩阵 II](https://leetcode.cn/problems/search-a-2d-matrix-ii/)

难度中等

编写一个高效的算法来搜索 `*m* x *n*` 矩阵 `matrix` 中的一个目标值 `target` 。该矩阵具有以下特性：

- 每行的元素从左到右升序排列。
- 每列的元素从上到下升序排列。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/searchgrid2.jpg)

```
输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
输出：true
```

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/searchgrid.jpg)

```
输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
输出：false
```

 

**提示：**

- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= n, m <= 300`
- `-109 <= matrix[i][j] <= 109`
- 每行的所有元素从左到右升序排列
- 每列的所有元素从上到下升序排列
- `-109 <= target <= 109`





### 题解

---

#### Z 字搜索

##### 心路历程

由题，每行的元素都从左到右升序排列，每列的元素从上到下升序排列，故追求最少的次数确定最多的情况时，将起始点放在右上角，此时左边的元素小于 `matrix[x][y]` ,下面的元素大于 `matrix[x][y]` , `matrix[x][y]>target  ` 时，向左搜索，即 `y--` ,与之同理， `matrix[x][y] < target  ` 时，向下搜索，即 `x++ `，每次确定一行或一列。



##### 代码

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int x = 0,y = n - 1;
        while(x < m && y >= 0){
            if(matrix[x][y] == target){
                return true;  
            } 
            if(matrix[x][y] > target){
                y--;
            } else {
                ++x;
            }
        } 
        return false;
    }
}
```

执行用时： 5 ms  内存消耗： 47.5 MB



注意 while 循环边界 `x < m && y >= 0 `







## [435. 无重叠区间](https://leetcode.cn/problems/non-overlapping-intervals/)

难度  中等

给定一个区间的集合 `intervals` ，其中 `intervals[i] = [starti, endi]` 。返回 *需要移除区间的最小数量，使剩余区间互不重叠* 。

 

**示例 1:**

```
输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
输出: 1
解释: 移除 [1,3] 后，剩下的区间没有重叠。
```

**示例 2:**

```
输入: intervals = [ [1,2], [1,2], [1,2] ]
输出: 2
解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
```

**示例 3:**

```
输入: intervals = [ [1,2], [2,3] ]
输出: 0
解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
```

 

**提示:**

- `1 <= intervals.length <= 105`
- `intervals[i].length == 2`
- `-5 * 104 <= starti < endi <= 5 * 104`





## [2331. 计算布尔二叉树的值](https://leetcode.cn/problems/evaluate-boolean-binary-tree/)

难度简单5收藏分享切换为英文接收动态反馈

给你一棵 **完整二叉树** 的根，这棵树有以下特征：

- **叶子节点** 要么值为 `0` 要么值为 `1` ，其中 `0` 表示 `False` ，`1` 表示 `True` 。
- **非叶子节点** 要么值为 `2` 要么值为 `3` ，其中 `2` 表示逻辑或 `OR` ，`3` 表示逻辑与 `AND` 。

**计算** 一个节点的值方式如下：

- 如果节点是个叶子节点，那么节点的 **值** 为它本身，即 `True` 或者 `False` 。
- 否则，**计算** 两个孩子的节点值，然后将该节点的运算符对两个孩子值进行 **运算** 。

返回根节点 `root` 的布尔运算值。

**完整二叉树** 是每个节点有 `0` 个或者 `2` 个孩子的二叉树。

**叶子节点** 是没有孩子的节点。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2022/05/16/example1drawio1.png)

```
输入：root = [2,1,3,null,null,0,1]
输出：true
解释：上图展示了计算过程。
AND 与运算节点的值为 False AND True = False 。
OR 运算节点的值为 True OR False = True 。
根节点的值为 True ，所以我们返回 true 。
```

**示例 2：**

```
输入：root = [0]
输出：false
解释：根节点是叶子节点，且值为 false，所以我们返回 false 。
```

 

**提示：**

- 树中节点数目在 `[1, 1000]` 之间。
- `0 <= Node.val <= 3`
- 每个节点的孩子数为 `0` 或 `2` 。
- 叶子节点的值为 `0` 或 `1` 。
- 非叶子节点的值为 `2` 或 `3` 。







## [334. 递增的三元子序列](https://leetcode.cn/problems/increasing-triplet-subsequence/)

难度中等593收藏分享切换为英文接收动态反馈

给你一个整数数组 `nums` ，判断这个数组中是否存在长度为 `3` 的递增子序列。

如果存在这样的三元组下标 `(i, j, k)` 且满足 `i < j < k` ，使得 `nums[i] < nums[j] < nums[k]` ，返回 `true` ；否则，返回 `false` 。

 

**示例 1：**

```
输入：nums = [1,2,3,4,5]
输出：true
解释：任何 i < j < k 的三元组都满足题意
```

**示例 2：**

```
输入：nums = [5,4,3,2,1]
输出：false
解释：不存在满足题意的三元组
```

**示例 3：**

```
输入：nums = [2,1,5,0,4,6]
输出：true
解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
```

 

**提示：**

- `1 <= nums.length <= 5 * 105`
- `-231 <= nums[i] <= 231 - 1`







## [560. 和为 K 的子数组](https://leetcode.cn/problems/subarray-sum-equals-k/)

难度中等1581收藏分享切换为英文接收动态反馈

给你一个整数数组 `nums` 和一个整数 `k` ，请你统计并返回 *该数组中和为 `k` 的连续子数组的个数* 。

 

**示例 1：**

```
输入：nums = [1,1,1], k = 2
输出：2
```

**示例 2：**

```
输入：nums = [1,2,3], k = 3
输出：2
```

 

**提示：**

- `1 <= nums.length <= 2 * 104`
- `-1000 <= nums[i] <= 1000`
- `-107 <= k <= 107`

















## [415. 字符串相加](https://leetcode.cn/problems/add-strings/)

难度简单587收藏分享切换为英文接收动态反馈

给定两个字符串形式的非负整数 `num1` 和`num2` ，计算它们的和并同样以字符串形式返回。

你不能使用任何內建的用于处理大整数的库（比如 `BigInteger`）， 也不能直接将输入的字符串转换为整数形式。

 

**示例 1：**

```
输入：num1 = "11", num2 = "123"
输出："134"
```

**示例 2：**

```
输入：num1 = "456", num2 = "77"
输出："533"
```

**示例 3：**

```
输入：num1 = "0", num2 = "0"
输出："0"
```

 

 

**提示：**

- `1 <= num1.length, num2.length <= 104`
- `num1` 和`num2` 都只包含数字 `0-9`
- `num1` 和`num2` 都不包含任何前导零







## [409. 最长回文串](https://leetcode.cn/problems/longest-palindrome/)

难度简单434收藏分享切换为英文接收动态反馈

给定一个包含大写字母和小写字母的字符串 `s` ，返回 *通过这些字母构造成的 **最长的回文串*** 。

在构造过程中，请注意 **区分大小写** 。比如 `"Aa"` 不能当做一个回文字符串。

 

**示例 1:**

```
输入:s = "abccccdd"
输出:7
解释:
我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
```

**示例 2:**

```
输入:s = "a"
输入:1
```

**示例 3:**

```
输入:s = "bb"
输入: 2
```

 

**提示:**

- `1 <= s.length <= 2000`
- `s` 只能由小写和/或大写英文字母组成







## [238. 除自身以外数组的乘积](https://leetcode.cn/problems/product-of-array-except-self/)

难度中等1210收藏分享切换为英文接收动态反馈

给你一个整数数组 `nums`，返回 *数组 `answer` ，其中 `answer[i]` 等于 `nums` 中除 `nums[i]` 之外其余各元素的乘积* 。

题目数据 **保证** 数组 `nums`之中任意元素的全部前缀元素和后缀的乘积都在 **32 位** 整数范围内。

请**不要使用除法，**且在 `O(*n*)` 时间复杂度内完成此题。

 

**示例 1:**

```
输入: nums = [1,2,3,4]
输出: [24,12,8,6]
```

**示例 2:**

```
输入: nums = [-1,1,0,-3,3]
输出: [0,0,9,0,0]
```

 

**提示：**

- `2 <= nums.length <= 105`
- `-30 <= nums[i] <= 30`
- **保证** 数组 `nums`之中任意元素的全部前缀元素和后缀的乘积都在 **32 位** 整数范围内





## [290. 单词规律](https://leetcode.cn/problems/word-pattern/)

难度简单484收藏分享切换为英文接收动态反馈

给定一种规律 `pattern` 和一个字符串 `s` ，判断 `s` 是否遵循相同的规律。

这里的 **遵循** 指完全匹配，例如， `pattern` 里的每个字母和字符串 `s` 中的每个非空单词之间存在着双向连接的对应规律。

 

**示例1:**

```
输入: pattern = "abba", s = "dog cat cat dog"
输出: true
```

**示例 2:**

```
输入:pattern = "abba", s = "dog cat cat fish"
输出: false
```

**示例 3:**

```
输入: pattern = "aaaa", s = "dog cat cat dog"
输出: false
```

 

**提示:**

- `1 <= pattern.length <= 300`
- `pattern` 只包含小写英文字母
- `1 <= s.length <= 3000`
- `s` 只包含小写英文字母和 `' '`
- `s` **不包含** 任何前导或尾随对空格
- `s` 中每个单词都被 **单个空格** 分隔











## [763. 划分字母区间](https://leetcode.cn/problems/partition-labels/)

难度中等769收藏分享切换为英文接收动态反馈

字符串 `S` 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。

 

**示例：**

```
输入：S = "ababcbacadefegdehijhklij"
输出：[9,7,8]
解释：
划分结果为 "ababcbaca", "defegde", "hijhklij"。
每个字母最多出现在一个片段中。
像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
```

 

**提示：**

- `S`的长度在`[1, 500]`之间。
- `S`只包含小写字母 `'a'` 到 `'z'` 。

通过次数124,265

提交次数161,947





## [49. 字母异位词分组](https://leetcode.cn/problems/group-anagrams/)

难度中等1198收藏分享切换为英文接收动态反馈

给你一个字符串数组，请你将 **字母异位词** 组合在一起。可以按任意顺序返回结果列表。

**字母异位词** 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。

 

**示例 1:**

```
输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
```

**示例 2:**

```
输入: strs = [""]
输出: [[""]]
```

**示例 3:**

```
输入: strs = ["a"]
输出: [["a"]]
```

 

**提示：**

- `1 <= strs.length <= 104`
- `0 <= strs[i].length <= 100`
- `strs[i]` 仅包含小写字母







## [43. 字符串相乘](https://leetcode.cn/problems/multiply-strings/)

难度中等983收藏分享切换为英文接收动态反馈

给定两个以字符串形式表示的非负整数 `num1` 和 `num2`，返回 `num1` 和 `num2` 的乘积，它们的乘积也表示为字符串形式。

**注意：**不能使用任何内置的 BigInteger 库或直接将输入转换为整数。

 

**示例 1:**

```
输入: num1 = "2", num2 = "3"
输出: "6"
```

**示例 2:**

```
输入: num1 = "123", num2 = "456"
输出: "56088"
```

 

**提示：**

- `1 <= num1.length, num2.length <= 200`
- `num1` 和 `num2` 只能由数字组成。
- `num1` 和 `num2` 都不包含任何前导零，除了数字0本身。





## [187. 重复的DNA序列](https://leetcode.cn/problems/repeated-dna-sequences/)

难度中等389收藏分享切换为英文接收动态反馈

**DNA序列** 由一系列核苷酸组成，缩写为 `'A'`, `'C'`, `'G'` 和 `'T'`.。

- 例如，`"ACGAATTCCG"` 是一个 **DNA序列** 。

在研究 **DNA** 时，识别 DNA 中的重复序列非常有用。

给定一个表示 **DNA序列** 的字符串 `s` ，返回所有在 DNA 分子中出现不止一次的 **长度为 `10`** 的序列(子字符串)。你可以按 **任意顺序** 返回答案。

 

**示例 1：**

```
输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
输出：["AAAAACCCCC","CCCCCAAAAA"]
```

**示例 2：**

```
输入：s = "AAAAAAAAAAAAA"
输出：["AAAAAAAAAA"]
```

 

**提示：**

- `0 <= s.length <= 105`
- `s[i]``==``'A'`、`'C'`、`'G'` or `'T'`







## [5. 最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/)

难度中等5466收藏分享切换为英文接收动态反馈

给你一个字符串 `s`，找到 `s` 中最长的回文子串。

 

**示例 1：**

```
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
```

**示例 2：**

```
输入：s = "cbbd"
输出："bb"
```

 

**提示：**

- `1 <= s.length <= 1000`
- `s` 仅由数字和英文字母组成







## [142. 环形链表 II](https://leetcode.cn/problems/linked-list-cycle-ii/)

难度中等1677收藏分享切换为英文接收动态反馈

给定一个链表的头节点  `head` ，返回链表开始入环的第一个节点。 *如果链表无环，则返回 `null`。*

如果链表中有某个节点，可以通过连续跟踪 `next` 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 `pos` 来表示链表尾连接到链表中的位置（**索引从 0 开始**）。如果 `pos` 是 `-1`，则在该链表中没有环。**注意：`pos` 不作为参数进行传递**，仅仅是为了标识链表的实际情况。

**不允许修改** 链表。



 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

```
输入：head = [3,2,0,-4], pos = 1
输出：返回索引为 1 的链表节点
解释：链表中有一个环，其尾部连接到第二个节点。
```

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png)

```
输入：head = [1,2], pos = 0
输出：返回索引为 0 的链表节点
解释：链表中有一个环，其尾部连接到第一个节点。
```

**示例 3：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png)

```
输入：head = [1], pos = -1
输出：返回 null
解释：链表中没有环。
```

 

**提示：**

- 链表中节点的数目范围在范围 `[0, 104]` 内
- `-105 <= Node.val <= 105`
- `pos` 的值为 `-1` 或者链表中的一个有效索引







## [2. 两数相加](https://leetcode.cn/problems/add-two-numbers/)

难度中等8363收藏分享切换为英文接收动态反馈

给你两个 **非空** 的链表，表示两个非负的整数。它们每位数字都是按照 **逆序** 的方式存储的，并且每个节点只能存储 **一位** 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/02/addtwonumber1.jpg)

```
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
```

**示例 2：**

```
输入：l1 = [0], l2 = [0]
输出：[0]
```

**示例 3：**

```
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]
```

 

**提示：**

- 每个链表中的节点数在范围 `[1, 100]` 内
- `0 <= Node.val <= 9`
- 题目数据保证列表表示的数字不含前导零







## [160. 相交链表](https://leetcode.cn/problems/intersection-of-two-linked-lists/)

难度简单1768收藏分享切换为英文接收动态反馈

给你两个单链表的头节点 `headA` 和 `headB` ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 `null` 。

图示两个链表在节点 `c1` 开始相交**：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)

题目数据 **保证** 整个链式结构中不存在环。

**注意**，函数返回结果后，链表必须 **保持其原始结构** 。

**自定义评测：**

**评测系统** 的输入如下（你设计的程序 **不适用** 此输入）：

- `intersectVal` - 相交的起始节点的值。如果不存在相交节点，这一值为 `0`
- `listA` - 第一个链表
- `listB` - 第二个链表
- `skipA` - 在 `listA` 中（从头节点开始）跳到交叉节点的节点数
- `skipB` - 在 `listB` 中（从头节点开始）跳到交叉节点的节点数

评测系统将根据这些输入创建链式数据结构，并将两个头节点 `headA` 和 `headB` 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被 **视作正确答案** 。

 

**示例 1：**

[![img](https://assets.leetcode.com/uploads/2021/03/05/160_example_1_1.png)](https://assets.leetcode.com/uploads/2018/12/13/160_example_1.png)

```
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
输出：Intersected at '8'
解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```

**示例 2：**

[![img](https://assets.leetcode.com/uploads/2021/03/05/160_example_2.png)](https://assets.leetcode.com/uploads/2018/12/13/160_example_2.png)

```
输入：intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Intersected at '2'
解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
从各自的表头开始算起，链表 A 为 [1,9,1,2,4]，链表 B 为 [3,2,4]。
在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```

**示例 3：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_3.png)](https://assets.leetcode.com/uploads/2018/12/13/160_example_3.png)

```
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
这两个链表不相交，因此返回 null 。
```

 

**提示：**

- `listA` 中节点数目为 `m`
- `listB` 中节点数目为 `n`
- `1 <= m, n <= 3 * 104`
- `1 <= Node.val <= 105`
- `0 <= skipA <= m`
- `0 <= skipB <= n`
- 如果 `listA` 和 `listB` 没有交点，`intersectVal` 为 `0`
- 如果 `listA` 和 `listB` 有交点，`intersectVal == listA[skipA] == listB[skipB]`





## [82. 删除排序链表中的重复元素 II](https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/)

难度中等944收藏分享切换为英文接收动态反馈

给定一个已排序的链表的头 `head` ， *删除原始链表中所有重复数字的节点，只留下不同的数字* 。返回 *已排序的链表* 。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/01/04/linkedlist1.jpg)

```
输入：head = [1,2,3,3,4,4,5]
输出：[1,2,5]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2021/01/04/linkedlist2.jpg)

```
输入：head = [1,1,1,2,3]
输出：[2,3]
```

 

**提示：**

- 链表中节点数目在范围 `[0, 300]` 内
- `-100 <= Node.val <= 100`
- 题目数据保证链表已经按升序 **排列**







## [24. 两两交换链表中的节点](https://leetcode.cn/problems/swap-nodes-in-pairs/)

难度中等1472收藏分享切换为英文接收动态反馈

给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/10/03/swap_ex1.jpg)

```
输入：head = [1,2,3,4]
输出：[2,1,4,3]
```

**示例 2：**

```
输入：head = []
输出：[]
```

**示例 3：**

```
输入：head = [1]
输出：[1]
```

 

**提示：**

- 链表中节点的数目在范围 `[0, 100]` 内
- `0 <= Node.val <= 100`





## [707. 设计链表](https://leetcode.cn/problems/design-linked-list/)

难度中等504收藏分享切换为英文接收动态反馈

设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：`val` 和 `next`。`val` 是当前节点的值，`next` 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 `prev` 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。

在链表类中实现这些功能：

- get(index)：获取链表中第 `index` 个节点的值。如果索引无效，则返回`-1`。
- addAtHead(val)：在链表的第一个元素之前添加一个值为 `val` 的节点。插入后，新节点将成为链表的第一个节点。
- addAtTail(val)：将值为 `val` 的节点追加到链表的最后一个元素。
- addAtIndex(index,val)：在链表中的第 `index` 个节点之前添加值为 `val` 的节点。如果 `index` 等于链表的长度，则该节点将附加到链表的末尾。如果 `index` 大于链表长度，则不会插入节点。如果`index`小于0，则在头部插入节点。
- deleteAtIndex(index)：如果索引 `index` 有效，则删除链表中的第 `index` 个节点。

 

**示例：**

```
MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
linkedList.get(1);            //返回2
linkedList.deleteAtIndex(1);  //现在链表是1-> 3
linkedList.get(1);            //返回3
```

 

**提示：**

- 所有`val`值都在 `[1, 1000]` 之内。
- 操作次数将在 `[1, 1000]` 之内。
- 请不要使用内置的 LinkedList 库。









## [25. K 个一组翻转链表](https://leetcode.cn/problems/reverse-nodes-in-k-group/)

难度困难1723收藏分享切换为英文接收动态反馈

给你链表的头节点 `head` ，每 `k` 个节点一组进行翻转，请你返回修改后的链表。

`k` 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 `k` 的整数倍，那么请将最后剩余的节点保持原有顺序。

你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/10/03/reverse_ex1.jpg)

```
输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/10/03/reverse_ex2.jpg)

```
输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]
```

 

**提示：**

- 链表中的节点数目为 `n`
- `1 <= k <= n <= 5000`
- `0 <= Node.val <= 1000`





## [143. 重排链表](https://leetcode.cn/problems/reorder-list/)

难度中等964收藏分享切换为英文接收动态反馈

给定一个单链表 `L` 的头节点 `head` ，单链表 `L` 表示为：

```
L0 → L1 → … → Ln - 1 → Ln
```

请将其重新排列后变为：

```
L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
```

不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 

**示例 1：**

![img](https://pic.leetcode-cn.com/1626420311-PkUiGI-image.png)

```
输入：head = [1,2,3,4]
输出：[1,4,2,3]
```

**示例 2：**

![img](https://pic.leetcode-cn.com/1626420320-YUiulT-image.png)

```
输入：head = [1,2,3,4,5]
输出：[1,5,2,4,3]
```

 

**提示：**

- 链表的长度范围为 `[1, 5 * 104]`
- `1 <= node.val <= 1000`





## [148. 排序链表](https://leetcode.cn/problems/sort-list/)

难度中等1695收藏分享切换为英文接收动态反馈

给你链表的头结点 `head` ，请将其按 **升序** 排列并返回 **排序后的链表** 。



 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/09/14/sort_list_1.jpg)

```
输入：head = [4,2,1,3]
输出：[1,2,3,4]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/09/14/sort_list_2.jpg)

```
输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]
```

**示例 3：**

```
输入：head = []
输出：[]
```

 

**提示：**

- 链表中节点的数目在范围 `[0, 5 * 104]` 内
- `-105 <= Node.val <= 105`
