package com.mi.miapi_client_sdk.client;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.mi.miapi_client_sdk.util.SignUtil;
import com.mi.miapi_common.model.dto.GetNameRequest;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * @author mi11
 * @version 1.0
 * @project miApi_interface
 * @description 测试接口类
 * @ClassName MiApiClient
 */
@Data
public class MiApiClient {

    private String  accessKey;

    private String  secretKey;

    final String BASEURL = "http://localhost:8090";

    public MiApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String resName= HttpUtil.get(BASEURL + "/name/", paramMap);
        return "get方式"+resName;
    }


    public String getNameByPost(String name){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result= HttpUtil.post(BASEURL + "/name/", paramMap);
        return "post方式"+result;
    }

    public Map<String,String> getHeaderMap() throws UnsupportedEncodingException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", URLEncoder.encode(accessKey, CharsetUtil.UTF_8));
        hashMap.put("sign", SignUtil.genSign("api"+secretKey));
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        return hashMap;
    }


    public String getNameByRestPost(String name) throws UnsupportedEncodingException {;
        GetNameRequest getNameRequest = new GetNameRequest();
        getNameRequest.setName(name);
        String url = BASEURL + "/name/post/rest";
        HttpResponse httpResponse = HttpRequest.post(url)
                .addHeaders(getHeaderMap())
                .body(JSONUtil.toJsonStr(getNameRequest))
                .execute();
        System.out.println(httpResponse.getStatus());
        return "post方式RESTFUL"+httpResponse.body();
    }

}
