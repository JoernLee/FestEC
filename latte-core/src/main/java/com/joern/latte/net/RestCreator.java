package com.joern.latte.net;

import com.joern.latte.app.ConfigKeys;
import com.joern.latte.app.Latte;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Joern on 2018/12/09.
 * 利用Retrofit创建的服务，对外提供配置参数以及Rest服务(自己定义的服务接口)！
 */

public class RestCreator {

    //懒汉式加载一下更好
    //public static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RetrofitHolder {
        //通过工具类获取我们配置的APIHOST
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigKeys.API_HOST.name());
        //这里可以借助建造者进行一些自定义操作，比如添加拦截器,最后生成Retrofit
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    //这里的建造者模式我们也可以添加我们自己的拦截器
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);

        //把全局配置项里面的拦截器利用建造者模式添加进去
        private static OkHttpClient.Builder addInterceptors(){
            if (INTERCEPTORS!=null&&!INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor:INTERCEPTORS){
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }
        //生成配置后的OKHttpClient
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptors()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
