package com.hardlove.cl.im.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.hardlove.cl.im.di.module.IMActivityModule

import com.jess.arms.di.scope.ActivityScope
import com.hardlove.cl.im.mvp.ui.activity.IMActivityActivity

@ActivityScope
@Component(modules = arrayOf(IMActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface IMActivityComponent {
    fun inject(activity: IMActivityActivity)
}
