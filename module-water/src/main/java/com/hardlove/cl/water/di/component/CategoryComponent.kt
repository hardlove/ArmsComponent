package com.hardlove.cl.water.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.hardlove.cl.water.di.module.CategoryModule

import com.jess.arms.di.scope.FragmentScope
import com.hardlove.cl.water.mvp.ui.fragment.CategoryFragment

@FragmentScope
@Component(modules = arrayOf(CategoryModule::class), dependencies = arrayOf(AppComponent::class))
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}
