/************************************************************************/
/******************************* 评论视频 *********************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {post} /videos/{id}/comment 评论视频
 * @apiName VIDEOS-COMMENT-POST
 * @apiGroup VIDEOS_COMMENT
 * @apiDescription 评论视频
 *
 * @apiParam (Path Valiable) {long} id 视频id
 *
 * @apiParamExample {json} Request Body示例
 * {
 *          "content":"评论内容"
 * }
 *
 * @apiParam (Request Body说明) {string} content 评论内容
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse SINGLE
 * @apiUse RESPONSE_NULL
 *
 */

/************************************************************************/
/*************************** 点赞/取消点赞视频评论 **************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {post} /videos/{videosId}/comment/{commentId}/like 点赞/取消点赞视频评论
 * @apiName VIDEOS-COMMENT-LIKE
 * @apiGroup VIDEOS_COMMENT
 * @apiDescription 点赞或者取消点赞视频评论
 *
 * @apiParam (Path Valiable) {long} videosId 视频id
 * @apiParam (Path Valiable) {long} commentId 评论id
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

/************************************************************************/
/***************************** 获取视频评论列表 ****************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {get} /videos/{id}/comments 获取视频评论列表
 * @apiName VIDEOS-COMMENTS-LIST
 * @apiGroup VIDEOS_COMMENT
 * @apiDescription 获取视频评论列表
 *
 * @apiParam (Path Valiable) {long} id 视频id
 *
 * @apiUse QUERY
 * @apiParam (Request Param说明) {long} [query.createTime] 评论时间
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
 * 			"commentId": 231313,
 * 			"content": "评论内容",
 * 		    "createTime":12312312313,
 * 		    "likeCount":100
 * 		}],
 * 		"count": 100
 * 	}
 * }
 * @apiSuccess (成功说明) {int} data.list.commentId 评论id
 * @apiSuccess (成功说明) {int} data.list.content 评论内容
 * @apiSuccess (成功说明) {int} data.list.createTime 评论时间
 * @apiSuccess (成功说明) {long} data.list.likeCount 评论被点赞数
 *
 */
