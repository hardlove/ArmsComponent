package com.hardlove.cl.water.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.FragmentScope
import javax.inject.Inject

import com.hardlove.cl.water.mvp.contract.MineContract


@FragmentScope
class MineModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), MineContract.Model {
    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}
