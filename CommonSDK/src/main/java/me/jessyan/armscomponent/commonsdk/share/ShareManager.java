package me.jessyan.armscomponent.commonsdk.share;

import android.content.Context;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Chenlu on 2018/11/3
 * Email:chenlu@globalroam.com
 */
public class ShareManager {

    public void share(Context context, ShareData data) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(data.getTitle());
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl(data.getTitleUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(data.getText());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(data.getImagePath());//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl(data.getUrl());
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment(data.getComment());
        // 启动分享GUI
        oks.show(context);

    }

}
