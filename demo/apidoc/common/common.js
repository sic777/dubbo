/**
 * @apiDefine common
 *
 *
 * @apiErrorExample {json} 失败响应体示例:
 * {
 *   "code": 404****,
 *   "message":"错误提示信息"
 * }
 *
 * @apiError (失败响应体说明) {number} code 错误码 404****(参数异常) 403****(权限异常) 404****(资源不存在) 405****(请求类型错误) 503****(系统异常)
 * @apiError (失败响应体说明) {string} message 错误提示信息
 *
 */