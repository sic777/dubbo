package com.sic777.utils.generator;


import com.sic777.utils.proguard.NoProguard;

/**
 * <p>全球唯一ID生成器
 * <br>
 * 使用这套算法必须调用setServerIndex进行初始化
 *
 * @author sic777
 * @since 0.0.1
 */
@NoProguard
public class GuidManager {
    private static final GuidManager singleton = new GuidManager();

    /**
     * 时间戳，32位
     */
    private int stdSecond;
    /**
     * 占16位,标志一个服务器
     */
    private short serverIndex = -1;
    /**
     * 低位区段占了9位
     */
    private int guidLowIndex = 0;

    public static GuidManager instance() {
        return singleton;
    }

    private GuidManager() {
        stdSecond = (int) (System.currentTimeMillis() / 1000);
    }

    private void update(int tempSecond) {
        if (tempSecond > stdSecond) {
            stdSecond = tempSecond;
            guidLowIndex = 0;
        }
    }

    /**
     * 加锁使用
     *
     * @param guidHigh GUID类型，保证每个生产的hex都是16位字符,建议在外部程序使用枚举定义，然后传入ID值
     *                 默认起始为8，8为系统保留序号，所以从9开始
     * @return
     */
    public synchronized long generateGuid(long guidHigh) {
        int second = (int) (System.currentTimeMillis() / 1000);
        update(second);
        if (guidLowIndex >= 250) {
            ++stdSecond;
            guidLowIndex = 0;
        }
        long guid = guidHigh << (64 - 7);
        long _serverIndex = serverIndex;
        guid |= (_serverIndex << (64 - 7 - 16));
        long _stdSecond = stdSecond;
        guid |= (_stdSecond << (64 - 7 - 16 - 32));
        long _guidLowIndex = guidLowIndex;
        guid |= _guidLowIndex;
        ++guidLowIndex;
        return guid;
    }

    public void setServerIndex(short serverIndex) {
        this.serverIndex = serverIndex;
    }
}
