/************************************************************************/
/************************* 获取未发起的活动列表 ****************************/
/************************************************************************/

/**
 *
 * @apiVersion 1.0.0
 * @api {get} /activities 获取未发起的活动列表
 * @apiName ACTIVITIES
 * @apiGroup ACTIVITY
 * @apiDescription 获取未发起的活动列表,只有领队有权限
 *
 * @apiUse QUERY
 * @apiParam (Request Param说明) {string} [query.name] 活动名称
 * @apiParam (Request Param说明) {string} [query.address] 活动地址
 * @apiParam (Request Param说明) {number} [query.originPrice] 活动原价
 * @apiParam (Request Param说明) {number} [query.currentPrice] 活动现价
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse LIST
 * @apiSuccessExample {json} 成功示例
 * {
 * 	"code": 0,
 * 	"message": "OK",
 * 	"data": {
 * 		"count": 14,
 * 		"list": [{
 * 			"id": 59422871511896064,
 * 			"name": "活动1",
 * 			"description": "活动描述",
 * 			"picture": "http://www.wanquan.com",
 * 			"address": "广州市海珠区昌岗路41号商铺",
 * 			"originPrice": 100,
 * 			"currentPrice": 88,
 * 			"activityCount": 12,
 * 			"creditCount": 10
 * 		}]
 * 	}
 * }
 * @apiSuccess (成功说明) {long} data.list.id 活动id
 * @apiSuccess (成功说明) {string} data.list.name 活动名称
 * @apiSuccess (成功说明) {string} data.list.description 活动描述
 * @apiSuccess (成功说明) {string} data.list.picture 活动图片url
 * @apiSuccess (成功说明) {string} data.list.address 活动地址
 * @apiSuccess (成功说明) {number} data.list.originPrice 活动原价
 * @apiSuccess (成功说明) {number} data.list.currentPrice 活动现价
 * @apiSuccess (成功说明) {number} data.list.activityCount 活动次数
 * @apiSuccess (成功说明) {number} data.list.creditCount 好评数
 *
 */



/************************************************************************/
/************************** 获取未发起的活动详情 ***************************/
/************************************************************************/

/**
 *
 * @apiVersion 1.0.0
 * @api {get} /activity/{id} 获取未发起的活动列表
 * @apiName ACTIVITY
 * @apiGroup ACTIVITY
 * @apiDescription 获取未发起的活动详情,只有领队有权限
 *
 * @apiParam (Path Valiable) {long} id 活动id
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse SINGLE
 * @apiSuccessExample {json} 成功示例
 * {
 * 	"code": 0,
 * 	"message":"OK",
 * 	"data": {
 * 		"id": 60801673479589888,
 * 		"name": "活动10",
 * 		"description": "活动说明",
 * 		"address": "广州市海珠区昌岗路41号商铺",
 * 		"businessId": 10000,
 * 		"businessName": "测试商家1",
 * 		"latitude": 2345.67890001,
 * 		"longitude": 12345.6789,
 * 		"number": "123456789",
 * 		"picture": "http://www.lol.com",
 * 		"type": 1,
 * 		"deadlineLimit": 240,
 * 		"peopleLimit": 12,
 * 		"priceTimeList": [{
 * 				"activityId": 60801673479589888,
 * 				"date": 1,
 * 				"priceTimes": [{
 * 					"priceTimeId": 88408920141336576,
 * 					"endTime": "10:00",
 * 					"originPrice": 100,
 * 					"currentPrice": 80,
 * 					"startTime": "08:00"
 * 				}]
 * 			},
 * 			{
 * 				"activityId": 60801673479589888,
 * 				"date": 2,
 * 				"priceTimes": [{
 * 					"priceTimeId": 88408920141336576,
 * 					"endTime": "10:00",
 * 					"originPrice": 100,
 * 					"currentPrice": 80,
 * 					"startTime": "08:00"
 * 				}]
 * 			}
 * 		],
 * 		"pictureList": ["www.wanquan.com/picture1.jpg", "www.wanquan.com/picture2.jpg"],
 * 		"videoList": ["www.wanquan.com/video1.mp4", "www.wanquan.com/video2.mp4"]
 * 	}
 * }
 *
 * 
 * @apiSuccess (成功说明) {number} data.id 活动id
 * @apiSuccess (成功说明) {string} data.name 活动名称
 * @apiSuccess (成功说明) {string} data.description 活动说明
 * @apiSuccess (成功说明) {string} data.address 活动地址
 * @apiSuccess (成功说明) {number} data.businessId 商家Id
 * @apiSuccess (成功说明) {string} data.businessName 商家名称
 * @apiSuccess (成功说明) {number} data.longitude 纬度
 * @apiSuccess (成功说明) {number} data.latitude 纬度
 * @apiSuccess (成功说明) {string} data.picture 活动头像
 * @apiSuccess (成功说明) {number} data.peopleLimit 人数限制
 * @apiSuccess (成功说明) {number} data.deadlineLimit 截至时间限制，单位为分
 * @apiSuccess (成功说明) {list} data.priceTimeList 时间价格列表
 * @apiSuccess (成功说明) {list} data.pictureList 活动图片列表
 * @apiSuccess (成功说明) {list} data.videoList 活动视频列表
 */


