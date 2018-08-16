/**
 * @apiDefine dynamic_error
 *
 *
 * @apiErrorExample {json} 失败示例
 *
 *{
 * "error" : {
 *   "code" : 21002,
 *   "message" : "'name' must not be null"
 * }
 *}
 *
 * @apiError (失败说明) {object} error 错误对象
 * @apiError (失败说明) {number} error.code 错误码
 * @apiError (失败说明) {string} error.message 错误提示信息
 *
 */