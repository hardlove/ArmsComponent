package com.hardlove.cl.login.di.component

import com.hardlove.cl.login.di.module.LoginModule
import com.hardlove.cl.login.mvp.ui.activity.LoginActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component


@ActivityScope
@Component(modules = arrayOf(LoginModule::class), dependencies = arrayOf(AppComponent::class))
interface LoginComponent {
    fun inject(activity: LoginActivity)
}
