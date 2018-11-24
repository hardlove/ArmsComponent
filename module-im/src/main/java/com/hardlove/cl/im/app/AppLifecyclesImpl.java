package com.hardlove.cl.im.app;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.hardlove.cl.im.BuildConfig;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.utils.ArmsUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;
import me.jessyan.armscomponent.commonsdk.utils.ProcessUtils;

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
        if (BuildConfig.IS_BUILD_MODULE)
            MultiDex.install(base);  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化


    }

    @Override
    public void onCreate(@NonNull Application application) {
        if (ProcessUtils.isMainProcess(application)) {
            initRongIM(application);

        }
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

    }

    private void initRongIM(@NonNull Application application) {
        //初始化融云IM SDK
        RongIM.init(application);
        Log.d("初始化", "初始化融云IM SDK");

        //获取用户信息的方式有两种，只能采用其中的一种，https://www.rongcloud.cn/docs/android.html#user_info
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String userId) {
                //很多时候 getUserInfo 这个方法会去 App 服务器异步获取用户信息，不能实时返回用户信息。
                // 这种情况下，请在成功获取到用户信息的异步回调中使用下面方法来刷新信息
                //RongIM.getInstance().refreshUserInfoCache(new UserInfo("userId", "啊明", Uri.parse("http://rongcloud-web.qiniudn.com/docs_demo_rongcloud_logo.png")));
                RongIM.getInstance().refreshUserInfoCache(new UserInfo("user1", "啊明", Uri.parse("http://rongcloud-web.qiniudn.com/docs_demo_rongcloud_logo.png")));
                RongIM.getInstance().refreshUserInfoCache(new UserInfo("user2", "猫咪", Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542971960698&di=998f44ceeffc2b4e8a8d42e5dd341643&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201312%2F05%2F20131205172320_V2P3N.thumb.700_0.jpeg")));

                return null;
            }
        }, true);//设置为ture，IMKit会自动缓存User信息到缓存中，避免每次都网络请求信息
        RongIM.setGroupInfoProvider(new RongIM.GroupInfoProvider() {
            @Override
            public Group getGroupInfo(String s) {
                // 刷新群组缓存数据。
                // RongIM.getInstance().refreshGroupInfoCache(Group group)
                return null;
            }
        }, true);
        RongIM.setLocationProvider(new RongIM.LocationProvider() {
            @Override
            public void onStartLocation(Context context, LocationCallback locationCallback) {

            }
        });//设置地理位置提供者,不用位置的同学可以注掉此行代码

        //设置支持消息回执的会话类型。目前只支持 PRIVATE、GROUP 和 DISCUSSION 三种类型，如果不设置的话，默认只有 PRIVATE 类型的会话支持消息回执。
        RongIM.getInstance().setReadReceiptConversationTypeList(Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP,
                Conversation.ConversationType.DISCUSSION);
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
