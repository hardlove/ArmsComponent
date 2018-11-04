package me.jessyan.armscomponent.commonsdk.share;

import android.content.Context;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by Chenlu on 2018/11/3
 * Email:chenlu@globalroam.com
 */
public class ShareManager {

    private static ShareManager mShareManager;
    private Platform mCurrentPlatform;

    private ShareManager() {
    }

    public static ShareManager getInstance() {
        if (mShareManager == null) {
            synchronized (ShareManager.class) {
                if (mShareManager == null) {
                    mShareManager = new ShareManager();
                }
            }
        }
        return mShareManager;
    }

    public enum PlatformType {
        Wechat, WechatMoments, SinaWeibo, QQ, QZone
    }

    /***
     * 分享数据入口
     * @param shareData
     * @param listener
     */
    public void share(ShareData shareData, PlatformActionListener listener) {
        switch (shareData.getType()) {
            case QQ:
                mCurrentPlatform = ShareSDK.getPlatform(QQ.NAME);
                break;
            case QZone:
                mCurrentPlatform = ShareSDK.getPlatform(QZone.NAME);
                break;
            case Wechat:
                mCurrentPlatform = ShareSDK.getPlatform(Wechat.NAME);
                break;
            case SinaWeibo:
                mCurrentPlatform = ShareSDK.getPlatform(SinaWeibo.NAME);
                break;
            case WechatMoments:
                mCurrentPlatform = ShareSDK.getPlatform(WechatMoments.NAME);
                break;
        }
        mCurrentPlatform.setPlatformActionListener(listener);
        mCurrentPlatform.share(shareData.getParams());
    }

    /***
     * 使用ShareSDK默认的GUI（九宫格）分享
     * @param context
     * @param onekeyShare
     */
    public void shareDefaultGui(Context context, OnekeyShare onekeyShare) {
        onekeyShare.show(context);
    }

}