/************************************************************************/
/******************************* 发起活动 *******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {post} /activity/{id}/launch 发起活动
 * @apiName ACTIVITY-LAUNCH
 * @apiGroup ACTIVITY
 * @apiDescription 发起活动,只有领队有权限
 *
 * @apiParam (Path Valiable) {long} id 活动id
 *
 * @apiParamExample {json} Request Body示例
 * {
 *  "activityDate":"活动日期 20180617"
 *  "price":98.00,
 *  "peopleMin":"最小可开团人数 12",
 *  "peopleMax":"最大可报名人数 16",
 *  "deadline":"活动截止时间 20180617 19:30:00",
 *  "leaderSpeak":"领队留言 留言",
 *  "priceTimeId":价格id
 * }
 *
 * @apiParam (Request Body说明) {string} activityDate 活动日期
 * @apiParam (Request Body说明) {number} price 活动价格
 * @apiParam (Request Body说明) {number} peopleMin 最少开团人数
 * @apiParam (Request Body说明) {number} peopleMax 最多参数人数
 * @apiParam (Request Body说明) {string} deadline 活动报名截至时间
 * @apiParam (Request Body说明) {string} leaderSpeak 领队留言
 * @apiParam (Request Body说明) {number} priceTimeId 活动价格id
 *
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse SINGLE
 * @apiUse RESPONSE_NULL
 *
 */

/************************************************************************/
/************************* 获取已发起的活动列表 ****************************/
/************************************************************************/
/**
 *
 * @apiVersion 1.0.0
 * @api {get} /activities-launch 获取已发起的活动列表
 * @apiName ACTIVITIES-LAUNCH
 * @apiGroup ACTIVITY
 * @apiDescription 用户搜索领队已经发起的活动
 *
 * @apiUse QUERY
 * @apiParam (Request Param说明) {string} [query.activityId] 活动Id
 * @apiParam (Request Param说明) {string} [query.activityName] 活动名称
 * @apiParam (Request Param说明) {long} [query.startTime] 开始时间
 * @apiParam (Request Param说明) {long} [query.endTime] 结束时间
 * @apiParam (Request Param说明) {long} [query.deadline] 报名截止时间
 * @apiParam (Request Param说明) {int} [query.joinNum] 已经加入的人数
 * @apiParam (Request Param说明) {int} [query.status] 状态
 * @apiParam (Request Param说明) {number} [query.price] 活动价格
 * @apiParam (Request Param说明) {string} [query.address] 活动地址
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
 * 		"count": 14,
 * 		"list": [{
 * 			"id": 1000000,
 * 		    "activityId":123123,
 * 			"activityName": "真人CS",
 * 			"startTime": 12313123123,
 * 			"endTime": 12312312312313,
 * 			"deadline": 123123123123,
 * 			"joinNum": 10,
 * 			"status": 1,
 * 			"picture": "www.wanquan.com/picture1.jpg",
 * 			"price": 120,
 * 			"address": "广州市天河区岗顶"
 * 		}]
 * 	}
 * }
 *
 * @apiSuccess (成功说明) {number} data.list.id 发起活动id
 * @apiSuccess (成功说明) {long} data.list.activityId 活动Id
 * @apiSuccess (成功说明) {string} data.list.activityName 活动名称
 * @apiSuccess (成功说明) {long} data.list.startTime 开始时间
 * @apiSuccess (成功说明) {long} data.list.endTime 结束时间
 * @apiSuccess (成功说明) {long} data.list.deadline 报名截止时间
 * @apiSuccess (成功说明) {string} data.list.address 活动地址
 * @apiSuccess (成功说明) {int} data.list.joinNum 已经加入的人数
 * @apiSuccess (成功说明) {string} data.list.price 活动价格
 * @apiSuccess (成功说明) {number} data.list.status 状态
 * @apiSuccess (成功说明) {string} data.list.picture 活动头像
 *
 **/

