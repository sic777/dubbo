package com.sic777.common.utils.classes;

import com.sic777.common.utils.container.ContainerGetter;

import java.util.List;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class ClassUtil {


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
