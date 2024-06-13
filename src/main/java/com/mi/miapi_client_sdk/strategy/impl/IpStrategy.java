package com.mi.miapi_client_sdk.strategy.impl;

import com.mi.miapi_client_sdk.appservice.AppService;
import com.mi.miapi_client_sdk.strategy.BaseStrategy;

/**
 * @author mi11
 * @version 1.0
 * @project miapi_client_sdk
 * @description 调用获取ip接口策略
 * @ClassName IpStrategy
 */
public class IpStrategy implements BaseStrategy {
    @Override
    public String definitionRequest(String url, String param, AppService appService) {
        return appService.definitionRequestUsingGet(url,param,appService);
    }
}
