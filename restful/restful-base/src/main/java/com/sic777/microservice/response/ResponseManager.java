package com.sic777.microservice.response;

import com.alibaba.fastjson.JSONObject;
import com.sic777.microservice.constants.MicroConstants;
import com.sic777.microservice.response.exception.error.ExceptionCode;
import com.sic777.microservice.response.exception.error.NotFoundException;
import com.sic777.microservice.response.exception.error.ParamException;
import com.sic777.utils.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Enumeration;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
            logger.info("set response body:" + responseBodyType);
        }
    }

    /**
     * @return
     */
    public final ResponseBodyType getResponseBodyType() {
        return this.responseBodyType;
    }


    /**
     * 获取成功的响应体
     *
     * @param obj
     * @return
     */
    public final JSONObject getSuccessResponseBody(Object obj) {
        JSONObject resp = new JSONObject();
        switch (responseBodyType) {
            case FIXED:
                resp.put(MicroConstants.CODE_FLAG, HttpStatus.OK.value());
                resp.put(MicroConstants.DATA_FLAG, obj);
                resp.put(MicroConstants.MSG_FLAG, HttpStatus.OK.getReasonPhrase());
                break;
            case DYNAMIC:
                resp.put(MicroConstants.DATA_FLAG, obj);
                break;
        }
        return resp;
    }

    /**
     * 构造响应列表对象
     *
     * @param dataList
     * @param count
     * @return
     */
    public final JSONObject generateReturnList(List<?> dataList, int count) {
        JSONObject js = new JSONObject();
        js.put(MicroConstants.COUNT_FLAG, count);
        js.put(MicroConstants.LIST_FLAG, dataList);
        return getSuccessResponseBody(js);
    }

    /**
     * 构造响应id
     *
     * @param idKey
     * @param idValue
     * @return
     */
    public final JSONObject generateReturnId(String idKey, Object idValue) {
        JSONObject js = new JSONObject();
        js.put(idKey, idValue);
        return getSuccessResponseBody(js);
    }

    /**
     * 构造默认的响应id,key为"id"
     *
     * @param idValue
     * @return
     */
    public final JSONObject generateDefaultReturnId(Object idValue) {
        JSONObject js = new JSONObject();
        js.put("id", idValue);
        return getSuccessResponseBody(js);
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
     * 获取抛出错误的响应体
     *
     * @param error
     * @param format
     * @return
     */
    public final JSONObject getErrorResponseBody(Enumeration.Value error, Object... format) {
        return this.getErrorResponseBody(error.id(), String.format(error.toString(), format));
    }


    /**
     * 抛异常
     *
     * @param restException
     * @throws AbstractRestException
     */
    private void throwRestException(AbstractRestException restException) throws AbstractRestException {
        throw restException;
    }

    /**
     * 抛503异常
     *
     * @param throwable
     * @throws AbstractRestException
     */
    public void throwRest503Exception(Throwable throwable) throws AbstractRestException {
        this.throwRestException(new Rest503Exception(throwable));
    }

    /**
     * 抛异常
     *
     * @param code
     * @param message
     * @param exceptionType
     * @param throwable
     */
    public void throwRestException(long code, String message, ExceptionType exceptionType, Throwable throwable) {
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
            default:
                this.throwRestException(new Rest503Exception(throwable));
                break;
        }
    }

    /**
     * 抛异常
     *
     * @param code
     * @param message
     * @param exceptionType
     */
    public void throwRestException(long code, String message, ExceptionType exceptionType) {
        this.throwRestException(code, message, exceptionType, null);
    }

    /**
     * 抛异常
     *
     * @param error
     * @param format
     */
    public void throwRestException(Enumeration.Value error, Object... format) {
        this.throwRestException(error.id(), String.format(error.toString(), format), getExceptionType(error));
    }

    /**
     * 获取异常类型
     *
     * @param error
     * @return
     */
    public ExceptionType getExceptionType(Enumeration.Value error) {
        Object obj = ExceptionCode.parse(error.id());
        ExceptionType exceptionType;
        if (obj instanceof ExceptionCode.ParamException) {//400
            exceptionType = ExceptionType.EXCEPTION_400;
        } else if (obj instanceof ExceptionCode.AuthenticationException) {//403
            exceptionType = ExceptionType.EXCEPTION_403;
        } else if (obj instanceof ExceptionCode.NotFoundException) {//404
            exceptionType = ExceptionType.EXCEPTION_404;
        } else {//503
            exceptionType = ExceptionType.EXCEPTION_503;
        }
        return exceptionType;
    }

    /**
     * 资源未找到异常
     *
     * @param details
     */
    public final void resourceNotFoundException(String details) {
        this.throwRestException(NotFoundException.RESOURCE_NOT_FOUND(), details);
    }

    /**
     * 参数校验异常
     *
     * @param details %s 详细信息,param invalid,details:'%s'
     */
    public final void paramInvalidException(String details) {
        this.throwRestException(ParamException.PARAM_INVALID(), details);
    }

    /**
     * 校验对象不为null,为null抛出异常
     *
     * @param obj
     * @param key
     */
    public final void funcValidateObjectNotNull(Object obj, String key) {
        if (ObjectUtil.isNull(obj)) {
            this.throwObjectNullException(key);
        }
    }

    /**
     * 抛出对象为null异常
     *
     * @param key
     */
    public final void throwObjectNullException(String key) {
        this.throwRestException(ParamException.OBJECT_NULL(), key);
    }


    /**
     * 校验值不为null,null则抛出异常
     *
     * @param value
     * @param key
     */
    public final void funcValidateValueNotNull(Object value, String key) {
        if (ObjectUtil.isNull(value)) {
            this.throwValueNullException(key);
        }
    }

    /**
     * 抛出值为null抛出异常
     *
     * @param key
     */
    public final void throwValueNullException(String key) {
        this.throwRestException(ParamException.VALUE_NULL(), key);
    }

    /**
     * 校验值不为空,为空抛出异常
     *
     * @param value
     * @param key
     */
    public final void funcValidateValueNotEmpty(Object value, String key) {
        if (ObjectUtil.isNull(value)) {
            this.throwValueEmptyException(key);
        }
    }

    /**
     * 抛出参数为空异常
     *
     * @param key
     */
    public final void throwValueEmptyException(String key) {
        this.throwRestException(ParamException.VALUE_EMPTY(), key);
    }

}
