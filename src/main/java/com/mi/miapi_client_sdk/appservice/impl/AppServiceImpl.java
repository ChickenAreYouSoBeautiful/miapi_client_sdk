package com.mi.miapi_client_sdk.appservice.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mi.miapi_client_sdk.appservice.AppService;
import com.mi.miapi_client_sdk.client.MiApiClient;
import com.mi.miapi_client_sdk.constant.MiApiConstant;
import com.mi.miapi_client_sdk.util.SignUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


/**
 * @author mi11
 * @version 1.0
 * @project miapi_client_sdk
 * @description Api接口实现
 * @ClassName AppServiceImpl
 */
@Data
@Slf4j
public class AppServiceImpl implements AppService {

    private MiApiClient miApiClient;


    private String host = MiApiConstant.GATEWAY_HOST;


    public Map<String,String> getHeaderMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        String accessKey = miApiClient.getAccessKey();
        String secretKey = miApiClient.getSecretKey();
        try {
            hashMap.put("accessKey", URLEncoder.encode(accessKey, CharsetUtil.UTF_8));
        } catch (UnsupportedEncodingException e) {
          log.error("加密传递错误：异常信息："+e.getMessage());
          throw new RuntimeException("加密传递错误");
        }
        hashMap.put("sign", SignUtil.genSign("api"+secretKey));
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        return hashMap;
    }


    @Override
    public <T> String definitionRequestUsingPost(String url, T param, AppService appService) {
        log.info("请求url:{},请求参数:{}",url,param);
        String jsonParam = JSONUtil.toJsonStr(param);
            HttpResponse response = HttpRequest.post(host + url)
                    .addHeaders(this.getHeaderMap())
                    .body(jsonParam)
                    .execute().charset(StandardCharsets.UTF_8);
            log.info("请求成功:{}",response.body());
        return response.body();
    }

    @Override
    public <T> String definitionRequestUsingGet(String url, T param, AppService appService) {
        log.info("请求url:{},请求参数:{}",url,param);
        String jsonParam = JSONUtil.toJsonStr(param);
        JSONObject entries = JSONUtil.parseObj(jsonParam);
        Map<String,Object> formData = entries.toBean(Map.class);
        HttpResponse response = HttpRequest.get(host + url)
                .addHeaders(this.getHeaderMap())
                .form(formData)
                .execute().charset(StandardCharsets.UTF_8);
        log.info("请求成功:{}",response.body());
        return response.body();
    }

}
