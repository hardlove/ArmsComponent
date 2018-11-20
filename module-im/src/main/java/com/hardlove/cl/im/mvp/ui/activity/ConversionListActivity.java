package com.hardlove.cl.im.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.hardlove.cl.im.di.component.DaggerConversionListComponent;
import com.hardlove.cl.im.di.module.ConversionListModule;
import com.hardlove.cl.im.mvp.contract.ConversionListContract;
import com.hardlove.cl.im.mvp.presenter.ConversionListPresenter;

import com.hardlove.cl.im.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;


public class ConversionListActivity extends BaseActivity<ConversionListPresenter> implements ConversionListContract.View {

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
        return R.layout.im_activity_conversion_list; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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
