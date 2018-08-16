package com.sic777.microservice.response.exception.error

import com.sic777.common.constants.{BaseConstant, ErrorMsg}

/**
  * <p>错误码</p>
  *
  * @author Zhengzhenxie
  * @version v1.0
  * @since 2018-06-15
  */
object ExceptionCode {

  /**
    * <p>
    * '参数校验错误'异常错误码
    * 说明：使用者的错误码从11000开始，10000~10999位为系统保留
    * </p>
    *
    * @author Zhengzhenxie
    * @version v1.0
    * @since 2018-06-15
    */
  class ParamException extends Enumeration {
    /**
      * 参数校验错误
      */
    val PARAM_INVALID: Value = Value(10000, ErrorMsg.PARAM_INVALID)
    /**
      * key或者对象不存在
      */
    val OBJECT_NULL: Value = Value(10001, ErrorMsg.OBJECT_NULL)
    /**
      * 参数值为空
      */
    val VALUE_EMPTY: Value = Value(10002, ErrorMsg.VALUE_EMPTY)
    /**
      * 参数值为NULL
      */
    val VALUE_NULL: Value = Value(10003, ErrorMsg.VALUE_NULL)

  }

  /**
    * <p>
    * '认证/权限错误'异常错误码
    * 说明：使用者的错误码从21000开始，20000~20999位为系统保留
    * </p>
    *
    * @author Zhengzhenxie
    * @version v1.0
    * @since 2018-06-15
    */
  class AuthenticationException extends Enumeration {
    /**
      * 禁止访问
      */
    val INVALID_ACCESS: Value = Value(20000, ErrorMsg.INVALID_ACCESS)

    /**
      * Access-Token 为空
      */
    val ACCESS_TOKEN_VALUE_EMPTY: Value = Value(20001, String.format("'%s' value must not be empty", BaseConstant.ACCESS_TOKEN_FLAG))
  }

  /**
    * <p>
    * '资源未找到'异常错误码
    * 说明：使用者的错误码从31000开始，30000~30999位为系统保留
    * </p>
    *
    * @author Zhengzhenxie
    * @version v1.0
    * @since 2018-06-15
    */
  class NotFoundException extends Enumeration {
    /**
      * 资源未找到
      */
    val RESOURCE_NOT_FOUND: Value = Value(30000, "%s")
    /**
      * URI找不到
      */
    val URI_NOT_FOUND: Value = Value(30001, "requested path: '%s' not found")
  }


  /**
    * 根据错误码返回错误类对象
    *
    * @param code
    * @return
    */
  def parse(code: Int): Object = {
    if (code >= 10000 && code <= 19999) {
      ParamException
    } else if (code >= 20000 && code <= 29999) {
      AuthenticationException
    } else if (code >= 30000 && code <= 39999) {
      NotFoundException
    } else {
      null
    }
  }
}