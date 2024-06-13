package com.mi.miapi_client_sdk.exception;

import cn.hutool.core.io.IORuntimeException;
import com.mi.miapi_common.common.BaseResponse;
import com.mi.miapi_common.common.ErrorCode;
import com.mi.miapi_common.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author <a href="https://github.com/limi">mi11</a>
 * @from <a href="https://mi.icu">编程导航知识星球</a>
 */
@RestControllerAdvice
@Slf4j
public class ApiGlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(IORuntimeException.class)
    public BaseResponse<?> ioRuntimeExceptionHandler(IORuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "连接网关失败");
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
