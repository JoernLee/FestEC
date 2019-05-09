package com.joern.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joern.latte.app.AccountManager;
import com.joern.latte.ec.database.DatabaseManager;
import com.joern.latte.ec.database.UserProfile;

/**
 * Created by Joern on 2019/05/09.
 */

public class SignHandler {
    public static void onSignIn(String response,ISignListener signListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        //初始化Entity,并插入到数据库
        //final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        //DatabaseManager.getInstance().getDao().insert(profile);

        //保存用户状态-已经注册并登陆成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }

    public static void onSignUp(String response,ISignListener signListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        //初始化Entity,并插入到数据库
        //final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        //DatabaseManager.getInstance().getDao().insert(profile);

        //保存用户状态-已经注册并登陆成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }
}
