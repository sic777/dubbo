package com.sic777.restful.base.response;

import com.sic777.utils.proguard.NoProguard;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-07-03
 */
@NoProguard
public enum ResponseBodyType {
    /**
     * 返回的数据格式:
     * {
     * "code" : "状态码",
     * "message" : "描述信息",
     * "data" : "响应数据"
     * }
     */
    FIXED,
    /**
     * 返回的数据格式:
     * 1.HTTP 200
     * {
     * "id" : "",
     * "xx" : "",
     * "xxx" : ""
     * }
     * <p>
     * <p>
     * 2.非HTTP 200
     * {
     * "error" :
     * {
     * "message" : "错误信息",
     * "code" : "错误码"
     * }
     * }
     */
    DYNAMIC
}
