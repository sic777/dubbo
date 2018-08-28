package com.sic777.restful.base.response;

import com.sic777.common.constants.ErrorMsg;
import com.sic777.restful.base.controller.SuperRestfulController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sic777.restful.base.constants.MicroConstants;
import com.sic777.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sic777.common.exception.ExceptionType;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import static com.sic777.common.exception.ExceptionType.ParamExceptionType.*;
import static com.sic777.common.exception.ExceptionType.NotFoundExceptionType.*;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-07-03
 */
public class ResponseManager {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static ResponseManager singleton = new ResponseManager();

    public static ResponseManager instance() {
        return singleton;
    }

    private ResponseManager() {
    }

    private ResponseBodyType responseBodyType = ResponseBodyType.FIXED;

    private AtomicBoolean isInit = new AtomicBoolean(false);

    /**
     * 初始化
     *
     * @param responseBodyType
     */
    public final void init(ResponseBodyType responseBodyType) {
        if (isInit.compareAndSet(false, true)) {
            this.responseBodyType = responseBodyType;
            logger.info("set response body type:" + responseBodyType);
        }
    }

    /**
     * 获取响应体类型
     *
     * @return
     */
    public final ResponseBodyType getResponseBodyType() {
        return this.responseBodyType;
    }

    /**
     * 返回成功(对象为空)
     *
     * @param restfulController
     * @throws Exception
     */
    public final void success(SuperRestfulController restfulController) throws Exception {
        success(restfulController, null);
    }

    /**
     * 返回成功(任意对象)
     *
     * @param restfulController
     * @param object
     * @throws Exception
     */
    public final void success(SuperRestfulController restfulController, Object object) throws Exception {
        Object response = null;
        switch (responseBodyType) {
            case FIXED:
                JSONObject resp = new JSONObject();
                resp.put(MicroConstants.CODE_FLAG, 0);
                resp.put(MicroConstants.DATA_FLAG, object);
                resp.put(MicroConstants.MSG_FLAG, "OK");
                response = resp;
                break;
            default:
                if (null != object) {
                    response = object instanceof JSON ? object : JSON.toJSON(object);
                }
        }
        if (null != response) {
            restfulController.success(response);
        }
    }

    /**
     * 返回成功(集合)
     *
     * @param restfulController
     * @param dataCollections   数据集合
     * @param count             总数
     * @throws Exception
     */
    public final void successCollections(SuperRestfulController restfulController, Collection<?> dataCollections,
                                         long count) throws Exception {
        JSONObject js = new JSONObject();
        js.put(MicroConstants.COUNT_FLAG, count);
        js.put(MicroConstants.LIST_FLAG, dataCollections);
        success(restfulController, js);
    }

    /**
     * 返回成功("键=值")
     *
     * @param restfulController
     * @param key               返回的键
     * @param value             返回的值
     * @throws Exception
     */
    public final void successKV(SuperRestfulController restfulController, String key, Object value) throws
            Exception {
        JSONObject js = new JSONObject();
        js.put(key, value);
        success(restfulController, js);
    }

    /**
     * 返回成功("id=值")
     *
     * @param restfulController
     * @param idValue           id值
     * @throws Exception
     */
    public final void successId(SuperRestfulController restfulController, Object idValue) throws Exception {
        JSONObject js = new JSONObject();
        js.put(MicroConstants.ID_FLAG, idValue);
        success(restfulController, js);
    }


    /**
     * 获取抛出错误的响应体
     *
     * @param code
     * @param msg
     * @return
     */
    public final JSONObject getErrorResponseBody(long code, String msg) {
        JSONObject json = new JSONObject();
        json.put(MicroConstants.CODE_FLAG, code);
        json.put(MicroConstants.MSG_FLAG, msg);
        switch (responseBodyType) {
            case DYNAMIC:
                JSONObject resp = new JSONObject();
                resp.put(MicroConstants.ERROR_FLAG, json);
                return resp;
            default:
                return json;
        }
    }


    /**
     * 校验对象不为null,为null抛出异常(自定义错误码)
     *
     * @param obj
     * @param code
     * @param key
     */
    public final void funcValidateObjectNotNull(Object obj, long code, String key) {
        if (StringUtil.isNull(obj)) {
            this.throwRestException(code, String.format(ErrorMsg.OBJECT_NULL, key));
        }
    }


    /**
     * 批量校验对象不为null,为null抛出异常(自定义错误码)
     *
     * @param objects
     * @param codes
     * @param keys
     */
    public final void funcValidateObjectNotNull(Object[] objects, long[] codes, String[] keys) {
        for (int i = 0, len = objects.length; i < len; i++) {
            this.funcValidateObjectNotNull(objects[i], codes[i], keys[i]);
        }
    }

    /**
     * 批量校验对象不为null,为null抛出异常(系统错误码)
     *
     * @param objects
     * @param keys
     */
    public final void funcValidateObjectNotNull(Object[] objects, String[] keys) {
        for (int i = 0, len = objects.length; i < len; i++) {
            funcValidateObjectNotNull(objects[i], OBJECT_NULL.getId(), keys[i]);
        }
    }

