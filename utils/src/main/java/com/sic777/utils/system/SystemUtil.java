package com.sic777.utils.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>防痴呆检测工具</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-05-28
 */
public final class SystemUtil {
    private final static Logger logger = LoggerFactory.getLogger(SystemUtil.class);

    private SystemUtil() {
    }

    /**
     * 检测是否有重复的Class
     *
     * @param classes
     */
    public static void checkDuplicate(Class<?>... classes) {
        for (Class<?> clz : classes) {
            checkDuplicate(clz.getName().replace('.', '/') + ".class");
        }
    }

    /**
     * 校验资源是否重复
     *
     * @param paths 资源路径(可以是Class全路径也可以是ClassPath下的配置文件名称)
     */
    public static void checkDuplicate(String... paths) {
        try {
            for (String path : paths) {
                // 在ClassPath搜文件
                Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(path);
                Set<String> files = new HashSet<>();
                while (urls.hasMoreElements()) {
                    URL url = urls.nextElement();
                    if (url != null) {
                        String file = url.getFile();
                        if (file != null && file.length() > 0) {
                            files.add(file);
                        }
                    }
                }
                // 如果有多个，就表示重复
                if (files.size() > 1) {
                    logger.error("Duplicate class " + path + " in " + files.size() + " jar " + files);
                    System.exit(-1);
                }
            }
        } catch (Throwable e) { // 防御性容错
            logger.error(e.getMessage(), e);
            System.exit(-1);
        }
    }


    /**
     * 获取方法顶级调用者类名（类名为全路径）
     *
     * @return
     */
    public static String getTopCaller() {
        String[] rs = getCaller();
        return rs[rs.length - 1];
    }

    /**
     * 通过获取调用栈获取调用链类名（类名为全路径）
     * 排序：调用者的类名排在最后
     *
     * @return
     */
    public static String[] getCaller() {
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        int lent = stack.length;
        String[] rs = new String[lent];
        for (int i = 0; i < lent; i++) {
            StackTraceElement ste = stack[i];
            rs[i] = ste.getClassName();
        }
        return rs;
    }
}
