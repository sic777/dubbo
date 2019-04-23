package com.sic777.common.utils.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;


/**
 * <p>防痴呆检测工具
 *
 * @author sic777
 * @since 0.0.1
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
}