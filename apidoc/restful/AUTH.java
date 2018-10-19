/************************************************************************/
/******************************** 用户登录 ******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {post} /auth/user 用户登录
 * @apiName USER-AUTH
 * @apiGroup AUTH
 * @apiDescription 用户登录，返回token相关信息
 *
 * @apiParamExample {json} Request Body示例
 * {
 * "phone":"手机号码",
 * "password":"密码",
 * "validateCode":"验证码"
 * }
 *
 * @apiParam (Request Body说明) {string} phone  手机号码
 * @apiParam (Request Body说明) {string} password  密码，MD5加密
 * @apiParam (Request Body说明) {string} validateCode  验证码
 *
 * @apiUse ACK
 * @apiPermission ANYBODY
 *
 * @apiUse SINGLE
 * @apiSuccessExample {json} 成功示例
 * {
 *   "code": 0,
 *   "data": {
 *      "userId":123456,
 *      "accessToken": "MUNDRkIxNDJFOTlBOThBN0VERjU3OTZCMUExNUI1RTQ2QzI4NDIyRjYyNENFQkVFRUU3OTcyRTYwQTUwODBCNA==",
 *      "expireIn": 2592000
 *   }
 * }
 * @apiSuccess (成功说明) {long} data.expireIn=一个月 过期时间
 * @apiSuccess (成功说明) {string} data.accessToken 鉴权标识
 * @apiSuccess (成功说明) {long} data.userId 用户id
 *
 */

/************************************************************************/
/******************************* 三方登录 ********************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {post} /user/auth/third 三方登录
 * @apiName USER-AUTH-THIRD
 * @apiGroup AUTH
 * @apiDescription 三方登录
 *
 * @apiParamExample {json} Request Body示例
 *
 * {
 *     "openId":"APP在第三方平台登录成功返回的用户标识",
 *     "accessToken":"APP在第三方平台登录成功返回的用户凭证"
 *     "name":"第三方用户昵称",
 *     "type":"三方登录类型",
 *     "deviceToken":"设备Token",
 *     "deviceModel":"设备类型"
 * }
 *
 * @apiParam (Request Body说明) {string} openId APP在第三方平台登录成功返回的用户标识
 * @apiParam (Request Body说明) {string} accessToken APP在第三方平台登录成功返回的用户凭证
 * @apiParam (Request Body说明) {string} name 第三方用户昵称
 * @apiParam (Request Body说明) {string} type 三方登录类型
 * @apiParam (Request Body说明) {string} deviceToken  设备Token
 * @apiParam (Request Body说明) {string} deviceModel  设备类型
 *
 * @apiPermission ANYBODY
 *
 * @apiUse SINGLE
 * @apiSuccessExample {json} 成功示例
 * {
 *   "code": 0,
 *   "data": {
 *      "userId":123456,
 *      "accessToken": "MUNDRkIxNDJFOTlBOThBN0VERjU3OTZCMUExNUI1RTQ2QzI4NDIyRjYyNENFQkVFRUU3OTcyRTYwQTUwODBCNA==",
 *      "expireIn": 2592000
 *   }
 * }
 * @apiSuccess (成功说明) {long} data.expireIn=一个月 过期时间
 * @apiSuccess (成功说明) {string} data.accessToken accessToken
 * @apiSuccess (成功说明) {long} data.userId 用户id
 *
 */