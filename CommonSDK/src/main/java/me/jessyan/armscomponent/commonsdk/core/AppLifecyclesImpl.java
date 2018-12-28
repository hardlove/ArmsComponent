package me.jessyan.armscomponent.commonsdk.core;


import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.delegate.AppLifecycles;
import com.mob.MobSDK;

import butterknife.ButterKnife;
import me.jessyan.armscomponent.commonsdk.BuildConfig;
import me.jessyan.armscomponent.commonsdk.utils.MLogger;
import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

public class AppLifecyclesImpl implements AppLifecycles {
    @Override
    public void attachBaseContext(Context base) {

    }

    @Override
    public void onCreate(Application application) {
        if (BuildConfig.LOG_DEBUG) {//Timber日志打印
//            Timber.plant(new Timber.DebugTree());// 注意：Timber多次初始化会重复打印日志
            MLogger.init();//初始化Timber
            ButterKnife.setDebug(true);
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            RetrofitUrlManager.getInstance().setDebug(true);
        }
        ARouter.init(application); // 尽可能早,推荐在Application中初始化

        MobSDK.init(application, "288cd81f31bf8", "70b8425b3005294dd1ab489d0108775f");
        configAutosize();


    }

    private void configAutosize() {
        //设置对AutoSize 对mm的支持
        AutoSizeConfig.getInstance()
                .setUseDeviceSize(false)
                .getUnitsManager()
                .setSupportDP(false)
                .setSupportSP(false)
                .setSupportSubunits(Subunits.MM);
    }

    @Override
    public void onTerminate(Application application) {

    }
}
