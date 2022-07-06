package Leetcode.Leetcode.com.cs7ericleetcode.src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author C77eric
 * @version 1.0
 */
public class MajorityElement {
    public class Solution1 {
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

    public class Solution2 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }
}
