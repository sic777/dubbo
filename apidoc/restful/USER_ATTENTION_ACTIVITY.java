/************************************************************************/
/***************************** 用户关注活动 *****************************/
/************************************************************************/
/**
 * @apiVersion 1.0.0
 * @api {post} /user/attention/activity 用户关注/取消关注活动
 * @apiName USER-ATTENTION-ACTIVITY
 * @apiGroup USER_ATTENTION_ACTIVITY
 * @apiDescription  用户关注/取消关注活动。
 *
 * @apiParamExample {json} Request Body示例
 *
 * {
 *          "activityId":123123,
 *          "type":"关注操作类型"
 *
 * }
 *
 * @apiParam (Request Body说明) {long} businessId 活动id
 * @apiParam (Request Body说明) {int} type <a href="#api-ENUMS-ATTENTION_TYPE">关注操作类型</a>
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse SINGLE
 * @apiSuccessExample {json} 成功示例
 * {
 * "code": 0,
 * "message":"OK",
 * "data": {
 *			"id":"关注id"
 *		}
 * }
 *
 * @apiSuccess (成功说明) {string} data.id 关注id
 * @apiSuccess (成功说明) {long} data.createTime 关注时间
 *
 */

/************************************************************************/
/***************************** 我的关注的活动 *****************************/
/************************************************************************/
/**
 *
 * @apiVersion 1.0.0
 * @api {get} /user/attention/activities 我的关注的活动
 * @apiName USER-ATTENTION-ACTIVITIES
 * @apiGroup USER_ATTENTION_ACTIVITY
 * @apiDescription  我的关注的活动
 *
 * @apiUse QUERY
 * @apiParam (Request Param说明) {number} [query.activityId] 活动Id
 * @apiParam (Request Param说明) {long} [query.activityDate] 活动日期
 * @apiParam (Request Param说明) {string} [query.startTime] 开始时间
 * @apiParam (Request Param说明) {string} [query.endTime] 结束时间
 * @apiParam (Request Param说明) {number} [query.status] 状态
 * @apiParam (Request Param说明) {number} [query.price] 价格
 * @apiParam (Request Param说明) {number} [query.oriPrice] 原价
 * @apiParam (Request Param说明) {string} [query.address] 地址
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse LIST
 * @apiSuccessExample {json} 成功示例
 * {
 * "code": 0,
 * "message":"OK",
 * "data": {
 * "count":14,
 * "list":[{
 *     "id":100000000,
 *     "picture":"www.wanquan.com/10000.jpg",
 *     "activityId":110000000,
 *     "activityDate":"2018123123131010，
 *     "startTime":"10:30",
 *     "endTime":"12:00",
 *     "status":1,
 *     "price":100,
 *     "oriPrice":120,
 *     "address":"广州市天河区岗顶"
 * }]
 *	}
 * }
 * @apiSuccess (成功说明) {number} data.list.id 记录id
 * @apiSuccess (成功说明) {number} data.list.activityId 活动id
 * @apiSuccess (成功说明) {string} data.list.picture 活动头像
 * @apiSuccess (成功说明) {long} data.list.activityDate 活动日期
 * @apiSuccess (成功说明) {string} data.list.activityName 活动名称
 * @apiSuccess (成功说明) {string} data.list.startTime 开始时间
 * @apiSuccess (成功说明) {string} data.list.endTime 结束时间
 * @apiSuccess (成功说明) {number} data.list.status 状态
 * @apiSuccess (成功说明) {number} data.list.price 价格
 * @apiSuccess (成功说明) {number} data.list.oriPrice 原价
 * @apiSuccess (成功说明) {string} data.list.address 地址
 */