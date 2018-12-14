package com.hardlove.cl.water.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.hardlove.cl.water.mvp.contract.FavoriteContract
import com.hardlove.cl.water.mvp.model.FavoriteModel


@Module
//构建FavoriteModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class FavoriteModule(private val view: FavoriteContract.View) {
    @FragmentScope
    @Provides
    fun provideFavoriteView(): FavoriteContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideFavoriteModel(model: FavoriteModel): FavoriteContract.Model {
        return model
    }
}
