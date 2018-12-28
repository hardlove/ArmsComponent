package com.hardlove.cl.water.mvp.presenter

import android.app.Application
import com.hardlove.cl.water.mvp.contract.FavoriteContract
import com.hardlove.cl.water.mvp.model.entity.CollectListResult
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import me.jessyan.armscomponent.commonsdk.utils.RxUtil
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@FragmentScope
class FavoritePresenter
@Inject
constructor(model: FavoriteContract.Model, rootView: FavoriteContract.View) :
        BasePresenter<FavoriteContract.Model, FavoriteContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    override fun onDestroy() {
        super.onDestroy();
    }

    fun queryCollectArticles(currentPage: Int) {
        mModel.queryCollectArticles(currentPage)
                .map {
                    if (it.errorCode != 0) {
                        throw NullPointerException("${it.errorMsg}")
                    }
                    it.data.datas
                }
                .compose(RxUtil.io_main())
                .doOnSubscribe {
                    mRootView.showLoading()
                }.doFinally {
                    mRootView.hideLoading()
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(
                        {
                            mRootView.onQueryCollectArticlesSucceed(it)
                        }, {
                    mRootView.showMessage("error:${it.localizedMessage}")
                })

    }

    fun unCollectRequest(data: CollectListResult.Data.Article) {
        mModel.unCollectRequest(data)
                .compose(RxUtil.io_main())
                .doOnSubscribe {
                    mRootView.showLoading()
                }.doFinally {
                    mRootView.hideLoading()
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(
                        {
                            if (it.errorCode == 0) {
                                mRootView.onUnCollectSucceed(data)
                            }
                        }, {
                    mRootView.showMessage("error:${it.localizedMessage}")
                }
                )

    }
}
