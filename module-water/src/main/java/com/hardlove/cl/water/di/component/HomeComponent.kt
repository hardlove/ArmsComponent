package com.hardlove.cl.water.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.hardlove.cl.water.di.module.HomeModule

import com.jess.arms.di.scope.FragmentScope
import com.hardlove.cl.water.mvp.ui.fragment.HomeFragment

@FragmentScope
@Component(modules = arrayOf(HomeModule::class), dependencies = arrayOf(AppComponent::class))
interface HomeComponent {
    fun inject(fragment: HomeFragment)
}