    /**
     * 校验对象不为null,为null抛出异常(系统错误码)
     *
     * @param obj
     * @param key
     */
    public final void funcValidateObjectNotNull(Object obj, String key) {
        funcValidateObjectNotNull(obj, OBJECT_NULL.getId(), key);
    }


    /**
     * 校验值不为null,null则抛出异常(自定义错误码)
     *
     * @param value
     * @param code
     * @param key
     */
    public final void funcValidateValueNotNull(Object value, long code, String key) {
        if (StringUtil.isNull(value)) {
            this.throwRestException(code, String.format(ErrorMsg.VALUE_NULL, key));
        }
    }

    /**
     * 批量校验值不为null,null则抛出异常(系统错误码)
     *
     * @param values
     * @param keys
     */
    public final void funcValidateValueNotNull(Object[] values, String[] keys) {
        for (int i = 0, len = values.length; i < len; i++) {
            funcValidateValueNotNull(values[i], VALUE_NULL.getId(), keys[i]);
        }
    }

    /**
     * 校验值不为null,null则抛出异常(系统错误码)
     *
     * @param value
     * @param key
     */
    public final void funcValidateValueNotNull(Object value, String key) {
        this.funcValidateValueNotNull(value, VALUE_NULL.getId(), key);
    }


    /**
     * 批量校验值不为null,null则抛出异常(自定义错误码)
     *
     * @param values
     * @param codes
     * @param keys
     */
    public final void funcValidateValueNotNull(Object[] values, long[] codes, String[] keys) {
        for (int i = 0, len = values.length; i < len; i++) {
            this.funcValidateObjectNotNull(values[i], codes[i], keys[i]);
        }
    }


    /**
     * 校验值不为空,为空抛出异常(自定义错误码)
     *
     * @param value
     * @param code
     * @param key
     */
    public final void funcValidateValueNotEmpty(Object value, long code, String key) {
        if (StringUtil.isEmpty(value)) {
            this.throwRestException(code, String.format(ErrorMsg.VALUE_EMPTY, key));
        }
    }

    /**
     * 批量校验值不为空,为空抛出异常(自定义错误码)
     *
     * @param values
     * @param codes
     * @param keys
     */
    public final void funcValidateValueNotEmpty(Object[] values, long[] codes, String[] keys) {
        for (int i = 0, len = values.length; i < len; i++) {
            this.funcValidateValueNotEmpty(values[i], codes[i], keys[i]);
        }
    }

    /**
     * 批量校验值不为null,null则抛出异常(系统错误码)
     *
     * @param values
     * @param keys
     */
    public final void funcValidateValueNotEmpty(Object[] values, String[] keys) {
        for (int i = 0, len = values.length; i < len; i++) {
            funcValidateValueNotEmpty(values[i], VALUE_EMPTY.getId(), keys[i]);
        }
    }

    /**
     * 校验值不为空,为空抛出异常(系统错误码)
     *
     * @param value
     * @param key
     */
    public final void funcValidateValueNotEmpty(Object value, String key) {
        this.funcValidateValueNotEmpty(value, VALUE_EMPTY.getId(), key);
    }

    /**
     * 参数校验异常(系统错误码)
     *
     * @param details param invalid,details:'%s'
     */
    public final void throwParamInvalidException(String details) {
        this.throwRestException(PARAM_INVALID.getId(), String.format(PARAM_INVALID.getMsg(), details));
    }

    /**
     * 资源未找到异常(系统错误码)
     *
     * @param msg
     */
    public final void throwResourceNotFoundException(String msg) {
        this.throwRestException(RESOURCE_NOT_FOUND.getId(), msg);
    }

    /**
     * 抛出Restful503异常
     *
     * @param throwable
     * @throws AbstractRestException
     */
    public final void throwRest503Exception(Throwable throwable) throws AbstractRestException {
        this.throwRestException(new Rest503Exception(throwable));
    }

    /**
     * 抛出Restful异常
     *
     * @param code
     * @param message
     */
    public final void throwRestException(long code, String message) {
        this.throwRestException(code, message, null);
    }

    /**
     * 抛出Restful异常
     *
     * @param code
     * @param message
     * @param throwable
     */
    public final void throwRestException(long code, String message, Throwable throwable) {
        ExceptionType exceptionType = ExceptionType.parse(code);
        switch (exceptionType) {
            case EXCEPTION_200:
                this.throwRestException(new Rest200Exception(code, message));
                break;
            case EXCEPTION_400:
                this.throwRestException(new Rest400Exception(code, message));
                break;
            case EXCEPTION_403:
                this.throwRestException(new Rest403Exception(code, message));
                break;
            case EXCEPTION_404:
                this.throwRestException(new Rest404Exception(code, message));
                break;
            case EXCEPTION_405:
                this.throwRestException(new Rest405Exception(code, message));
                break;
            default:
                this.throwRestException(new Rest503Exception(throwable));
                break;
        }
    }

    /**
     * 抛出Restful异常
     *
     * @param restException
     * @throws AbstractRestException
     */
    private void throwRestException(AbstractRestException restException) throws AbstractRestException {
        throw restException;
    }
}
