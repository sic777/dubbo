/************************************************************************/
/****************************** 获取常量列表 ******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {get} /constants  获取常量列表
 * @apiName CONSTANTS
 * @apiGroup CONSTANT
 * @apiDescription 获取常量列表
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiSuccessExample {json} 成功示例
 *
 * {
 * 	"code": 0,
 * 	"message": "OK",
 * 	"data": {
 * 		"1": {
 * 			"list": [{}],
 * 			"count": 100
 * 		},
 * 		"2": {
 * 			"list": [{}],
 * 			"count": 100
 * 		},
 * 		"3": {
 * 			"list": [{}],
 * 			"count": 100
 * 		},
 * 		"4": {
 * 			"list": [{}],
 * 			"count": 100
 * 		},
 * 		"5": {
 * 			"list": [{}],
 * 			"count": 100
 * 		}
 * 	}
 * }
 * 
 * @apiSuccess (成功说明) {json} data.1 <a href="#api-CONSTANT-SYYY">爽约原因</a>
 * @apiSuccess (成功说明) {json} data.2 <a href="#api-CONSTANT-CSXX">城市信息</a>
 * @apiSuccess (成功说明) {json} data.3 <a href="#api-CONSTANT-XQAH">兴趣爱好</a>
 * @apiSuccess (成功说明) {json} data.4 <a href="#api-CONSTANT-FKLX">反馈说明</a>
 * @apiSuccess (成功说明) {json} data.5 <a href="#api-CONSTANT-YHDJ">用户等级</a>
 *
 */




/************************************************************************/
/******************************** 爽约原因 ******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {get} /constant?type=1 爽约原因
 * @apiName SYYY
 * @apiGroup CONSTANT
 * @apiDescription 爽约原因
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse LIST
 * @apiSuccessExample {json} 成功示例
 *
 * {
 *	   "code":0,
 *	   "message":"OK",
 *	   "data":{
 *		 "list":[{
 *			  "id":"主键",
 *			  "reason":"爽约原因描述",
 *			  "createTime":12312312313,
 *			  "updateTime":12312312323,
 *			  "status":"0可用,1不可以用"
 *		  }],
 *		  "count":100
 *     }
 * }
 *
 */

/************************************************************************/
/******************************** 城市信息 ******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {get} /constant?type=2 城市信息
 * @apiName CSXX
 * @apiGroup CONSTANT
 * @apiDescription 城市信息
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse LIST
 * @apiSuccessExample {json} 成功示例
 *
 *   {
 *	   "code":0,
 *	   "message":"OK",
 *	   "data":{
 *		 "list":[{
 *			  "id":"主键",
 *			  "city":"广州",
 *			  "createTime":12312312313,
 *			  "updateTime":12312312323,
 *			  "status":"0可用,1不可以用"
 *		  }],
 *		  "count":100
 *     }
 * }
 */

/************************************************************************/
/******************************** 兴趣爱好 ******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {get} /constant?type=3 兴趣爱好
 * @apiName XQAH
 * @apiGroup CONSTANT
 * @apiDescription 兴趣爱好
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse LIST
 * @apiSuccessExample {json} 成功示例
 *
 * {
 *	   "code":0,
 *	   "message":"OK",
 *	   "data":{
 *		 "list":[{
 *			  "id":"主键",
 *			  "name":"爱好",
 *			  "createTime":12312312313,
 *			  "updateTime":12312312323,
 *			  "status":"0可用,1不可以用"
 *		  }],
 *		  "count":100
 *     }
 * }
 */

/************************************************************************/
/******************************** 反馈类型 ******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {get} /constant?type=4 反馈类型
 * @apiName FKLX
 * @apiGroup CONSTANT
 * @apiDescription 反馈类型
 *
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse LIST
 * @apiSuccessExample {json} 成功示例
 * {
 *	   "code":0,
 *	   "message":"OK",
 *	   "data":{
 *		 "list":[{
 *			  "id":"主键",
 *			  "name":"反馈类型名称",
 *			  "createTime":12312312313,
 *			  "updateTime":12312312323,
 *			  "status":"0可用,1不可以用"
 *		  }],
 *		  "count":100
 *     }
 * }
 */

/************************************************************************/
/******************************** 用户等级 ******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {get} /constant?type=5 用户等级
 * @apiName YHDJ
 * @apiGroup CONSTANT
 * @apiDescription 用户等级
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse LIST
 * @apiSuccessExample {json} 成功示例
 *
 * {
 *	   "code":0,
 *	   "message":"OK",
 *	   "data":{
 *		 "list":[{
 *			  "id":"主键",
 *			  "name":"等级名称",
 *			  "createTime":12312312313,
 *			  "updateTime":12312312323,
 *			  "status":"0可用,1不可以用"
 *		  }],
 *		  "count":100
 *     }
 * }
 *
 */