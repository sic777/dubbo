package com.sic777.demo.client.controller;

import com.sic777.demo.client.PermissionExtends;
import com.sic777.restful.springboot.controller.RestfulController;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.sic777.demo.client.controller.REST_URL.*;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
@RequestMapping(PREFIX_URI)
public abstract class BaseController extends RestfulController implements PermissionExtends {
}
