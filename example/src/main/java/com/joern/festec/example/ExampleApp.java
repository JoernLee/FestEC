package com.joern.festec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joern.latte.app.Latte;
import com.joern.latte.ec.database.DatabaseManager;
import com.joern.latte.ec.icon.FontECModule;
import com.joern.latte.net.interceptors.DebugInterceptor;

/**
 * Created by Joern on 2018/12/02.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //通过我们这种设计，如果需要加入别的配置的话，直接.调用相关配置方法就可以了，十分方便！
        Latte.init(this)
                .withIcon(new FontECModule())
                .withIcon(new FontAwesomeModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
        DatabaseManager.getInstance().init(this);
        initStetho();
    }

    private void initStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }

}
