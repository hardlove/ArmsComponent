package com.hardlove.cl.water.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.hardlove.cl.water.mvp.contract.HomeContract
import com.hardlove.cl.water.mvp.model.api.service.WaterService
import com.hardlove.cl.water.mvp.model.entity.BaseResult
import com.hardlove.cl.water.mvp.model.entity.Chapter
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject


@FragmentScope
class HomeModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), HomeContract.Model {
    override fun queryChapters(): Observable<BaseResult<List<Chapter>>>{
      return  mRepositoryManager.obtainRetrofitService(WaterService::class.java)
                .queryChapters()
    }

    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}
