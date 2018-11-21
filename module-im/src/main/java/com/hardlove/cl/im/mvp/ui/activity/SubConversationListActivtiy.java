package com.hardlove.cl.im.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hardlove.cl.im.R;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

/**
 * Created by Chenlu on 2018/11/20
 * Email:chenlu@globalroam.com
 */
public class SubConversationListActivtiy extends BaseActivity {
    /**
     * 提供 AppComponent (提供所有的单例对象) 给实现类, 进行 Component 依赖
     *
     * @param appComponent
     */
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.im_subconversationlist_activity;
    }

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
