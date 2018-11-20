package com.hardlove.cl.im.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hardlove.cl.im.mvp.contract.ConversionListContract;
import com.hardlove.cl.im.mvp.model.ConversionListModel;


@Module
public class ConversionListModule {
    private ConversionListContract.View view;

    /**
     * 构建ConversionListModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public ConversionListModule(ConversionListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ConversionListContract.View provideConversionListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ConversionListContract.Model provideConversionListModel(ConversionListModel model) {
        return model;
    }
}