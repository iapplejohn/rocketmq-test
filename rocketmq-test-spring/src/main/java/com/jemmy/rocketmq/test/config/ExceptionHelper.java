package com.jemmy.rocketmq.test.config;

import com.jemmy.rocketmq.test.exception.DataConflictException;
import com.jemmy.rocketmq.test.exception.DataNotFoundException;
import com.jemmy.rocketmq.test.exception.NotLoginException;
import com.jemmy.rocketmq.test.web.vo.ApiResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author chengzhujiang
 * @since  2019/8/9
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse illegalArgExceptionHandle(IllegalArgumentException ex) {
        log.warn("参数错误：" + ex.getMessage(), ex);
        if (log.isDebugEnabled()) {
            log.debug("参数错误", ex);
        }
        String message = StringUtils.isBlank(ex.getMessage()) ? "参数错误!" : ex.getMessage();
        return ApiResponse.error(400, message);
    }

    @ExceptionHandler(NotLoginException.class)
    public ApiResponse noLogin(NotLoginException e) {
        log.warn("未登录：" + e.getMessage());
        return ApiResponse.error(40401, "No Login !");
    }

    @ExceptionHandler(value = {DataNotFoundException.class})
    public ApiResponse dataNotFoundExceptionHandle(Exception e) {
        log.warn("[DataNotFoundException]Exception:", e);
        return ApiResponse.error(20404, e.getMessage());
    }

    @ExceptionHandler(value = {DataConflictException.class})
    public ApiResponse dataConflictExceptionHandle(Exception e) {
        log.warn("[DataConflictException]Exception:", e);
        return ApiResponse.error(20406, e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuilder errorMsg = new StringBuilder();
        errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
        return ApiResponse.error(20400, errorMsg.toString());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse commonExceptionHandle(Exception ex) {
        log.warn("系统异常", ex);
        return ApiResponse.error(500, "系统异常");
    }
}

