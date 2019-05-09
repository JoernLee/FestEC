package com.joern.latte.app;

import com.joern.latte.util.stroage.LattePreference;

/**
 * Created by Joern on 2019/05/09.
 */

public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    //保存用户登陆状态，登陆之后调用
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            //如果已经登陆了，执行登陆的回调
            checker.onSignIn();
        }else{
            checker.onNotSignIn();
        }
    }

}
