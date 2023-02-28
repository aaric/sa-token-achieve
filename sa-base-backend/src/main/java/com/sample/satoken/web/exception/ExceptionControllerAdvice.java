package com.sample.satoken.web.exception;

import cn.dev33.satoken.exception.*;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 异常捕获
 *
 * @author Aaric, created on 2022-11-30T17:06.
 * @version 0.2.0-SNAPSHOT
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 数据校验（参数）异常-400
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public SaResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("handleMissingServletRequestParameterException", e);
        Map<String, String> tips = new HashMap<>(1);
        tips.put(e.getParameterName(), e.getMessage());
        String errorMessage = "数据校验失败";
        if (StringUtils.isNotBlank(e.getMessage())) {
            errorMessage = e.getMessage();
        }
        return SaResult.code(HttpStatus.BAD_REQUEST.value())
                .setMsg(errorMessage)
                .setData(tips);
    }

    /**
     * 数据校验（方法）异常-400
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SaResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> tips = new HashMap<>(e.getBindingResult().getAllErrors().size());
        bindingResult.getFieldErrors().forEach(error -> {
            tips.put(error.getField(), error.getDefaultMessage());
        });
        String errorMessage = "数据校验失败";
        if (null != tips && !tips.isEmpty()) {
            errorMessage = tips.values().iterator().next();
        }
        return SaResult.code(HttpStatus.BAD_REQUEST.value())
                .setMsg(errorMessage)
                .setData(tips);
    }

    /**
     * 数据校验（违反约束）异常-400
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public SaResult handleConstraintViolationException(ConstraintViolationException e) {
        log.error("handleConstraintViolationException", e);
        Map<String, String> tips = new HashMap<>(e.getConstraintViolations().size());
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        errors.forEach(error -> {
            tips.put(error.getPropertyPath().toString(), error.getMessage());
        });
        String errorMessage = "数据校验失败";
        if (null != errors && !errors.isEmpty()) {
            errorMessage = errors.iterator().next().getMessage();
        }
        return SaResult.code(HttpStatus.BAD_REQUEST.value())
                .setMsg(errorMessage)
                .setData(tips);
    }

    /**
     * Sa-Token 未登录异常-401
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotLoginException.class)
    public SaResult handleNotLoginException(NotLoginException e) {
        log.error("handleNotLoginException", e);
        return SaResult.code(HttpStatus.UNAUTHORIZED.value())
                .setMsg("未登录或Token无效");
    }

    /**
     * Sa-Token Http Basic 认证失败异常-403
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotBasicAuthException.class)
    public SaResult handleNotBasicAuthException(NotBasicAuthException e) {
        log.error("handleNotBasicAuthException", e);
        return SaResult.code(HttpStatus.UNAUTHORIZED.value())
                .setMsg("Http Basic 认证失败");
    }

    /**
     * Sa-Token 二级认证失败异常-403
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotSafeException.class)
    public SaResult handleNotSafeException(NotSafeException e) {
        log.error("handleNotSafeException", e);
        return SaResult.code(HttpStatus.FORBIDDEN.value())
                .setMsg("二级认证失失败");
    }

    /**
     * Sa-Token 无角色异常-403
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotRoleException.class)
    public SaResult handleNotRoleException(NotRoleException e) {
        log.error("handleNotRoleException", e);
        return SaResult.code(HttpStatus.FORBIDDEN.value())
                .setMsg("无权限访问");
    }

    /**
     * Sa-Token 无权限异常-403
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotPermissionException.class)
    public SaResult handleNotPermissionException(NotPermissionException e) {
        log.error("handleNotPermissionException", e);
        return SaResult.code(HttpStatus.FORBIDDEN.value())
                .setMsg("无权限访问");
    }

    /**
     * 其他异常-500
     *
     * @param e 异常信息
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public SaResult handleDefaultException(Exception e) {
        log.error("handleDefaultException", e);
        return SaResult.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMsg("未知错误");
    }
}
