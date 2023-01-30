package leetcode;

import java.util.HashMap;

public class q1 {
        public static int[] twoSum(int[] nums, int target) {
            HashMap<Integer,Integer> hashmap = new HashMap();
            hashmap.put(nums[0],0);
            for(int i = 0;i <nums.length; i++){
                int another = target-nums[i];
                if(hashmap.containsKey(another)){
                   int[] s = new int[]{i,hashmap.get(another)};
                    return s;
                }
                hashmap.put(nums[i],i);
            }
            return null;
        }
}
