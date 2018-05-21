package com.bobo.common.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * create by lishengbo on 2018-05-21 15:05
 *
 * 图片上传插件 返回结果封装
 */
//@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageResult {

    /**
     * 返回值定义必须为integer
     */
    private Integer error;

    private String url;

    private String message;

    /**
     * 路径、错误消息
     * @param url
     * @param message
     */
    public ImageResult(String url, String message) {
        if(url!=null){
            this.error=0;
            this.url = url;
        }
        if(message!=null){
            this.error=1;
            this.message = message;
        }

    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ImageResult{" +
                "error='" + error + '\'' +
                ", url='" + url + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
