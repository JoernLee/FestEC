package com.joern.festec.example;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.joern.latte.activities.ProxyActivity;
import com.joern.latte.app.Latte;
import com.joern.latte.delegates.LatteDelegate;
import com.joern.latte.ec.launcher.LauncherDelegate;
import com.joern.latte.ec.launcher.LauncherScrollDelegate;
import com.joern.latte.ec.sign.ISignListener;
import com.joern.latte.ec.sign.SignInDelegate;
import com.joern.latte.ec.sign.SignUpDelegate;
import com.joern.latte.ui.launcher.ILauncherListener;
import com.joern.latte.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                Toast.makeText(this,"启动结束，用户登陆了",Toast.LENGTH_LONG).show();
                startWithPop(new ExampleDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this,"启动结束，用户没登陆",Toast.LENGTH_LONG).show();
                //启动新的Delegate，然后把上一个Delegate彻底清除（此时启动图已经没用了）
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
