package com.mi.miapi_client_sdk;

import com.mi.miapi_client_sdk.appservice.impl.AppServiceImpl;
import com.mi.miapi_client_sdk.client.MiApiClient;
import com.mi.miapi_client_sdk.strategy.BaseContext;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author mi11
 * @version 1.0
 * @project miapi_client_sdk
 * @description clent配置类
 * @ClassName MiApiClientConfig
 */
@Configuration
@ConfigurationProperties(prefix = "miapi.client")
@Data
@ComponentScan
public class MiApiClientConfig {

    private String  accessKey;

    private String  secretKey;


    @Bean
    public MiApiClient miApiClient(){
        return new MiApiClient(accessKey,secretKey);
    }

    @Bean
    public BaseContext baseContext(){
        AppServiceImpl appService = new AppServiceImpl();
        appService.setMiApiClient(new MiApiClient(accessKey,secretKey));
        BaseContext baseContext = new BaseContext();
        baseContext.setAppService(appService);
        return baseContext;
    }
}
