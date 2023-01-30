package leetcode;

import java.util.ArrayList;
import java.util.HashSet;

public class q6 {
        public int lengthOfLongestSubstring(String s) {
            String[] letter = s.split("");
            HashSet<String> myset = new HashSet<String>();
            int count = 0;
            for (String e:letter){
                if(!myset.contains(e)){
                    myset.add(e);
                    count++;
                }
            }
            return count;
        }
}
