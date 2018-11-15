package com.hardlove.cl.im.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.hardlove.cl.im.mvp.contract.IMActivityContract
import com.hardlove.cl.im.mvp.model.IMActivityModel


@Module
//构建IMActivityModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class IMActivityModule(private val view: IMActivityContract.View) {
    @ActivityScope
    @Provides
    fun provideIMActivityView(): IMActivityContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideIMActivityModel(model: IMActivityModel): IMActivityContract.Model {
        return model
    }
}
