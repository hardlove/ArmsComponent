package com.hardlove.cl.water.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.hardlove.cl.water.di.module.MainModule

import com.jess.arms.di.scope.ActivityScope
import com.hardlove.cl.water.mvp.ui.activity.MainActivity

@ActivityScope
@Component(modules = arrayOf(MainModule::class), dependencies = arrayOf(AppComponent::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}
