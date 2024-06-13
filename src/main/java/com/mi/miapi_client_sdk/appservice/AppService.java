package com.mi.miapi_client_sdk.appservice;

/**
 * @author mi11
 * @version 1.0
 * @project miapi_client_sdk
 * @description 公共调用接口
 * @ClassName AppService
 */
public interface AppService {

    /**
     * 定义调用Post方法
     * @param url 请求路径
     * @param param 请求参数
     * @param appService 接口实现
     * @return
     */
    <T> String definitionRequestUsingPost(String url, T param, AppService appService);

    /**
     * 定义调用Get方法
     * @param url 请求路径
     * @param param 请求参数
     * @param appService 接口实现
     * @return
     */
    <T> String definitionRequestUsingGet(String url, T param, AppService appService);
}
