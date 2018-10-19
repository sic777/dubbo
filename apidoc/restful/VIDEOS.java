/************************************************************************/
/*************************** 获取商家视频列表 ******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {get} /videos/business/{bisinessId} 获取商家视频列表
 * @apiName BUSINESS-VIDEOS
 * @apiGroup VIDEOS
 * @apiDescription 获取商家视频列表
 *
 * @apiParam (Path Valiable) {long} bisinessId 商家id
 *
 * @apiUse QUERY
 * @apiParam (Request Param说明) {string} [query.videoId] 视频id
 * @apiParam (Request Param说明) {string} [query.name] 视频名称
 * @apiParam (Request Param说明) {int} [query.comments] 评论数
 * @apiParam (Request Param说明) {int} [query.likeCount] 被点赞次数
 * @apiParam (Request Param说明) {int} [query.shareCount] 被分享次数
 * @apiParam (Request Param说明) {long} [query.createTime] 视频创建时间
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
 *			"list":[{
 *			    "videoId": 10001,
 *			    "url":"www.wanquan.com/10001.mp4",
 *			    "thumbnail":"www.wanquan.com/10001.jpg",
 *			    "name": "视频名称",
 *			    "comments":100,
 *			    "likeCount":12,
 *			    "shareCount":20,
 *			    "createTime":123131312
 *			}],
 *		    "count":100
 *		}
 * }
 *
 * @apiSuccess (成功说明) {string} data.list.videoId 视频id
 * @apiSuccess (成功说明) {string} data.list.url 下载地址
 * @apiSuccess (成功说明) {string} data.list.thumbnail 视频缩略图
 * @apiSuccess (成功说明) {string} data.list.name 视频名称
 * @apiSuccess (成功说明) {int} data.list.comments 评论数
 * @apiSuccess (成功说明) {int} data.list.likeCount 被点赞次数
 * @apiSuccess (成功说明) {int} data.list.shareCount 被分享次数
 * @apiSuccess (成功说明) {long} data.list.createTime 视频创建时间
 */

/************************************************************************/
/*************************** 获取活动视频列表 ******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {get} /videos/activity/{activityId} 获取活动视频列表
 * @apiName ACTIVITY-VIDEOS
 * @apiGroup VIDEOS
 * @apiDescription 获取活动视频列表
 *
 * @apiParam (Path Valiable) {long} activityId 活动id
 *
 * @apiUse QUERY
 * @apiParam (Request Param说明) {string} [query.videoId] 视频id
 * @apiParam (Request Param说明) {string} [query.name] 视频名称
 * @apiParam (Request Param说明) {int} [query.comments] 评论数
 * @apiParam (Request Param说明) {int} [query.likeCount] 被点赞次数
 * @apiParam (Request Param说明) {int} [query.shareCount] 被分享次数
 * @apiParam (Request Param说明) {long} [query.createTime] 视频创建时间
 *
 * @apiUse ACK_OPTION
 * @apiPermission USER
 * @apiPermission ANYBODY
 *
 * @apiUse LIST
 * @apiSuccessExample {json} 成功示例
 *
 * {
 * 	"code": 0,
 * 	"message": "OK",
 * 	"data": {
 * 		"list": [{
 * 			"videoId": 10001,
 * 			"url": "www.wanquan.com/10001.mp4",
 * 			"thumbnail": "www.wanquan.com/10001.jpg",
 * 			"name": "视频名称",
 * 			"comments": 100,
 * 			"likeCount": 12,
 * 			"shareCount": 20,
 * 			"createTime": 123131312
 * 		}],
 * 		"count": 100
 * 	}
 * }
 *
 * @apiSuccess (成功说明) {string} data.list.videoId 视频id
 * @apiSuccess (成功说明) {string} data.list.url 下载地址
 * @apiSuccess (成功说明) {string} data.list.thumbnail 视频缩略图
 * @apiSuccess (成功说明) {string} data.list.name 视频名称
 * @apiSuccess (成功说明) {int} data.list.comments 评论数
 * @apiSuccess (成功说明) {int} data.list.likeCount 被点赞次数
 * @apiSuccess (成功说明) {int} data.list.shareCount 被分享次数
 * @apiSuccess (成功说明) {long} data.list.createTime 视频创建时间
 *
 */

/************************************************************************/
/*************************** 点赞/取消点赞视频 *****************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {post} /videos/{id}/like 点赞/取消点赞视频
 * @apiName VIDEOS-LIKE
 * @apiGroup VIDEOS
 * @apiDescription 点赞或者取消点赞视频
 *
 * @apiParam (Path Valiable) {long} id 视频id
 *
 * @apiParamExample {json} Request Body示例
 * {
 *          "type":1
 * }
 *
 * @apiParam (Request Body说明) {int} type <a href="#api-ENUMS-LIKE_TYPE">点赞操作类型</a>
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse SINGLE
 * @apiUse RESPONSE_NULL
 *
 */