/************************************************************************/
/******************************* 修改密码 ********************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {post} /user/password/reset 修改密码
 * @apiName USER-PWD-RESET
 * @apiGroup USER_PWD
 * @apiDescription 修改用户的密码。
 *
 * @apiParamExample {json} Request Body示例
 *
 * {
 *     "oldPassword" : "旧密码",
 *     "newPassword" : "新密码"
 * }
 *
 * @apiParam (Request Body说明) {string} noldPassword  旧密码，MD5加密
 * @apiParam (Request Body说明) {string} newPassword  新密码，MD5加密
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse SINGLE
 * @apiUse RESPONSE_NULL
 *
 */

/************************************************************************/
/******************************* 初始化密码 *******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {post} /user/password/init 初始化密码
 * @apiName USER-PWD-INIT
 * @apiGroup USER_PWD
 * @apiDescription 初始化密码，只能初始化一次（如果忘记密码，后续会提供一个重置密码的接口）
 *
 * @apiParamExample {json} Request Body示例
 *
 * {
 *     "password" : "密码"
 * }
 *
 * @apiParam (Request Body说明) {string} password  密码，MD5加密
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse SINGLE
 * @apiUse RESPONSE_NULL
 *
 */

