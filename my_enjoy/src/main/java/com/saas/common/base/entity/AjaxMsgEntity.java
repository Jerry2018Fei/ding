package com.saas.common.base.entity;

import lombok.Data;

/**
 * author  魏鹏
 * date    2017年9月1日
 *
 * title   Ajax 请求返回信息公用类
 * descri   1、目前只有两个字段： success 是否成功；message 返回前端的提示信息；
 *          2、可根据实际情况添加需要的字段；
 */
@Data
public class AjaxMsgEntity {

    private boolean success = true;
	private String message;


	      
}
