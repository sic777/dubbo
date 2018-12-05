package com.sic777.restful.base.constants;

import com.sic777.common.utils.proguard.NoProguard;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
@NoProguard
public class HttpConstants {

    public static final String OPTIONS_METHOD = "OPTIONS";

    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    public static final String HEADER_KEY_CONNECTION = "Connection";
    public static final String HEADER_VALUE_CONNECTION = "keep-alive";

    public static final String HEADER_KEY_ALLOW_METHODS = "Access-Control-Allow-Methods";
    public static final String HEADER_VALUE_ALLOW_METHODS = "GET, POST, PUT, DELETE, OPTIONS";

    public static final String HEADER_KEY_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String HEADER_VALUE_ALLOW_HEADERS = "Access-Token,Content-Type";

    public static final String HEADER_KEY_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    public static final String HEADER_VALUE_ALLOW_CREDENTIALS = "true";

    public static final String HEADER_KEY_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

    public static final String ALL_ORIGIN = "*";

    public static final String ORIGIN = "Origin";
}
