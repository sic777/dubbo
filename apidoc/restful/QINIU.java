/************************************************************************/
/************************ 获取用户头像上传token **************************/
/************************************************************************/

/**
 * @apiVersion 1.0.0
 * @api {get} /auth/qiniu/userPicture 用户获取上传头像token
 * @apiName QINIU-USER-PICTURE
 * @apiGroup QINIU
 * @apiDescription 用户获取上传头像token
 *
 * @apiUse ACK
 * @apiPermission USER
 *
 *
 * @apiUse SINGLE
 * @apiSuccessExample {json} 成功示例
 * {
 * "code": 0,
 * "message": "OK",
 * "data": {
 *  "expireIn": 7200,
 *  "fileKey": "10000.jpg",
 *  "upToken": "HYLxkXunUsnKPEP7XU54Qa-OZS_ukbXZn77Zp4rB:XCqd1qnmc6cyWjNPKoyPfbq9yP8=:eyJzY29wZSI6InVzZXItaGVhZHNob3Q6MTAwMDAuanBnIiwicmV0dXJuQm9keSI6IntcImtleVwiOlwiJChrZXkpXCIsXCJoYXNoXC * I6XCIkKGV0YWcpXCIsXCJidWNrZXRcIjpcIiQoYnVja2V0KVwiLFwiZnNpemVcIjokKGZzaXplKX0iLCJkZWFkbGluZSI6MTUzNTQ3MjY1OX0="
 * }
 * }
 */

