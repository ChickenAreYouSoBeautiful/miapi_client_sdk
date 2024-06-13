package com.mi.miapi_client_sdk.strategy;

import com.mi.miapi_client_sdk.appservice.AppService;

/**
 * @author mi11
 * @version 1.0
 * @project miapi_client_sdk
 * @description 基础调用策略类
 * @ClassName BaseStrategy
 */
public interface BaseStrategy {

    /**
     * 定义请求方法
     *
     * @author mi11
     * @param url 请求路径
     * @param param 请求参数
     * @return 请求结果
     */
    String definitionRequest(String url, String param, AppService appService);
}
