/************************************************************************/
/****************************** 获取用户Token ***************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {get} /im/token/user 获取用户Token
 * @apiName IM-TOKEN
 * @apiGroup IM
 * @apiDescription  获取用户Token
 *
 * @apiUse ACK
 * @apiPermission USER
 * 
 * @apiUse SINGLE
 * @apiSuccessExample {json} 成功示例
 * {
 *  "code": 0,
 *  "message":"OK"
 *  "data": {
 *			"token":"sadasda9s9090=="
 *		}
 * }
 *
 * @apiSuccess (成功说明) {string} data.token 融云Token，<code>有效期永久</code>
 *
 */

/************************************************************************/
/****************************** 更新用户信息 ****************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {put} /im/user 更新用户信息
 * @apiName IM-USER
 * @apiGroup IM
 * @apiDescription 更新用户在im的信息
 *
 * @apiParamExample {json} Request Body示例
 * {
 *  "name":"sic777",
 *  "portrait":"http://127.0.0.1/img.png",
 * }
 * @apiParam (Request Body说明) {string} name 用户昵称
 * @apiParam (Request Body说明) {string} portrait 用户头像地址
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse SINGLE
 * @apiUse RESPONSE_NULL
 *
 */




