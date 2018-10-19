/************************************************************************/
/***************************** 获取错误码列表 *****************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {get} /error-code 获取错误码列表
 * @apiName ERROR-CODE
 * @apiGroup OTHER
 * @apiDescription 获取错误码列表
 *
 * @apiPermission ANYBODY
 *
 * @apiUse LIST
 * @apiSuccessExample {json} 成功示例
 * {
 *     "code":0,
 *     "message":"OK",
 *     "data":{
 *       "list":[{
 *              "code":"错误码",
 *              "message":"描述信息"
 *          }],
 *          "count":100
 *     }
 * }
 * @apiSuccess (成功说明) {string} data.list 数据
 * @apiSuccess (成功说明) {string} data.list.code 错误码
 * @apiSuccess (成功说明) {string} data.list.message 错误信息
 *
 */

/************************************************************************/
/**************************** 发送短信验证码 ******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {post} /sms/validateCode/{phone} 发送手机验证码
 * @apiName SEND-VALIDATE-CODE
 * @apiGroup OTHER
 * @apiDescription 请求发送一封手机验证码的短信，手机验证码有效期是5分钟。
 *
 * @apiParam (Path Valiable) {string} phone 手机号码
 *
 * @apiUse ACK
 * @apiPermission ANYBODY
 *
 * @apiUse SINGLE
 * @apiUse RESPONSE_NULL
 *
 */
