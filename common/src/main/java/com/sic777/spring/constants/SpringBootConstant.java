package com.sic777.spring.constants;


/**
 * <p>SpringBoot基础常量
 *
 * @author sic777
 * @since 0.0.1
 */
public class SpringBootConstant {
    /**
     * Spring在此框架下的默认扫描路径,不可覆盖
     */
    public final static String BASE_SPRING_SCAN_PACKAGE = "com.sic777";
    /**
     * 开发者自定义的Spring扫描路径,不可覆盖
     */
    public final static String ISV_SPRING_SCAN_PACKAGE = "${spring.scan}";
}
