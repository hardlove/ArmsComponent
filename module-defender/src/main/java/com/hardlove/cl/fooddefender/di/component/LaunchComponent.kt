package com.hardlove.cl.fooddefender.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.hardlove.cl.fooddefender.di.module.LaunchModule

import com.jess.arms.di.scope.ActivityScope
import com.hardlove.cl.fooddefender.mvp.ui.activity.LaunchActivity

@ActivityScope
@Component(modules = arrayOf(LaunchModule::class), dependencies = arrayOf(AppComponent::class))
interface LaunchComponent {
    fun inject(activity: LaunchActivity)
}
