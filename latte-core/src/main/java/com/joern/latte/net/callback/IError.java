package com.joern.latte.net.callback;

/**
 * Created by Joern on 2018/12/09.
 */

public interface IError {
    void onError(int code, String msg);
}
