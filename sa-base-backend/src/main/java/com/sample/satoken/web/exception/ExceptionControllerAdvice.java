package com.sample.satoken.web.exception;

import cn.dev33.satoken.exception.*;
import cn.dev33.satoken.util.SaResult;
import com.sample.satoken.config.i18n.I18nMessageSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private I18nMessageSource i18nMessageSource;

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
        Integer errorCode = HttpStatus.BAD_REQUEST.value();
        String errorMessage = i18nMessageSource.getText(errorCode);
        Map<String, String> tips = new HashMap<>(1);
        tips.put(e.getParameterName(), e.getMessage());
        if (StringUtils.isNotBlank(e.getMessage())) {
            errorMessage = e.getMessage();
        }
        return SaResult.code(errorCode)
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
        Integer errorCode = HttpStatus.BAD_REQUEST.value();
        String errorMessage = i18nMessageSource.getText(errorCode);
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> tips = new HashMap<>(e.getBindingResult().getAllErrors().size());
        bindingResult.getFieldErrors().forEach(error -> {
            tips.put(error.getField(), error.getDefaultMessage());
        });
        if (null != tips && !tips.isEmpty()) {
            errorMessage = tips.values().iterator().next();
        }
        return SaResult.code(errorCode)
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
        Integer errorCode = HttpStatus.BAD_REQUEST.value();
        String errorMessage = i18nMessageSource.getText(errorCode);
        Map<String, String> tips = new HashMap<>(e.getConstraintViolations().size());
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        errors.forEach(error -> {
            tips.put(error.getPropertyPath().toString(), error.getMessage());
        });
        if (null != errors && !errors.isEmpty()) {
            errorMessage = errors.iterator().next().getMessage();
        }
        return SaResult.code(errorCode)
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
        Integer errorCode = HttpStatus.UNAUTHORIZED.value();
        String errorMessage = i18nMessageSource.getText(errorCode);
        return SaResult.code(errorCode)
                .setMsg(errorMessage);
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
        Integer errorCode = HttpStatus.UNAUTHORIZED.value();
        String errorMessage = i18nMessageSource.getText(errorCode);
        return SaResult.code(errorCode)
                .setMsg(errorMessage);
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
        Integer errorCode = HttpStatus.FORBIDDEN.value();
        String errorMessage = i18nMessageSource.getText(errorCode);
        return SaResult.code(errorCode)
                .setMsg(errorMessage);
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
        Integer errorCode = HttpStatus.FORBIDDEN.value();
        String errorMessage = i18nMessageSource.getText(errorCode);
        return SaResult.code(errorCode)
                .setMsg(errorMessage);
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
        Integer errorCode = HttpStatus.FORBIDDEN.value();
        String errorMessage = i18nMessageSource.getText(errorCode);
        return SaResult.code(errorCode)
                .setMsg(errorMessage);
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
        Integer errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String errorMessage = i18nMessageSource.getText(errorCode);
        return SaResult.code(errorCode)
                .setMsg(errorMessage);
    }
}
