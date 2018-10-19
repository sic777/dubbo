/************************************************************************/
/*************************** 获取商家基本信息 ***************************/
/************************************************************************/

/**
 * 
 * @apiVersion 1.0.0
 * @api {get} /business/{id} 获取商家基本信息
 * @apiGroup BUSINESS
 * @apiName BUSINESS-ID
 * @apiDescription 获取商家基本信息 
 *
 * @apiParam (Path Valiable) {long} id 商家id
 *
 * @apiUse ACK_OPTION
 * @apiPermission USER
 * @apiPermission ANYBODY
 *
 * @apiUse SINGLE 
 * @apiSuccessExample {json} 成功示例
 *
 * {
 * "code": 0,
 * "message": "OK",
 * "data": {
 *		"id": 10001,
 *		"picture":"www.wanquan.com/10001.jpg",
 *		"address": "广东省广州市XXXXX", 
 *		"desc":"商家描述",
 *		"name": "商家名称",
 *		"phone": "15014361307",
 *		"openingTime":111222333444,
 *		"closingTime":222123123123,
 *		"activityCount":10,
 *		"attentionCount":4,
 *		"creditCount":3,
 *		"longitude":20.0,
 *		"latitude":20.1,
 *		"isFollow":true
 *	}
 * }
 *
 * @apiSuccess (成功说明) {string} data.picture 商家头像
 * @apiSuccess (成功说明) {string} data.address 商家地址
 * @apiSuccess (成功说明) {string} data.desc 商家描述
 * @apiSuccess (成功说明) {string} data.name 商家名称 
 * @apiSuccess (成功说明) {string} data.phone 手机号码
 * @apiSuccess (成功说明) {long} data.openingTime 营业时间
 * @apiSuccess (成功说明) {long} data.closingTime 打烊时间
 * @apiSuccess (成功说明) {int} data.activityCount 活动次数
 * @apiSuccess (成功说明) {int} data.attentionCount 被关注次数
 * @apiSuccess (成功说明) {int} data.creditCount 好评次数
 * @apiSuccess (成功说明) {double} data.longitude 商家所在经度
 * @apiSuccess (成功说明) {double} data.latitude 商家所在纬度
 * @apiSuccess (成功说明) {boolean} data.isFollow 我是否关注该商家
 * @apiSuccess (成功说明) {long} data.id 主键
 */


/************************************************************************/
/******************************* 获取商家列表 ***************************/
/************************************************************************/


