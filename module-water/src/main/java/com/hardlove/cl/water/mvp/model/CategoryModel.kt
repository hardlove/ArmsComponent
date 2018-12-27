package com.hardlove.cl.water.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.hardlove.cl.water.mvp.contract.CategoryContract
import com.hardlove.cl.water.mvp.model.api.service.WaterService
import com.hardlove.cl.water.mvp.model.entity.ArticleResult
import com.hardlove.cl.water.mvp.model.entity.BaseResult
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject


@FragmentScope
class CategoryModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), CategoryContract.Model {
    override fun collectArticle(id: Int): Observable<BaseResult<Any>> {
        return mRepositoryManager.obtainRetrofitService(WaterService::class.java).collectArticle(id)
    }

    override fun unCollectArticle(id: Int): Observable<BaseResult<Any>> {
        return mRepositoryManager.obtainRetrofitService(WaterService::class.java).unCollectArticle(id)
    }

    override fun getArtiles(id: Int, page: Int): Observable<ArticleResult> {
        return mRepositoryManager.obtainRetrofitService(WaterService::class.java).getArtiles(id, page)

    }

    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
}
