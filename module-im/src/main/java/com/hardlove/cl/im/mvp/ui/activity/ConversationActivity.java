package com.hardlove.cl.im.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.hardlove.cl.im.R;
import com.hardlove.cl.im.R2;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import io.rong.imkit.fragment.ConversationFragment;

/**
 * Created by Chenlu on 2018/11/20
 * Email:chenlu@globalroam.com
 */
public class ConversationActivity extends BaseActivity {
    @BindView(R2.id.tv_title_left)
    TextView mTitle;
    @BindView(R2.id.iv_back)
    ImageView mBack;
    ConversationFragment mConversationFragment;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.im_conversion_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mConversationFragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);
        String title = getIntent().getData().getQueryParameter("title");
        mTitle.setText(title);
    }
}
