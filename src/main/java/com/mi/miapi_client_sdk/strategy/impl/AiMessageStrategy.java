package com.mi.miapi_client_sdk.strategy.impl;

import com.google.gson.Gson;
import com.mi.miapi_client_sdk.appservice.AppService;
import com.mi.miapi_client_sdk.strategy.BaseStrategy;
import com.mi.miapi_common.common.ErrorCode;
import com.mi.miapi_common.exception.BusinessException;
import com.mi.miapi_common.model.dto.AiMessageRequest;

/**
 * @author mi11
 * @version 1.0
 * @project miapi_client_sdk
 * @description
 * @ClassName AiMessageStrategy
 */
public class AiMessageStrategy implements BaseStrategy {

    @Override
    public String definitionRequest(String url, String param, AppService appService) {
        Gson gson = new Gson();
        AiMessageRequest aiMessageRequest = gson.fromJson(param, AiMessageRequest.class);
        if (aiMessageRequest ==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数错误");
        }
        return appService.definitionRequestUsingPost(url,aiMessageRequest,appService);
    }
}
