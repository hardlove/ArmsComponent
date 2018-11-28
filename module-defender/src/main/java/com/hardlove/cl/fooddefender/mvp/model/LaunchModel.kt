package com.hardlove.cl.fooddefender.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.hardlove.cl.fooddefender.mvp.contract.LaunchContract
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import com.jess.arms.utils.DataHelper
import javax.inject.Inject


@ActivityScope
class LaunchModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), LaunchContract.Model {
    override fun hasLogined()= DataHelper.getIntergerSF(mApplication,"has_login")==1

    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun onDestroy() {
        super.onDestroy()
    }
}
