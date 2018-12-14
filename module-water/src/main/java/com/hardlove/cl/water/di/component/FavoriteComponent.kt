package com.hardlove.cl.water.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.hardlove.cl.water.di.module.FavoriteModule

import com.jess.arms.di.scope.FragmentScope
import com.hardlove.cl.water.mvp.ui.fragment.FavoriteFragment

@FragmentScope
@Component(modules = arrayOf(FavoriteModule::class), dependencies = arrayOf(AppComponent::class))
interface FavoriteComponent {
    fun inject(fragment: FavoriteFragment)
}
