package cz.fku.codingbat;

import org.junit.Test;

/**
 * Created by Filip on 02.04.2017.
 */
public class Recursion1p135988 {
    @Test
    public void test() {
        int[] a = {11};
        int result = array11(a, 0);
        System.out.print(result);
    }

    public int array11(int[] nums, int index) {
        int total = 0;
        if(index < nums.length){
            index = index +1;
            total = array11(nums, index);
            total = (nums[index-1]==11 ? total=total+1 : total);
        }
        return total;
    }

}
