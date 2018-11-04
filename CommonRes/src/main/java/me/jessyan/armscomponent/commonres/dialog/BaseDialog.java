package me.jessyan.armscomponent.commonres.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;

import com.jess.arms.utils.ArmsUtils;

import me.jessyan.armscomponent.commonres.R;

/**
 * Created by Chenlu on 2018/11/4
 * Email:chenlu@globalroam.com
 */
public class BaseDialog extends Dialog {
    private float mWidthScale = 1.0f;
    private int mWidth;

    public BaseDialog(@NonNull Context context) {
        this(context, R.style.public_base_dialog);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setWidthScale(mWidthScale);
    }

    /**
     * 设置弹窗宽度相对屏幕宽度显示的比例
     *
     * @param widthScale
     */
    public void setWidthScale(float widthScale) {
        mWidthScale = widthScale;
        mWidth = (int) (mWidthScale * ArmsUtils.getScreenWidth(getContext()));

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = mWidth;
        window.setAttributes(lp);
    }

    public void setGravity(int gravity) {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = gravity;
        window.setAttributes(lp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
