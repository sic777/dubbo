package com.sic777.demo.api.test;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-05
 */
public class TestDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8178506626966153031L;
	private String id;
    private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
