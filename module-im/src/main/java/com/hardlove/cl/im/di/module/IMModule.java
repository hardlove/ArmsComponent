package com.hardlove.cl.im.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hardlove.cl.im.mvp.contract.IMContract;
import com.hardlove.cl.im.mvp.model.IMModel;


@Module
public class IMModule {
    private IMContract.View view;

    /**
     * 构建IMModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public IMModule(IMContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    IMContract.View provideIMView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    IMContract.Model provideIMModel(IMModel model) {
        return model;
    }
}