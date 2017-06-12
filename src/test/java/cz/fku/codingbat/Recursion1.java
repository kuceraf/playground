package cz.fku.codingbat;

import org.junit.Test;

/**
 * Created by Filip on 02.04.2017.
 */
public class Recursion1 {
    //p135988 begin
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
    //p135988 end


    //p183649 begin
    @Test
    public void name() throws Exception {
        int result = bunnyEars(10);
        System.out.print(result);
    }

    private int bunnyEars(int bunnies) {
        if(bunnies <= 0) {
            return 0;
        }
        return bunnyEars(bunnies-1) + 2;
    }
    //p183649 end

//    p158175 begin
    @Test
    public void pairStarTest() throws Exception {
        String result = pairStar("hello");
        System.out.print("result: " + result);
    }

    private String pairStar(String str) {
        if(str.length() <=0) {
            return "";
        }
        String letter = str.substring(0,1);
        String ret = pairStar(str.substring(1));
//        System.out.println("ret: " +ret);
//        System.out.println("letter: " +letter);
        if(ret.length() > 0 && letter.equals(ret.substring(0,1))){
            letter = letter+"*";
        }
        return letter+ret;
    }

//    p158175 end
}
