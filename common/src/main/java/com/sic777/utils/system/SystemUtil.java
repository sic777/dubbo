package com.sic777.utils.system;

import com.sic777.utils.container.ContainerGetter;
import com.sic777.utils.proguard.NoProguard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * <p>防痴呆检测工具
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
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
        return getCaller(1);
    }

    /**
     * 获取方法调用者类名（类名为全路径）
     *
     * @param lastIndex 倒序下标，比如1表示最后一个，2表示倒数第二个...
     * @return
     */
    public static String getCaller(int lastIndex) {
        String[] rs = getCallers();
        return rs[rs.length - lastIndex];
    }

    /**
     * 通过获取调用栈获取调用链类名（类名为全路径）
     * 排序：调用者的类名排在最后
     *
     * @return
     */
    public static String[] getCallers() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        int lent = stack.length;
        String[] rs = new String[lent];
        for (int i = 0; i < lent; i++) {
            StackTraceElement ste = stack[i];
            rs[i] = ste.getClassName();
        }
        return rs;
    }

    /**
     * 获取所有的父类
     *
     * @param clz
     * @param classes
     * @return
     */
    private static List<Class<?>> getSuperClasses(Class<?> clz, List<Class<?>> classes) {
        if (clz != null) {
            Class<?> superClass = clz.getSuperclass();
            if (superClass != null) {
                classes.add(superClass);
                getSuperClasses(superClass, classes);
            }
        }
        return classes;
    }

    /**
     * 获取所有的父类
     *
     * @param clz
     * @return
     */
    public static List<Class<?>> getSuperClasses(Class<?> clz) {
        return getSuperClasses(clz, ContainerGetter.arrayList());
    }

    /**
     * 根据下标获取父类
     *
     * @param clz
     * @param lastIndex 倒序下标，比如1表示最后一个，2表示倒数第二个...
     * @return
     */
    public static Class<?> getSuperClass(Class<?> clz, int lastIndex) {
        List<Class<?>> rs = getSuperClasses(clz);
        return rs.get(rs.size() - lastIndex);
    }

    /**
     * 获取顶级的父类
     *
     * @param clz
     * @return
     */
    public static Class<?> getTopSuperClass(Class<?> clz) {
        return getSuperClass(clz, 1);
    }

    /**
     * 获取所有的父类名称
     *
     * @param clz
     * @return
     */
    public static List<String> getSuperClassNames(Class<?> clz) {
        List<String> rs = ContainerGetter.arrayList();
        List<Class<?>> classes = ContainerGetter.arrayList();
        List<Class<?>> ls = getSuperClasses(clz, classes);
        for (Class<?> l : ls) {
            rs.add(l.getName());
        }
        return rs;
    }

    /**
     * 根据下标获取父类名称
     *
     * @param clz
     * @param lastIndex 倒序下标，比如1表示最后一个，2表示倒数第二个...
     * @return
     */
    public static String getSuperClassName(Class<?> clz, int lastIndex) {
        List<String> rs = getSuperClassNames(clz);
        return rs.get(rs.size() - lastIndex);
    }

    /**
     * 获取顶级的父类名称
     *
     * @param clz
     * @return
     */
    public static String getTopSuperClassName(Class<?> clz) {
        return getSuperClassName(clz, 1);
    }
}