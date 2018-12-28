package com.hardlove.cl.water.mvp.presenter

import android.app.Application
import com.hardlove.cl.water.mvp.contract.HomeContract
import com.hardlove.cl.water.mvp.model.entity.BaseResult
import com.hardlove.cl.water.mvp.model.entity.Chapter
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import me.jessyan.armscomponent.commonsdk.utils.MLogger
import me.jessyan.armscomponent.commonsdk.utils.RxUtil
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@FragmentScope
class HomePresenter
@Inject
constructor(model: HomeContract.Model, rootView: HomeContract.View) :
        BasePresenter<HomeContract.Model, HomeContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * 获取公众号列表
     */
    fun queryChapters() {
        val obser: Observable<BaseResult<List<Chapter>>> = mModel.queryChapters()
                .compose(RxUtil.io_main())
        obser.doOnSubscribe {
            mRootView.showLoading()
        }.doFinally {
            mRootView.hideLoading()
        }.compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.errorCode == 0) {
                        mRootView.onQueryChaptersSucceed(it.data)
                    } else {
                        mRootView.showMessage("获取公众号列表失败")
                    }
                }, {
                    mRootView.showMessage("获取失败")
                    MLogger.tag(TAG).e("获取公众号列表失败：${it.localizedMessage}")
                })
    }
}