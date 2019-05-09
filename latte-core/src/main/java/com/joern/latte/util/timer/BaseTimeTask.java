package com.joern.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by Joern on 2018/12/22.
 */

public class BaseTimeTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimeTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }


    @Override
    public void run() {
        if (mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}
