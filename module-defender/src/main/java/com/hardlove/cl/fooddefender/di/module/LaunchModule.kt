package com.hardlove.cl.fooddefender.di.module

import com.hardlove.cl.fooddefender.mvp.contract.LaunchContract
import com.hardlove.cl.fooddefender.mvp.model.LaunchModel
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides


@Module
//构建LaunchModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class LaunchModule(private val view: LaunchContract.View) {
    @ActivityScope
    @Provides
    fun provideLaunchView(): LaunchContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideLaunchModel(model: LaunchModel): LaunchContract.Model {
        return model
    }
}
