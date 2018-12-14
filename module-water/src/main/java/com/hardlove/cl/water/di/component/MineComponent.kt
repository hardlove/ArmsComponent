package com.hardlove.cl.water.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.hardlove.cl.water.di.module.MineModule

import com.jess.arms.di.scope.FragmentScope
import com.hardlove.cl.water.mvp.ui.fragment.MineFragment

@FragmentScope
@Component(modules = arrayOf(MineModule::class), dependencies = arrayOf(AppComponent::class))
interface MineComponent {
    fun inject(fragment: MineFragment)
}
