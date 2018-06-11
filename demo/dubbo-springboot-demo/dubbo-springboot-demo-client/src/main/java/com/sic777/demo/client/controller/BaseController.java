package com.sic777.demo.client.controller;

import com.sic777.demo.client.permission.RestPermissionExtend;
import com.sic777.microservice.controller.RestfulController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
@RequestMapping("/v1")
public abstract class BaseController extends RestfulController implements RestPermissionExtend {
}