/************************************************************************/
/************************** 获取已发起的活动详情 ***************************/
/************************************************************************/

/**
 *
 * @apiVersion 1.0.0
 * @api {get} /activity-launch/{id} 获取已发起的活动详情
 * @apiName ACTIVITY-LAUNCH-GET
 * @apiGroup ACTIVITY
 * @apiDescription 用户查看领队已经发起的活动详情
 *
 * @apiParam (Path Valiable) {long} id 发起活动id
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse SINGLE
 * @apiSuccessExample 成功示例
 * {
 * 	"code": 0,
 * 	"message": "OK",
 * 	"data": {
 * 		"id": 1000001,
 * 		"activityId": 60801673479589890,
 * 		"activityName": "活动10",
 * 		"address": "广州市海珠区昌岗路41号商铺",
 * 		"businessId": 10000,
 * 		"businessName": "测试商家1",
 * 		"startTime": 1231231231,
 * 		"endTime": 123123123123,
 * 		"deadline": 123123123,
 * 		"price": 100,
 * 		"oriPrice": 120,
 * 		"joinNum": 10,
 * 		"commentCount": 340,
 * 		"creditCount": 200,
 * 		"description": "活动说明",
 * 		"latitude": 2345.67890001,
 * 		"leaderSpeak": "领队留言 留言",
 * 		"longitude": 12345.6789,
 * 		"peopleMax": 16,
 * 		"peopleMin": 12,
 * 		"picture": "http://www.lol.com",
 * 		"type": 1,
 * 		"pictureList": ["www.wanquan.com/picture1.jpg", "www.wanquan.com/picture2.jpg"],
 * 		"videoList": ["www.wanquan.com/video1.mp4", "www.wanquan.com/video2.mp4"]
 * 	}
 * }
 *
 * @apiSuccess (成功说明) {number} data.id 发起活动id
 * @apiSuccess (成功说明) {long} data.activityId 活动id
 * @apiSuccess (成功说明) {string} data.activityName 活动名称
 * @apiSuccess (成功说明) {string} data.address 活动地址
 * @apiSuccess (成功说明) {number} data.businessId 商家Id
 * @apiSuccess (成功说明) {string} data.businessName 商家名称
 * @apiSuccess (成功说明) {long} data.list.startTime 开始时间
 * @apiSuccess (成功说明) {long} data.list.endTime 结束时间
 * @apiSuccess (成功说明) {long} data.list.deadline 报名截止时间
 * @apiSuccess (成功说明) {number} data.price 价格
 * @apiSuccess (成功说明) {number} data.oriPrice 原价
 * @apiSuccess (成功说明) {number} data.joinNum 已经参与人数
 * @apiSuccess (成功说明) {number} data.commentCount 评论数
 * @apiSuccess (成功说明) {number} data.creditCount 好评数
 * @apiSuccess (成功说明) {string} data.description 活动说明
 * @apiSuccess (成功说明) {number} data.longitude 纬度
 * @apiSuccess (成功说明) {number} data.latitude 纬度
 * @apiSuccess (成功说明) {string} data.picture 活动头像
 * @apiSuccess (成功说明) {number} data.peopleMax 最多参与人数
 * @apiSuccess (成功说明) {number} data.peopleMin 最少参数人数
 * @apiSuccess (成功说明) {list} data.pictureList 活动图片列表
 * @apiSuccess (成功说明) {list} data.videoList 活动视频列表
 *
 */


