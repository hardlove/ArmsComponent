package me.jessyan.armscomponent.commonres.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;

import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import me.jessyan.armscomponent.commonres.R;
import me.jessyan.armscomponent.commonsdk.share.ShareData;
import me.jessyan.armscomponent.commonsdk.share.ShareManager;


/**
 * Created by Chenlu on 2018/11/4
 * Email:chenlu@globalroam.com
 */
public class ShareDialog extends BaseDialog implements View.OnClickListener {

    private static final String TAG = "ShareDialog";
    private View mWeChat;
    private View mWechatmoments;
    private View mSinaweibo;
    private View mQq;
    private View mQzone;
    private Platform.ShareParams mShareParams;

    /**
     * @param context
     * @param shareParams 分享的数据信息
     */
    public ShareDialog(@NonNull Context context, Platform.ShareParams shareParams) {
        super(context);
        this.mShareParams = shareParams;
        setContentView(R.layout.public_dialog_share);
        setGravity(Gravity.BOTTOM);
        initView();
    }

    private void initView() {
        mWeChat = findViewById(R.id.share_wechat);
        mWechatmoments = findViewById(R.id.share_wechatmoments);
        mSinaweibo = findViewById(R.id.share_sinaweibo);
        mQq = findViewById(R.id.share_qq);
        mQzone = findViewById(R.id.share_qzone);

        mWeChat.setOnClickListener(this);
        mWechatmoments.setOnClickListener(this);
        mSinaweibo.setOnClickListener(this);
        mQq.setOnClickListener(this);
        mQzone.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        ShareManager.PlatformType type = null;
        int id = v.getId();
        if (id == R.id.share_wechat) {
            type = ShareManager.PlatformType.Wechat;
        } else if (id == R.id.share_wechatmoments) {
            type = ShareManager.PlatformType.WechatMoments;
        } else if (id == R.id.share_sinaweibo) {
            type = ShareManager.PlatformType.SinaWeibo;
        } else if (id == R.id.share_qq) {
            type = ShareManager.PlatformType.QQ;
        } else if (id == R.id.share_qzone) {
            type = ShareManager.PlatformType.QZone;
        }
        ShareData sharedata = new ShareData();
        if (type == null) {
            throw new NullPointerException("请指定要分享的平台");
        }
        if (mShareParams == null) {
            throw new NullPointerException("mShareParams is Null,分享的数据不能为空");
        }
        sharedata.setType(type);
        sharedata.setParams(mShareParams);
        ShareManager.getInstance().share(sharedata, mListener);

        dismiss();
    }

    private PlatformActionListener mListener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            ArmsUtils.makeText(getContext(), "分享成功");
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            ArmsUtils.makeText(getContext(), "分享失败");
            LogUtils.debugInfo(TAG, "分享失败,erroe:" + throwable.getLocalizedMessage());
        }

        @Override
        public void onCancel(Platform platform, int i) {
            ArmsUtils.makeText(getContext(), "分享取消");
        }
    };
}
