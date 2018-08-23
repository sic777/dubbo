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
    * '参数校验错误'异常错误码(400...)
    * 说明：使用者的错误码从11000xxxxx开始，10000xxxxx~10999xxxxx位为系统保留(xxxxx可以用作系统标识,也可为空)
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
    * '认证/权限错误'异常错误码(403...)
    * 说明：使用者的错误码从21000xxxxx开始，20000xxxxx~20999xxxxx位为系统保留(xxxxx可以用作系统标识,也可为空)
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
    val ACCESS_TOKEN_VALUE_EMPTY: Value = Value(20001, String.format(ErrorMsg.VALUE_EMPTY, BaseConstant.ACCESS_TOKEN_FLAG))
  }

  /**
    * <p>
    * '资源未找到'异常错误码(404...)
    * 说明：使用者的错误码从31000xxxxx开始，30000xxxxx~30999xxxxx位为系统保留(xxxxx可以用作系统标识,也可为空)
    * </p>
    *
    * @author Zhengzhenxie
    * @version v1.0
    * @since 2018-06-15
    */
  class NotFoundException extends Enumeration {
    /**
      * URL未找到
      */
    val URL_NOT_FOUND: Value = Value(30000, ErrorMsg.URL_NOT_FOUND)
    /**
      * 资源未找到
      */
    val RESOURCE_NOT_FOUND: Value = Value(30001)
  }

  /**
    * <p>
    * '不支持的操作'异常错误码(405...)
    * 说明：使用者的错误码从41000xxxxx开始，40000xxxxx~40999xxxxx位为系统保留(xxxxx可以用作系统标识,也可为空)
    * </p>
    *
    * @author Zhengzhenxie
    * @version v1.0
    * @since 2018-08-23
    */
  class NotAllowException extends Enumeration {
    /**
      * 请求方法不支持
      */
    val METHOD_NOT_ALLOW: Value = Value(40000, ErrorMsg.METHOD_NOT_ALLOWED)
  }

  /**
    * <p>
    * '服务器不可用'异常错误码(503...)
    * 说明：使用者的错误码从51000xxxxx开始，50000xxxxx~50999xxxxx位为系统保留(xxxxx可以用作系统标识,也可为空)
    * </p>
    *
    * @author Zhengzhenxie
    * @version v1.0
    * @since 2018-08-23
    */
  class ServiceUnavailableException extends Enumeration {
    /**
      * 服务不可用
      */
    val SERVICE_UNAVAILABLE: Value = Value(50000, ErrorMsg.SERVICE_EXCEPTION)
  }


  /**
    * 根据错误码返回错误类对象
    *
    * @param code
    * @return
    */
  def parse(code: Int): Object = {
    val codeStr = code.toString
    if (codeStr.startsWith("1") || codeStr.startsWith("400")) {
      ParamException
    } else if (codeStr.startsWith("2") || codeStr.startsWith("403")) {
      AuthenticationException
    } else if (codeStr.startsWith("3") || codeStr.startsWith("404")) {
      NotFoundException
    } else if (codeStr.startsWith("4") || codeStr.startsWith("405")) {
      NotAllowException
    } else {
      ServiceUnavailableException
    }
  }
}
