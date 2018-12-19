package com.joern.latte.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.joern.latte.app.Latte;
import com.joern.latte.net.callback.IRequest;
import com.joern.latte.net.callback.ISuccess;
import com.joern.latte.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Joern on 2018/12/19.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir.equals("")){
            downloadDir = "down_loads";
        }
        if (extension == null|| extension.equals("")){
            extension = "";
        }
        if (name == null){
            //下面的是作者的文件工具类--有时间可以自己去看看
            return FileUtil.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }else {
            return FileUtil.writeToDisk(is,downloadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS!= null){
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    //如果下载的是apk文件，可以提供自动安装的功能
    private void autoInstallApk(File file){
        if (FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            //application/vnd.android.package-archive是文件类型，具体对应apk类型。
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive" );
            Latte.getApplicationContext().startActivity(install);
        }
    }

}
