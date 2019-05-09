package com.joern.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Joern on 2018/12/02.
 */

public final class Latte {

    //项目Module来调用，在全局配置里面存入当前项目的上下文并将全局配置工具返回给项目使用。
    public static Configurator init(Context context){
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

    //获取全局配置项
    public static HashMap<Object,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    //获取全局配置器
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    //获取指定配置项
    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    //获取应用上下文
    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT);
    }
}
