package com.joern.latte.ui.launcher;

/**
 * Created by Joern on 2019/05/09.
 */

public interface ILauncherListener {
    //到底是什么状态的登陆Finish？
    void onLauncherFinish(OnLauncherFinishTag tag);
}
