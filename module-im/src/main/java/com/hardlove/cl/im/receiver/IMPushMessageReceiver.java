package com.hardlove.cl.im.receiver;

import android.content.Context;

import com.jess.arms.utils.ArmsUtils;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * Created by Chenlu on 2018/11/20
 * Email:chenlu@globalroam.com
 */
public class IMPushMessageReceiver extends PushMessageReceiver {
    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        ArmsUtils.makeText(context,"onNotificationMessageArrived==>"+pushNotificationMessage.getPushContent());
        return false; // 返回 false, 会弹出融云 SDK 默认通知; 返回 true, 融云 SDK 不会弹通知, 通知需要由您自定义。
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        ArmsUtils.makeText(context,"onNotificationMessageClicked==>"+pushNotificationMessage.getPushContent());
        return false; // 返回 false, 会走融云 SDK 默认处理逻辑, 即点击该通知会打开会话列表或会话界面; 返回 true, 则由您自定义处理逻辑。
    }
}
