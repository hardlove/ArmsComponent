package me.jessyan.armscomponent.commonres.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebResourceError;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import java.util.HashMap;

import me.jessyan.armscomponent.commonres.R;
import me.jessyan.armscomponent.commonres.view.MyWebView;
import me.jessyan.armscomponent.commonres.view.PageLayout;

/**
 * Created by Chenlu on 2018/12/19
 * Email:chenlu@globalroam.com
 */
public class WebActivity extends BaseActivity {


    private PageLayout mPageLayout;

    public static void lunchActivity(@NonNull Activity activity, @NonNull String url, @Nullable HashMap<String, String> headers) {
        Intent intent = new Intent(activity, WebActivity.class);
        intent.putExtra("url", url);
        if (headers != null && !headers.isEmpty()) {
            intent.putExtra("headers", headers);
        }
        activity.startActivity(intent);
    }

    private MyWebView mMyWebView;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.public_web_activity;
    }

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mMyWebView = findViewById(R.id.myWebView);
        String url = getIntent().getStringExtra("url");
        HashMap<String, String> headers = (HashMap<String, String>) getIntent().getSerializableExtra("headers");
        if (headers != null && !headers.isEmpty()) {
            mMyWebView.loadUrl(url, headers);
        } else {
            mMyWebView.loadUrl(url);
        }
        mPageLayout = new PageLayout.Builder(this)
                .initPage(mMyWebView).create();
        mMyWebView.setOnWebListener(new MyWebView.OnWebListener() {
            @Override
            public void onPageStarted() {
                mPageLayout.showLoading();
            }

            @Override
            public void onPageFinished() {
                mPageLayout.hide();
            }

            @Override
            public void onProgressChanged(int progress) {
            }

            @Override
            public void onReceivedError(WebResourceError error) {
                mPageLayout.showError();
            }

            @Override
            public void onReceivedSslError(SslError error) {
                mPageLayout.showError();
            }

            @Override
            public void onReceivedTitle(String title) {

            }

            @Override
            public void onOverrideUrl(String url) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyWebView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMyWebView.onPause();
    }

    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mMyWebView.canGoBack()) {
            mMyWebView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        } else {
            finish();
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMyWebView.removeAllViews();
        mMyWebView.destroy();
        mMyWebView = null;
    }
}
