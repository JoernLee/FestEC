package com.joern.latte.app;

/**
 * Created by Joern on 2018/12/02.
 */

/**
 * API_HOST:配置一些网络请求，比如域名
 * APPLICATION_CONTEXT:全局的上下文
 * CONFIG_READY:控制配置或者初始化完成了没有
 * ICON:我们自己的一些配置
 */
public enum ConfigType {
    API_HOST,
    APPLICATION_CONTEXT,
    CONFIG_READY,
    ICON
}
