package Leetcode.Leetcode.com.cs7ericleetcode.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author C77eric
 * @version 1.0
 */
public class SingleNumber {
    public int singleNumber1(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    public int singleNumber2(int[] nums) {
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

    public int singleNumber3(int[] nums) {
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
