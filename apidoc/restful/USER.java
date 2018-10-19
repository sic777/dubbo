/************************************************************************/
/**************************** 获取用户基本信息 **************************/
/************************************************************************/
/**
 * @apiVersion 1.0.0
 * @api {get} /user/{id} 获取用户基本信息
 * @apiName USER-BASE-INFO
 * @apiGroup USER
 * @apiDescription 获取用户基本信息,当调用者为用户本人则获取的是"个人基本信息",否则获取的为"非本人的用户公开信息"
 *
 * @apiParam (Path Valiable) {long} id 用户id
 *
 * @apiUse ACK_OPTION
 * @apiPermission USER
 * @apiPermission ANYBODY
 *
 *
 * @apiUse SINGLE
 * @apiSuccessExample {json} 成功示例
 * {
 *	"code": 0,
 *	"message": "OK",
 *	"data": {
 *		"picture": "www.wanquan.com/10001.jpg",
 *		"email": "329113220@qq.com",
 *		"id": 10001,
 *		"name": "刘钊辉",
 *		"nickName": "liuzh",
 *		"phone": "15014361307",
 *		"birthday": "19930101",
 *		"wechat": "liuzhaohui123",
 *		"sex": 1,
 *		"type": 1,
 *		"tags": [123123, 234234],
 *	    "levels":[1231,13123],
 *		"activityCount": 10,
 *		"attentionCount": 4,
 *		"breachCount": 3
 *	}
 *}
 * 
 *
 * @apiSuccess (成功说明) {string} data.id 主键
 * @apiSuccess (成功说明) {int} data.type 类型
 * @apiSuccess (成功说明) {string} data.nickName 昵称
 * @apiSuccess (成功说明) {int} data.sex 性别
 * @apiSuccess (成功说明) {string} data.birthday 生日
 * @apiSuccess (成功说明) {string} data.wechat 微信号
 * @apiSuccess (成功说明) {string} data.phone 手机
 * @apiSuccess (成功说明) {string} data.email 邮箱
 * @apiSuccess (成功说明) {string} data.picture 头像
 * @apiSuccess (成功说明) {string} data.name 姓名
 * @apiSuccess (成功说明) {int} data.activityCount 活动次数
 * @apiSuccess (成功说明) {int} data.attentionCount 关注次数
 * @apiSuccess (成功说明) {int} data.breachCount 爽约次数
 * @apiSuccess (成功说明) {list} data.tags 用户兴趣爱好ID列表
 * @apiSuccess (成功说明) {list} data.levels 用户等级ID列表
 *
 */

/************************************************************************/
/****************************** 编辑用户信息 ****************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {put} /user/{id} 修改用户信息
 * @apiName USER-INFO
 * @apiGroup USER
 * @apiDescription 修改用户信息
 *
 * @apiParam (Path Valiable) {int} id 用户id
 *
 * @apiParamExample {json} Request Body示例
 *
 * {
 *	 "email": "329113220@qq.com",
 *	 "nickName": "liuzh",
 *	 "birthday":"19930101",
 *	 "wechat":"liuzhaohui123",
 *	 "sex": 1,
 *	 "tag": [23423423,1313123]
 * }
 *
 * @apiParam (Request Body说明) {string} email 邮箱
 * @apiParam (Request Body说明) {string} nickName 昵称
 * @apiParam (Request Body说明) {string} birthday 生日
 * @apiParam (Request Body说明) {string} wechat 微信
 * @apiParam (Request Body说明) {int} sex 性别
 * @apiParam (Request Body说明) {array} tag 标签ID列表
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse SINGLE
 * @apiUse RESPONSE_NULL
 *
 */

/************************************************************************/
/******************************* 获取用户列表 *****************************/
/************************************************************************/
/**
 * @apiVersion 1.0.0
 * @api {get} /users 获取用户列表
 * @apiName USERS
 * @apiGroup USER
 * @apiDescription 获取用户列表
 *
 * @apiUse QUERY
 *
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse LIST
 * @apiSuccessExample {json} 成功示例
 *
 * {
 * 	"code": 0,
 * 	"message": "OK",
 * 	"data": {
 * 		"list": [{
 * 			"id": 100001,
 * 			"nickName": "user1",
 * 			"picture": "www.wanquan.com/10001.jpg"
 * 		}, {
 * 			"id": 100002,
 * 			"nickName": "user2",
 * 			"picture": "www.wanquan.com/10002.jpg"
 * 		}],
 * 		"count": 100
 * 	}
 * }
 *
 * @apiSuccess (成功说明) {string} data.list.id 用户
 * @apiSuccess (成功说明) {string} data.list.nickName 昵称
 * @apiSuccess (成功说明) {string} data.list.picture 用户头像
 *
 **/
