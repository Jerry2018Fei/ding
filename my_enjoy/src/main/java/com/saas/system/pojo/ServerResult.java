package com.saas.system.pojo;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
/**
 * Auth: dingpengfei
 * Date: 2017/9/7 12:55
 * Title: web层信息封装类
 * Describe:需要转换为json数据的信息统一由此工具转换后再处理
 **/
@NoArgsConstructor@Data
public class ServerResult<T> implements Serializable {
    private static final long serialVersionUID = 171867919805672543L;
    private Boolean  success;
    private String message;
    private T data;//对象
    private String dataMessage;//字符串
    private List<T> list;//集合
    private PageInfo<T> pageInfo;//分页信息
    public ServerResult(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ServerResult(Boolean success, String message, String dataMessage) {
        this.success = success;
        this.message = message;
        this.dataMessage = dataMessage;
    }

    public ServerResult(Boolean success, String message, List<T> list) {
        this.success = success;
        this.message = message;
        this.list = list;
    }

    public ServerResult(Boolean success, String message, PageInfo<T> pageInfo) {
        this.success = success;
        this.message = message;
        this.pageInfo = pageInfo;
    }

    public ServerResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }




}
