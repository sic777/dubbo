package com.sic777.restful.base.constants;

import com.sic777.utils.ConfigureManager;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class ConfigureConstants {
    /**
     * 统计周期(ms),默认一小时
     */
    public static final long COUNTER_CYCLE = ConfigureManager.instance().getLong("counter.cycle", 3600000L);
}
