/**
 * @apiDefine response_list
 *
 * @apiSuccessExample {json} 成功示例
 *
 *{
 *	"code" : 200,
 *	"message" : "OK",
 *	"data" : {
 *		"list" : [{
 *
 *		}],
 *		"count" : 999
 *	}
 *}
 *
 * @apiSuccess (成功说明) {number} code 状态码,非200时均为错误状态
 * @apiSuccess (成功说明) {string} message 提示信息
 * @apiSuccess (成功说明) {json} data 响应数据对象
 * @apiSuccess (成功说明) {number} data.count 数据总量
 * @apiSuccess (成功说明) {array} data.list 对象数组
 */