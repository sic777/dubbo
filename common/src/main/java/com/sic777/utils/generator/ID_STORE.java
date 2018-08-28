package com.sic777.utils.generator;

import java.util.Random;

/**
 * <p>ID生成库
 *
 * @author sic777
 * @since 0.0.1
 * @deprecated
 */
public class ID_STORE {

    public static void main(String[] args) throws InterruptedException {
        int a_count = 200000;
        int b_count = 10000;
        int a_start = 20000;
        int b_start = 1000;
        int[] a = new int[a_count - a_start];
        int[] b = new int[b_count - b_start];

        StringBuffer a_buffer = new StringBuffer();
        StringBuffer b_buffer = new StringBuffer();
        for (int ix = a_start, index = 0, len = a_count; ix < len; ++ix, ++index) {
            a[index] = ix;
        }
        for (int ix = b_start, index = 0, len = b_count; ix < len; ++ix, ++index) {
            b[index] = ix;
        }

        shuffle(a);
        shuffle(b);

        for (int i : a) {
            a_buffer.append(i);
            a_buffer.append(",");
        }

        for (int i : b) {
            b_buffer.append(i);
            b_buffer.append(",");
        }
        System.out.println(a_buffer.toString());
        System.out.println(b_buffer.toString());
        System.out.println("OK");
        for (; ; ) {
            Thread.sleep(1000);
        }
    }


    public static void shuffle(int[] nums) {
        Random random = new Random();
        for (int ix = 0, len = 1000000; ix < len; ++ix) {
            int r1 = random.nextInt(nums.length);
            int r2 = random.nextInt(nums.length);
            int temp = nums[r1];
            nums[r1] = nums[r2];
            nums[r2] = temp;
        }
    }
}
