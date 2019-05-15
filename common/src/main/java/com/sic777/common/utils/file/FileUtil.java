package com.sic777.common.utils.file;

import java.io.*;

/**
 * <p>文件工具类
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class FileUtil {

    /**
     * 将InputStream写入本地文件
     *
     * @param destination 写入本地目录
     * @param input       输入流
     * @throws IOException
     */
    public static void writeToLocal(String destination, InputStream input) throws IOException {
        FileOutputStream downloadFile = new FileOutputStream(destination);
        try {
            int index;
            byte[] bytes = new byte[1024];
            while ((index = input.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
        } finally {
            downloadFile.close();
            if (null != input) {
                input.close();
            }
        }
    }

    /**
     * 根据本地文件路径获取InputStream
     *
     * @param destination
     * @return
     */
    public static InputStream getFromLocal(String destination) {
        try {
            return new FileInputStream(new File(destination));
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
