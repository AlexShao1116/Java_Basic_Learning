package leetcode;

import javax.print.attribute.HashAttributeSet;
import java.util.Arrays;

public class q4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int aLen = nums1.length;
        int bLen = nums2.length;
        int[] result = new int[aLen + bLen];

        System.arraycopy(nums1, 0, result, 0, aLen);
        System.arraycopy(nums2, 0, result, aLen, bLen);
        Arrays.sort(result);
        int cLen = result.length;
        if(aLen+bLen == 0){
            return 0;
        }else if((aLen+bLen)%2 == 1){
            return result[(cLen-1)/2];
        }else{
            return ((double)(result[(cLen)/2]+result[(cLen-2)/2])/2);
        }
    }
    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));

    }
}
