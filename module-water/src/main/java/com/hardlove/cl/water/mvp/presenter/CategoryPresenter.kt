package com.hardlove.cl.water.mvp.presenter

import android.app.Application
import com.hardlove.cl.water.mvp.contract.CategoryContract
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import me.jessyan.armscomponent.commonsdk.utils.MLogger
import me.jessyan.armscomponent.commonsdk.utils.RxUtil
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@FragmentScope
class CategoryPresenter
@Inject
constructor(model: CategoryContract.Model, rootView: CategoryContract.View) :
        BasePresenter<CategoryContract.Model, CategoryContract.View>(model, rootView) {
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

    fun getArtiles(id: Int, page: Int) {
        mModel.getArtiles(id, page)
                .compose(RxUtil.io_main())
                .doOnSubscribe {
                    mRootView.showLoading()
                }.doFinally {
                    mRootView.hideLoading()
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe({
                    if (it.errorCode == 0) {
                        mRootView.onLoadArticlesSucceed(it.data.articles)
                    } else {
                        mRootView.showMessage("error:${it.errorMsg}")
                    }

                }, {
                    mRootView.showMessage("请求失败：" + it.message)
                    MLogger.tag(TAG).e("请求失败：${it.localizedMessage}")
                })
    }
}