/**
 * @apiVersion 1.0.0
 * @api {get} /businesses 获取商家列表
 * @apiName BUSINESSES
 * @apiGroup BUSINESS
 * @apiDescription 获取商家列表
 *
 * @apiUse QUERY
 * @apiParam (Request Param说明) {long} [query.id] 主键
 * @apiParam (Request Param说明) {string} [query.name] 商家名称
 * @apiParam (Request Param说明) {string} [query.phone] 商家号码
 * @apiParam (Request Param说明) {string} [query.address] 商家地址
 * @apiParam (Request Param说明) {long} [query.openingTime] 营业时间
 * @apiParam (Request Param说明) {long} [query.closingTime] 打烊时间
 * @apiParam (Request Param说明) {int} [query.activityCount] 活动次数
 * @apiParam (Request Param说明) {int} [query.attentionCount] 被关注次数
 * @apiParam (Request Param说明) {int} [query.creditCount] 好评次数
 * @apiParam (Request Param说明) {boolean} [query.isFollow] 我是否关注该商家
 *
 * @apiUse ACK_OPTION
 * @apiPermission USER
 * @apiPermission ANYBODY
 *
 * @apiUse LIST
 * @apiSuccessExample {json} 成功示例
 * {
 * "code": 0,
 * "message":"OK",
 * "data": { 
 * 	"list":[{ 
 *		"id": 10001,
 * 		"picture":"www.wanquan.com/10001.jpg",
 * 		"address": "广东省广州市XXXXX", 
 * 		"desc":"商家描述",
 * 		"name": "商家名称",
 * 		"phone": "15014361307",
 * 		"openingTime":111222333444,
 *		"closingTime":222123123123,
 * 		"activityCount":10,
 * 		"attentionCount":4,
 * 		"creditCount":3,
 *		"longitude":20.0,
 *		"latitude":20.1,
 *		"isFollow":true
 * 		}], 
 *	"count":100 
 *  }
 * }
 * 
 * @apiSuccess (成功说明) {string} data.list.picture 商家头像
 * @apiSuccess (成功说明) {string} data.list.address 商家地址
 * @apiSuccess (成功说明) {string} data.list.desc 商家描述
 * @apiSuccess (成功说明) {string} data.list.name 商家名称 
 * @apiSuccess (成功说明) {string} data.list.phone 手机号码
 * @apiSuccess (成功说明) {long} data.list.openingTime 营业时间
 * @apiSuccess (成功说明) {long} data.list.closingTime 打烊时间
 * @apiSuccess (成功说明) {int} data.list.activityCount 活动次数
 * @apiSuccess (成功说明) {int} data.list.attentionCount 被关注次数
 * @apiSuccess (成功说明) {int} data.list.creditCount 好评次数
 * @apiSuccess (成功说明) {double} data.list.longitude 经度
 * @apiSuccess (成功说明) {double} data.list.latitude 纬度
 * @apiSuccess (成功说明) {boolean} data.list.isFollow 我是否关注该商家
 * @apiSuccess (成功说明) {long} data.list.id 主键
 */

/************************************************************************/
/*************************** 获取附近商家列表 ***************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {get} /businesses/near 获取附近商家列表
 * @apiName BUSINESSES-NEAR
 * @apiGroup BUSINESS
 * @apiDescription 获取附近商家列表
 *
 * @apiParam (Request Param说明) {long} [distance=1000] 动态参数- 距离，单位：米
 *
 * @apiUse ACK_OPTION
 * @apiPermission USER
 * @apiPermission ANYBODY
 *
 * @apiUse LIST
 * @apiSuccessExample {json} 成功示例
 * {
 * "code": 0,
 * "message":"OK",
 * "data": {
 * 	"list":[{ 
 *		"id": 10001,
 * 		"picture":"www.wanquan.com/10001.jpg",
 * 		"address": "广东省广州市XXXXX", 
 * 		"desc":"商家描述",
 * 		"name": "商家名称",
 * 		"phone": "15014361307",
 * 		"openingTime":111222333444,
 *		"closingTime":222123123123,
 * 		"activityCount":10,
 * 		"attentionCount":4,
 * 		"creditCount":3,
 *		"longitude":20.0,
 *		"latitude":20.1,
 *		"isFollow":true
 * 		}], 
 *	"count":100 
 *   }
 * }
 * 
 * @apiSuccess (成功说明) {string} data.list.picture 商家头像
 * @apiSuccess (成功说明) {string} data.list.address 商家地址
 * @apiSuccess (成功说明) {string} data.list.desc 商家描述
 * @apiSuccess (成功说明) {string} data.list.name 商家名称 
 * @apiSuccess (成功说明) {string} data.list.phone 手机号码
 * @apiSuccess (成功说明) {long} data.list.openingTime 营业时间
 * @apiSuccess (成功说明) {long} data.list.closingTime 打烊时间
 * @apiSuccess (成功说明) {int} data.list.activityCount 活动次数
 * @apiSuccess (成功说明) {int} data.list.attentionCount 被关注次数
 * @apiSuccess (成功说明) {int} data.list.creditCount 好评次数
 * @apiSuccess (成功说明) {double} data.list.longitude 经度
 * @apiSuccess (成功说明) {double} data.list.latitude 纬度
 * @apiSuccess (成功说明) {boolean} data.list.isFollow 我是否关注该商家
 * @apiSuccess (成功说明) {long} data.list.id 主键
 */

