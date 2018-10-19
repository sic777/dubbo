/**
 * @apiDefine SINGLE
 *
 * @apiSuccess (成功说明) {number} code 状态码,<code>非0时均为错误状态</code>
 * @apiSuccess (成功说明) {string} message 提示信息
 * @apiSuccess (成功说明) {json} data 响应数据对象
 * 
 * @apiHeader (Request Header) {string} [Content-Type=application/json] 内容类型
 * @apiErrorExample {json} 失败示例
 * {
 *   "code" : 100000000,
 *   "message" : "error message",
 *   "data" : null
 * }
 *
 * @apiError (失败说明) {number} code 状态码,<code>非0时均为错误状态</code>
 * @apiError (失败说明) {string} message 提示信息
 * @apiError (失败说明) {json} data 响应数据对象(无)
 *
 */

/**
 * @apiDefine LIST
 *
 * @apiSuccess (成功说明) {number} code 状态码,<code>非0时均为错误状态</code>
 * @apiSuccess (成功说明) {string} message 提示信息
 * @apiSuccess (成功说明) {json} data 响应数据对象
 * @apiSuccess (成功说明) {number} data.count 数据总量
 * @apiSuccess (成功说明) {array} data.list 对象数组
 *
 * @apiHeader (Request Header) {string} [Content-Type=application/json] 内容类型
 * @apiErrorExample {json} 失败示例
 * {
 *   "code" : 100000000,
 *   "message" : "error message",
 *   "data" : null
 * }
 *
 * @apiError (失败说明) {number} code 状态码,<code>非0时均为错误状态</code>
 * @apiError (失败说明) {string} message 提示信息
 * @apiError (失败说明) {json} [data] 响应数据对象(无)
 *
 */


/**
 * @apiDefine RESPONSE_NULL
 *
 * @apiSuccessExample {json} 成功示例
 * {
 *   "code" : 0,
 *   "message" : "OK",
 *   "data" : null
 * }
 *
 */


