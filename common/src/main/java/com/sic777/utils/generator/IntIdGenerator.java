package com.sic777.utils.generator;

import java.io.*;
import java.nio.charset.Charset;


/**
 * <p>随机32位整形，每个数字2千万以上
 *
 * @author sic777
 * @since 0.0.1
 * @deprecated
 */
public class IntIdGenerator implements IGenerator<Integer> {
    private static final IntIdGenerator singleton = new IntIdGenerator();

    private static final int A_LENGTH = 180000;
    private static final int B_LENGTH = 9000;

    /**
     * 最大支持的ID数量，不能大于等于这个值，约17亿
     */
    private static final int MAX_ID = 1620000000;

    private int[] A_TABLE;
    private int[] B_TABLE;

    public static final IntIdGenerator instance() {
        return singleton;
    }

    private IntIdGenerator() {
    }

    public void init() {

        String text = read();
        String[] texts = text.split("\n");
        String a_str = texts[0];
        String b_str = texts[1];

        String[] a_str_arr = a_str.split(",");
        String[] b_str_arr = b_str.split(",");
        if (a_str_arr.length != A_LENGTH || b_str_arr.length != B_LENGTH) {
            System.err.print("ID_Store file error");
            System.exit(-1);
        }

        A_TABLE = new int[A_LENGTH];
        for (int ix = 0, len = A_LENGTH; ix < len; ++ix) {
            A_TABLE[ix] = Integer.valueOf(a_str_arr[ix]);
        }
        B_TABLE = new int[B_LENGTH];
        for (int ix = 0, len = B_LENGTH; ix < len; ++ix) {
            B_TABLE[ix] = Integer.valueOf(b_str_arr[ix]);
        }
    }

    @Override
    public Integer next() throws Exception {
        return next(1)[0];
    }

    /**
     * @param batchNum
     * @return
     * @throws Exception
     */
    public Integer[] next(int batchNum) throws Exception {
//        int id_index = MIdIndexService.instance().incr(batchNum);
//        TODO 数据库递增索引表
        int id_index = 2;
        if (id_index > MAX_ID) {
            throw new UnsupportedOperationException("ID MAX");
        }
        Integer[] result = new Integer[batchNum];
        for (int ix = id_index - batchNum, index = 0, len = id_index; ix < len; ++ix, ++index) {
            int a_index = ix / B_LENGTH;
            int b_index = ix % B_LENGTH;
            result[index] = Integer.parseInt(A_TABLE[a_index] + "" + B_TABLE[b_index]);
        }
        return result;
    }

    public String read() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("ID_STORE");
        try {
            String text;
            StringBuilder sb = new StringBuilder();
            BufferedReader input = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
            while ((text = input.readLine()) != null) {
                sb.append(text);
                sb.append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}
