package com.hardlove.cl.fooddefender.di.component

import com.hardlove.cl.fooddefender.di.module.MainModule
import com.hardlove.cl.fooddefender.mvp.ui.activity.MainActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(MainModule::class), dependencies = arrayOf(AppComponent::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}
