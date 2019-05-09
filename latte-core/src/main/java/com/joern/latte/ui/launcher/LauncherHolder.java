package com.joern.latte.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by Joern on 2018/12/23.
 *  这种Holder结构类似RecycleView的逻辑
 */

public class LauncherHolder implements Holder<Integer> {
    //这里每一个子View都是一张图片，故声明一个ImageView
    private AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    //
    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        //设置Background是方便占整个屏幕，不使用src
        mImageView.setBackgroundResource(data);
    }
}
