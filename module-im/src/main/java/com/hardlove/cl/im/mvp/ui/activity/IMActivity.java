package com.hardlove.cl.im.mvp.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.hardlove.cl.im.R;
import com.hardlove.cl.im.di.component.DaggerIMComponent;
import com.hardlove.cl.im.di.module.IMModule;
import com.hardlove.cl.im.mvp.contract.IMContract;
import com.hardlove.cl.im.mvp.presenter.IMPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import me.jessyan.armscomponent.commonsdk.utils.ProcessUtils;

import static com.squareup.haha.guava.base.Joiner.checkNotNull;


public class IMActivity extends BaseActivity<IMPresenter> implements IMContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerIMComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .iMModule(new IMModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.im_activity_im; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    public void LoginIM(View view) {
        String userId = "user_id_1";
        String token = "NFron1Zef0CnaxVbD3MQ2Zz/ywqWaA30O2E9H001KP3o5QGjKADroWjbkRPeAI6SI78AraZs6HvBiYv6QCXfLJ9ubwk90HMi";
        connect(token);
        showMessage("登陆。。。");
    }


    private void connect(String token) {
        if (ProcessUtils.isMainProcess(this)) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.d(TAG, "onTokenIncorrect~~~~~~~~~~~~");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d(TAG, "--onSuccess" + userid);
                    startActivity(new Intent(IMActivity.this, ConversionListActivity.class));
                    finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    showMessage("连接融云失败:" + errorCode.getMessage() + " :" + errorCode.getValue());
                    Log.d(TAG, "连接融云失败:" + errorCode.getMessage() + " :" + errorCode.getValue());
                }
            });
        }

    }

    private static final String TAG = "IMActivity";
}
