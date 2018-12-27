package me.jessyan.armscomponent.commonres.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jess.arms.utils.ArmsUtils;

import java.util.Map;

import me.jessyan.armscomponent.commonres.BuildConfig;
import me.jessyan.armscomponent.commonsdk.utils.MLogger;
import me.jessyan.autosize.AutoSize;

/**
 * time 2018/11/29.
 * author hmily
 * description:
 */
public class MyWebView extends WebView {
    private OnWebListener listener;
    private boolean resetImgWidthEnable = false;
    private boolean imgClickEnable = false;

    public MyWebView(Context context) {
        super(context);
        init();
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setDefaultWebSettings(this);
        this.setWebChromeClient(mWebChromeClient);
        this.setWebViewClient(mWebViewClient);
    }

    @Override
    public void setOverScrollMode(int mode) {
        super.setOverScrollMode(mode);
        AutoSize.autoConvertDensityOfGlobal(ArmsUtils.obtainAppComponentFromContext(getContext()).appManager().getCurrentActivity());
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setDefaultWebSettings(WebView webView) {
        WebSettings webViewSettings = webView.getSettings();
        //5.0以上开启混合模式加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//解决Android5.0以上图片不显示问题
            webViewSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //是否允许WebView度超出以概览的方式载入页面
        webViewSettings.setLoadWithOverviewMode(true);
        webViewSettings.setUseWideViewPort(true);
        //允许js代码
        webViewSettings.setJavaScriptEnabled(true);
        //允许SessionStorage和LocalStorage存储
        webViewSettings.setDomStorageEnabled(true);
        //允许数据库存储
        webViewSettings.setDatabaseEnabled(true);
        //禁用缩放
        webViewSettings.setDisplayZoomControls(false);
        webViewSettings.setBuiltInZoomControls(false);
        //禁用文字缩放
        webViewSettings.setTextZoom(100);
        //10M缓存，api 18后，系统自动管理
        webViewSettings.setAppCacheMaxSize(10 * 1024 * 1024);
        //允许缓存，设置缓存位置
        webViewSettings.setAppCacheEnabled(true);
        webViewSettings.setAppCachePath(getContext().getDir("appcache", 0).getPath());
        //允许WebView使用File协议
        webViewSettings.setAllowFileAccess(true);
        //不保存密码
        webViewSettings.setSavePassword(false);
        //设置UA
        webViewSettings.setUserAgentString(webViewSettings.getUserAgentString() + getContext().getPackageName() + "/" + BuildConfig.VERSION_NAME);
        //自动加载图片
        webViewSettings.setLoadsImagesAutomatically(true);
        //定位是否可用
        webViewSettings.setGeolocationEnabled(true);
        //是否禁止从网络（通过http和https URI schemes访问的资源）下载图片资源
        webViewSettings.setBlockNetworkImage(false);

        webViewSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webViewSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        //防止远程执行漏洞
        removeJavascriptInterfaces(webView);

    }

    /**
     * 如果启用了JavaScript，务必做好安全措施，防止远程执行漏洞
     */
    @TargetApi(11)
    private void removeJavascriptInterfaces(WebView webView) {
        try {
            if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT < 17) {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    @Override
    public void loadUrl(String url) {
        super.loadUrl(url);
    }

    @Override
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        super.loadUrl(url, additionalHttpHeaders);
    }

    @Override
    public void loadData(String data, @Nullable String mimeType, @Nullable String encoding) {
        super.loadData(data, mimeType, encoding);
    }

    public void loadData(String data) {
        this.loadData(data, "text/html;charset=UTF-8", null);
    }

    @Override
    public void loadDataWithBaseURL(@Nullable String baseUrl, String data, @Nullable String mimeType, @Nullable String encoding, @Nullable String historyUrl) {
        super.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }


    @Override
    public void setWebViewClient(WebViewClient webViewClient) {
        mWebViewClient = webViewClient;
    }

    @Override
    public void setWebChromeClient(WebChromeClient webChromeClient) {
        mWebChromeClient = webChromeClient;
    }

    public void setOnWebListener(OnWebListener listener) {
        this.listener = listener;
    }

    public void setResetImgWidthEnable(boolean resetImgWidthEnable) {
        this.resetImgWidthEnable = resetImgWidthEnable;
    }

    public void setImgClickEnable(boolean imgClickEnable) {
        this.imgClickEnable = imgClickEnable;
    }


    public interface OnWebListener {
        void onPageStarted();

        void onPageFinished();

        void onProgressChanged(int progress);

        void onReceivedError(WebResourceError error);

        void onReceivedSslError(SslError error);

        void onReceivedTitle(String title);

        void onOverrideUrl(String url);
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url;
            if (request != null && request.getUrl() != null) {
                url = request.getUrl().toString();
                if (listener != null) {
                    listener.onOverrideUrl(url);
                }
                view.loadUrl(url);
            }
            return true;
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (listener != null)
                listener.onPageStarted();
            if (imgClickEnable) {
                //添加图片点击拦截器
                view.addJavascriptInterface(new ImageJavaScript(getContext()), "imagelistner");
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (listener != null)
                listener.onPageFinished();

            if (resetImgWidthEnable) {
                imgReset(view);
            }
        }


        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            if (listener != null)
                listener.onReceivedError(error);

        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            if (error.getPrimaryError() == SslError.SSL_DATE_INVALID
                    || error.getPrimaryError() == SslError.SSL_EXPIRED
                    || error.getPrimaryError() == SslError.SSL_INVALID
                    || error.getPrimaryError() == SslError.SSL_UNTRUSTED) {
                handler.proceed();
            } else {
                if (listener != null)
                    listener.onReceivedSslError(error);
                handler.cancel();
            }
        }
    };

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (listener != null)
                listener.onProgressChanged(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (listener != null)
                listener.onReceivedTitle(title);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public void onPermissionRequest(PermissionRequest request) {
            super.onPermissionRequest(request);
        }
    };

    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private void imgReset(WebView webView) {
        webView.clearHistory();
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                "}" +
                "})()");
    }

    /**
     * 网页加载器图片点击监听接口
     */
    public static class ImageJavaScript {

        private Context context;

        public ImageJavaScript(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void openImage(String img) {
            MLogger.tag(MyWebView.class.getSimpleName()).d("openImage-->" + img);
            ArmsUtils.makeText(context, "openImage:" + img);
            //            Intent intent = PictureActivity.newIntent(context, img);
            //            context.startActivity(intent);
        }
    }


    /**
     * 调用js方法，不需要js返回数据
     *
     * @param funName 方法名称
     * @param params  参数字符串
     */
    public void callJsFun(String funName, String params) {
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:").append(funName).append("(");
        if (!TextUtils.isEmpty(params)) {
            sb.append("'").append(params).append("'");
        }
        sb.append(")");
        this.evaluateJavascript(sb.toString(), null);
    }

    public void callJsFun(String funName, String... params) {
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:").append(funName).append("(");
        for (String param : params) {
            if (!TextUtils.isEmpty(param)) {
                sb.append("'").append(param).append("'").append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        this.evaluateJavascript(sb.toString(), null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN && this.canGoBack()) {
            this.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
