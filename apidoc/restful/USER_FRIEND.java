/************************************************************************/
/********************************  添加好友 ******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {post} /user/friend/{id} 添加好友
 * @apiName USER-FRIEND-POST
 * @apiGroup USER_FRIEND
 * @apiDescription 双向添加好友
 *
 * @apiParam (Path Valiable) {long} id 目标用户id
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse SINGLE
 * @apiUse RESPONSE_NULL
 *
 */

/************************************************************************/
/******************************** 删除好友 ******************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {delete} /user/friend/{id} 删除好友
 * @apiName USER-FRIEND-DELETE
 * @apiGroup USER_FRIEND
 * @apiDescription 双向删除好友
 *
 * @apiParam (Path Valiable) {long} id 目标用户id
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse SINGLE
 * @apiUse RESPONSE_NULL
 *
 */

/************************************************************************/
/******************************** 好友列表 ******************************/
/************************************************************************/
/**
 * @apiVersion 1.0.0
 * @api {get} /user/friends 好友列表
 * @apiName USER-FRIEND-LIST
 * @apiGroup USER_FRIEND
 * @apiDescription 返回所有已经添加的好友列表
 *
 * @apiUse QUERY
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
 * 			"picture": "www.wanquan.com/10001.jpg",
 * 			"note": "用户备注1"
 * 		}, {
 * 			"id": 100002,
 * 			"nickName": "user2",
 * 			"picture": "www.wanquan.com/10002.jpg",
 * 			"note": "用户备注2"
 * 		}],
 * 		"count": 100
 * 	}
 * }
 *
 * @apiSuccess (成功说明) {long} data.list.id 用户主键
 * @apiSuccess (成功说明) {string} data.list.nickName 用户昵称
 * @apiSuccess (成功说明) {string} data.list.picture 用户头像
 * @apiSuccess (成功说明) {string} data.list.note 用户备注
 *
 */

/************************************************************************/
/******************************** 修改好友备注 ****************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {put} /user/friend/{id}/note 修改好友信息
 * @apiName USER-FRIEND_NOTE
 * @apiGroup USER_FRIEND
 * @apiDescription 修改好友备注
 *
 * @apiParam (Path Valiable) {long} id 目标用户id
 *
 * @apiParamExample {json} Request Body示例
 * {
 *  "friendId":"好友id",
 *  "note":"好友备注"
 * }
 *
 * @apiParam (Request Body说明) {long} friendId 目标用户id
 * @apiParam (Request Body说明) {string} note 备注
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 * @apiUse SINGLE
 * @apiUse RESPONSE_NULL
 *
 */
