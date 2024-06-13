package com.mi.miapi_client_sdk.strategy;

import com.mi.miapi_client_sdk.appservice.AppService;
import com.mi.miapi_client_sdk.constant.MiApiConstant;
import com.mi.miapi_client_sdk.strategy.impl.AiMessageStrategy;
import com.mi.miapi_client_sdk.strategy.impl.GetNameStrategy;
import com.mi.miapi_client_sdk.strategy.impl.IpStrategy;
import com.mi.miapi_client_sdk.strategy.impl.SimilarityStrategy;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mi11
 * @version 1.0
 * @project miapi_client_sdk
 * @description 定义策略环境
 * @ClassName BaseContext
 */
@Data
public class BaseContext {

    private AppService appService;

    private static final Map<String,BaseStrategy> STRATEGY_MAP = new HashMap();

    static {
        STRATEGY_MAP.put(MiApiConstant.BASE_PATH_POST_NAME,new GetNameStrategy());
        STRATEGY_MAP.put(MiApiConstant.BASE_PATH_POST_SIMILARITY,new SimilarityStrategy());
        STRATEGY_MAP.put(MiApiConstant.BASE_PATH_POST_AI_MESSAGE,new AiMessageStrategy());
        STRATEGY_MAP.put(MiApiConstant.BASE_PATH_GET_IP,new IpStrategy());
    }

    public String handlerRequest(String url, String param){
        BaseStrategy baseStrategy = STRATEGY_MAP.get(url);
        return baseStrategy.definitionRequest(url,param,appService);
    }
}
