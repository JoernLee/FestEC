package com.joern.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.joern.latte.app.AccountManager;
import com.joern.latte.app.IUserChecker;
import com.joern.latte.delegates.LatteDelegate;
import com.joern.latte.ec.R;
import com.joern.latte.ui.launcher.ILauncherListener;
import com.joern.latte.ui.launcher.LauncherHolderCreator;
import com.joern.latte.ui.launcher.OnLauncherFinishTag;
import com.joern.latte.ui.launcher.ScrollLauncherTag;
import com.joern.latte.util.stroage.LattePreference;

import java.util.ArrayList;

/**
 * Created by Joern on 2018/12/23.
 * 轮播启动页
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    //开源库中要求的，传入的是图片，泛型是Interger
    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        //需要配置启动页图片、启动页提示点（这里使用了代码生成shape来代替直接放图片-快且占用空间小）
        //还设置了下面提示点的位置-水平居中
        //设置了点击事件接听
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个（这里通过一个标记进行判断）
        if (position == INTEGERS.size()-1){
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //下面应该检查用户是否已经登陆
            //检查用户是否已经登陆
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }
}
