package com.sample.satoken.config.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * I18n 本地化消息
 *
 * @author Aaric, created on 2023-03-02T16:10.
 * @version 0.13.0-SNAPSHOT
 */
@Component
public class I18nMessageSource {

    @Autowired
    protected MessageSource messageSource;

    /**
     * 获取本地化内容
     *
     * @param code 消息码
     * @return
     */
    public String getText(Integer code) {
        return getText("tips.error." + code, LocaleContextHolder.getLocale());
    }

    /**
     * 获取本地化内容
     *
     * @param key 消息键
     * @return
     */
    public String getText(String key) {
        return getText(key, LocaleContextHolder.getLocale());
    }

    /**
     * 获取本地化内容
     *
     * @param key    消息键
     * @param locale 地域
     * @return
     */
    public String getText(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }
}
