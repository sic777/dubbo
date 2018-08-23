/**
 * @apiDefine fixed_error
 *
 *
 * @apiErrorExample {json} 失败示例
 * {
 *   "code" : 21002,
 *   "message" : "error message",
 *   "data" : null
 * }
 *
 * @apiError (失败说明) {number} code 状态码,非0时均为错误状态
 * @apiError (失败说明) {string} message 提示信息
 * @apiError (失败说明) {json} data 响应数据对象(无)
 *
 */