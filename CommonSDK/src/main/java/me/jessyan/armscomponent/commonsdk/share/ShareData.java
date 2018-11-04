package me.jessyan.armscomponent.commonsdk.share;

import cn.sharesdk.framework.Platform;

/**
 * Created by Chenlu on 2018/11/3
 * Email:chenlu@globalroam.com
 */
public class ShareData {
    private Platform.ShareParams mParams;//分享的数据
    private ShareManager.PlatformType mType;//分享的平台

    public Platform.ShareParams getParams() {
        return mParams;
    }

    public void setParams(Platform.ShareParams params) {
        mParams = params;
    }

    public ShareManager.PlatformType getType() {
        return mType;
    }

    public void setType(ShareManager.PlatformType type) {
        mType = type;
    }
}
