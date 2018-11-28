package com.hardlove.cl.fooddefender.di.component

import com.hardlove.cl.fooddefender.di.module.LoginModule
import com.hardlove.cl.fooddefender.mvp.ui.activity.LoginActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(LoginModule::class), dependencies = arrayOf(AppComponent::class))
interface LoginComponent {
    fun inject(activity: LoginActivity)
}
