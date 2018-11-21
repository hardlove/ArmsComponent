package com.hardlove.cl.im.mvp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hardlove.cl.im.R;
import com.hardlove.cl.im.di.component.DaggerConversionListComponent;
import com.hardlove.cl.im.di.module.ConversionListModule;
import com.hardlove.cl.im.mvp.contract.ConversionListContract;
import com.hardlove.cl.im.mvp.presenter.ConversionListPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ConversationListActivity extends BaseActivity<ConversionListPresenter> implements ConversionListContract.View {


    ConversationListFragment mConversationListFragment;
    private Conversation.ConversationType[] mConversationsTypes;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerConversionListComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .conversionListModule(new ConversionListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.im_conversion_list_activity; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mConversationListFragment = (ConversationListFragment) getSupportFragmentManager().findFragmentById(R.id.conversationlist);
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                .build();
        mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP,
                Conversation.ConversationType.PUBLIC_SERVICE,
                Conversation.ConversationType.APP_PUBLIC_SERVICE,
                Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.DISCUSSION};

        mConversationListFragment.setUri(uri);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}
