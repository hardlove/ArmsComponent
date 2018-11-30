package com.hardlove.cl.fooddefender.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.hardlove.cl.fooddefender.BuildConfig;
import com.hardlove.cl.fooddefender.mvp.model.api.Api;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.utils.ArmsUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * ================================================
 * 展示 {@link AppLifecycles} 的用法
 * <p>
 * Created by ArmsComponentTemplate
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class AppLifecyclesImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        //当所有模块集成到宿主 App 时, 在 App 中已经执行了以下代码
        if (BuildConfig.IS_BUILD_MODULE) {
            //leakCanary内存泄露检查
            ArmsUtils.obtainAppComponentFromContext(application).extras().put(RefWatcher.class.getName(), BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);
        }
        //使用 RetrofitUrlManager 切换 BaseUrl
        RetrofitUrlManager.getInstance().putDomain(Api.DEFENDER_DOMAIN_NAME, Api.DEFENDER_DOMAIN);

    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
