/**
 * @apiDefine QUERY
 *
 * @apiParam (Request Param说明) {int} [pageNum=1] 页码
 * @apiParam (Request Param说明) {int} [pageSize=10] 每页数据量
 * @apiParam (Request Param说明) {json} [orders] 排序
 *
 * 例如:<br>
 * <code>
 * {
 *		"field1":"desc",
 *		"field2":"asc"
 * }
 * </code>
 *
 * @apiParam (Request Param说明) {json} [filters] 调用者自定义显示字段<br>
 * 例如：<br>
 * <code>
 *		["id","name"]
 * </code>
 *
 * @apiParam (Request Param说明) {json} [query] 查询条件，可以根据不同字段加上不同的比较指令来查询<br>
 * 支持比较指令包含如下:<br>
 *     $in：包含于该列表任意一个值<br>
 *     $lt：小于该字段值<br>
 *     $lte：小于或等于字段值<br>
 *     $gt：大于该字段值<br>
 *     $gte：大于或等于该字段值<br>
 *     $like：模糊匹配该字段值<br>
 *
 * 例如:<br>
 * <code>
 *		{
 *		<br>
 *		    "field1": {"$lt": "字段值"},<br>
 *		    "filed2": {"$lt": "字段值"}<br>
 * 		}
 * </code>
 *
 * @apiParamExample {json} Request Param示例
 * ?pageNum=1&pageSize=10&query={"field_1":{"$gt":10,"$lt":100},"field_2":{"$in":[1,2,3,4]},"field_3":{"$like":"hello"}}&orders={"filed_1":"desc","field_2":"asc"}&filters=["id","name"]
 *
 */
