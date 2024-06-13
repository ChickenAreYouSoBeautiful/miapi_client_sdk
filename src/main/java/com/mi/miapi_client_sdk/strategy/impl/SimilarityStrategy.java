package com.mi.miapi_client_sdk.strategy.impl;

import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.mi.miapi_client_sdk.appservice.AppService;
import com.mi.miapi_client_sdk.strategy.BaseStrategy;
import com.mi.miapi_common.model.dto.GetSimilarityRequest;

/**
 * @author mi11
 * @version 1.0
 * @project miapi_client_sdk
 * @description
 * @ClassName SimilarityStrategy
 */
public class SimilarityStrategy implements BaseStrategy {

    @Override
    public String definitionRequest(String url, String param, AppService appService) {
        Gson gson = new Gson();
        GetSimilarityRequest getSimilarityRequest = gson.fromJson(param, GetSimilarityRequest.class);
        if (getSimilarityRequest == null || StrUtil.isBlank(getSimilarityRequest.getStrCenter()) || getSimilarityRequest.getStrList() == null) {
            throw new RuntimeException("请求参数错误");
        }
        return appService.definitionRequestUsingPost(url,getSimilarityRequest,appService);
    }
}
