package com.mi.miapi_client_sdk.strategy.impl;

import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.mi.miapi_client_sdk.appservice.AppService;
import com.mi.miapi_client_sdk.strategy.BaseStrategy;
import com.mi.miapi_common.model.dto.GetNameRequest;

/**
 * @author mi11
 * @version 1.0
 * @project mi api_client_sdk
 * @description 获取名字接口
 * @ClassName GetNameStrategy
 */
public class GetNameStrategy implements BaseStrategy {



    @Override
    public String definitionRequest(String url, String param,AppService appService) {
        Gson gson = new Gson();
        GetNameRequest getNameRequest = gson.fromJson(param, GetNameRequest.class);
        if (getNameRequest == null || StrUtil.isBlank(getNameRequest.getName())) {
           throw new RuntimeException("请求参数错误");
        }
        return appService.definitionRequestUsingPost(url,getNameRequest,appService);
    }
}
