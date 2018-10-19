/************************************************************************/
/***************************** 用户关注商家 *****************************/
/************************************************************************/

/**
 *
 * @apiVersion 1.0.0
 * @api {post} /user/attention/business 用户关注/取消关注商家
 * @apiName USER-ATTENTION-BUSINESS
 * @apiGroup USER_ATTENTION_BUSINESS
 * @apiDescription  用户关注/取消关注商家
 *
 * @apiParamExample {json} Request Body示例
 *
 * {
 *          "businessId":"商家id",
 *          "type":"关注操作类型"
 *
 * }
 *
 * @apiParam (Request Body说明) {long} businessId 商家id
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
 */


/************************************************************************/
/**************************** 我的关注的商家 ******************************/
/************************************************************************/

/**
 *
 * @apiVersion 1.0.0
 * @api {get} /user/attention/businesses 我的关注的商家
 * @apiName USER-ATTENTION-BUSINESSES
 * @apiGroup USER_ATTENTION_BUSINESS
 * @apiDescription 我的关注的商家
 *
 * @apiUse QUERY
 * @apiParam (Request Param说明) {number} [query.businessId] 商家id
 * @apiParam (Request Param说明) {string} [query.businessName] 商家名
 * @apiParam (Request Param说明) {number} [query.activityCount] 活动次数
 * @apiParam (Request Param说明) {number} [query.creditCount] 好评次数
 * @apiParam (Request Param说明) {number} [query.fansCount] 粉丝数
 *
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
 *     "businessId":110000000,
 *     "businessName":"真人CS"，
 *     "activityCount":12,
 *     "creditCount":15,
 *     "fansCount":19,
 *     "picture":"www.wanquan.com/shangjia.jpg"
 *     "activityPictureList":["www.wanquan.com/url1","www.wanquan.com/url2"]
 * }]
 *	}
 * }
 * @apiSuccess (成功说明) {number} data.list.id 记录id
 * @apiSuccess (成功说明) {number} data.list.businessId 商家id
 * @apiSuccess (成功说明) {string} data.list.businessName 商家名
 * @apiSuccess (成功说明) {string} data.list.picture 商家头像
 * @apiSuccess (成功说明) {number} data.list.activityCount 活动次数
 * @apiSuccess (成功说明) {number} data.list.creditCount 好评次数
 * @apiSuccess (成功说明) {number} data.list.fansCount 粉丝数
 * @apiSuccess (成功说明) {number} data.list.activityPictureList 商家活动图片
 */
