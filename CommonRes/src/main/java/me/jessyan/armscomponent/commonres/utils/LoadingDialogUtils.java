package me.jessyan.armscomponent.commonres.utils;

import android.app.Activity;
import android.app.ProgressDialog;

import me.jessyan.armscomponent.commonres.R;

/**
 * Created by CL on 2017/8/25.
 */

public class LoadingDialogUtils {
    public static ProgressDialog showLoadingDialog(Activity activity) {
       return showLoadingDialog(activity,activity.getString(R.string.public_loading));
    }
    public static ProgressDialog showLoadingDialog(Activity activity, String msg) {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(msg);
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);//// 设置在点击Dialog外是否取消Dialog进度条
        dialog.show();
        return dialog;
    }
}
