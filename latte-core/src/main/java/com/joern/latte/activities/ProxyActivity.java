package com.joern.latte.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import me.yokeyword.fragmentation.SupportActivity;
import android.support.v7.widget.ContentFrameLayout;

import com.joern.latte.R;
import com.joern.latte.delegates.LatteDelegate;

/**
 * Created by Joern on 2018/12/06.
 */

//Activity的基类
public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        //该Id对应着RootFragment的容器Layout
        container.setId(R.id.delegate_container);
        setContentView(container);
        //第一次创建Activity时执行，加载Fragment（这个是Fragmentation库特有的）
        if (savedInstanceState == null ){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //这两个回收不一定执行，但还是可以写上去
        System.gc();
        System.runFinalization();
    }
}
