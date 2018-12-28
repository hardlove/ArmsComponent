package com.hardlove.cl.water.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.hardlove.cl.water.mvp.contract.FavoriteContract
import com.hardlove.cl.water.mvp.model.api.service.WaterService
import com.hardlove.cl.water.mvp.model.entity.BaseResult
import com.hardlove.cl.water.mvp.model.entity.CollectListResult
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject


@FragmentScope
class FavoriteModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), FavoriteContract.Model {
    override fun queryCollectArticles(currentPage: Int): Observable<CollectListResult> {
        return mRepositoryManager.obtainRetrofitService(WaterService::class.java)
                .queryCollectArticles(currentPage)

    }

    override fun unCollectRequest(data: CollectListResult.Data.Article): Observable<BaseResult<Any>> {
        return mRepositoryManager.obtainRetrofitService(WaterService::class.java)
                .unCollectArticle(data.originId)
    }

    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}
