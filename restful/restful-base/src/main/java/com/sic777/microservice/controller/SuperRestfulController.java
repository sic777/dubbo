package com.sic777.microservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.sic777.microservice.constants.MicroConstants;
import com.sic777.microservice.exception.*;
import com.sic777.microservice.exception.error.RESTFUL_ERROR;
import com.sic777.microservice.utils.RestValidateUtil;

import java.util.List;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public abstract class SuperRestfulController {

    /**
     * 获取当前accessToken对应的权限
     *
     * @return
     */
    protected final int getPermission() {
        return this.getAccessTokenData().getIntValue(MicroConstants.PERMISSION_FLAG);
    }

    /**
     * 获取accessToken字符串
     *
     * @return
     */
    protected final String getAccessTokenString() {
        return this.getAccessTokenData().getString(MicroConstants.ACCESS_TOKEN_FLAG);
    }

    /**
     * 获取accessToken缓存数据和接口权限
     *
     * @return
     */
    protected abstract JSONObject getAccessTokenData();


    /**
     * 校验对象不为null
     *
     * @param obj
     * @param key
     * @throws Rest400Exception
     */
    protected final void funcValidateNotNull(Object obj, String key) throws Rest400Exception {
        RestValidateUtil.objectNotNull(obj, key);
    }

    /**
     * 校验字符串不为空
     *
     * @param str
     * @param key
     * @throws Rest400Exception
     */
    protected final void funcValidateNotEmpty(String str, String key) throws Rest400Exception {
        RestValidateUtil.stringNotEmpty(str, key);
    }


    /**
     * 构造响应列表对象
     *
     * @param dataList
     * @param count
     * @return
     */
    protected final JSONObject generateReturnList(List<?> dataList, int count) {
        JSONObject js = new JSONObject();
        js.put(MicroConstants.COUNT_FLAG, count);
        js.put(MicroConstants.LIST_FLAG, dataList);
        return js;
    }

    /**
     * 构造响应id
     *
     * @param idKey
     * @param idValue
     * @return
     */
    protected final JSONObject generateReturnId(String idKey, Object idValue) {
        JSONObject js = new JSONObject();
        js.put(idKey, idValue);
        return js;
    }

    /**
     * 构造默认的响应id,key为"id"
     *
     * @param idValue
     * @return
     */
    protected final JSONObject generateDefaultReturnId(Object idValue) {
        JSONObject js = new JSONObject();
        js.put("id", idValue);
        return js;
    }

    /**
     * 响应200
     * <p>说明：</p>
     * <p>默认以Rest200异常拦截的方式返回数据</p>
     * <p>上层框架可以根据不同的框架重写该方法选择返回200的方式</p>
     *
     * @param obj
     */
    protected void rest200(Object obj) {
        throw new Rest200(obj);
    }

    /**
     * 抛出400异常
     *
     * @param code
     * @param msg
     */
    protected final void rest400(long code, String msg) {
        throw new Rest400Exception(code, msg);
    }

    /**
     * 抛出403异常
     *
     * @param code
     * @param msg
     */
    protected final void rest403(long code, String msg) {
        throw new Rest403Exception(code, msg);
    }

    /**
     * 抛出404异常
     *
     * @param code
     * @param msg
     */
    protected final void rest404(long code, String msg) {
        throw new Rest404Exception(code, msg);
    }


    private void rest400(RESTFUL_ERROR error, Object... format) {
        throw new Rest400Exception(error, format);
    }

    private void rest404(RESTFUL_ERROR error, Object... format) {
        throw new Rest404Exception(error, format);
    }

    /**
     * 参数校验异常(400异常)
     *
     * @param details %s 详细信息,param invalid,details:'%s'
     */
    protected final void exceptionParamInvalid(String details) {
        this.rest400(RESTFUL_ERROR.PARAM_INVALID, details);
    }

    /**
     * key或者对象不存在异常(400异常)
     *
     * @param key 关键字
     */
    protected final void exceptionObjectOrKeyNull(String key) {
        this.rest400(RESTFUL_ERROR.OBJECT_NULL, key);
    }

    /**
     * 值为空异常(400异常)
     *
     * @param key 关键字
     */
    protected final void exceptionValueEmpty(String key) {
        this.rest400(RESTFUL_ERROR.VALUE_EMPTY, key);
    }

    /**
     * 值为Null异常(400异常)
     *
     * @param key 关键字
     */
    protected final void exceptionValueNull(String key) {
        this.rest400(RESTFUL_ERROR.VALUE_NULL, key);
    }

    /**
     * 资源未找到异常(404异常)
     * @param details
     */
    protected final void exceptionResourceNotFound(String details) {
        this.rest404(RESTFUL_ERROR.RESOURCE_NOT_FOUND, details);
    }

}
