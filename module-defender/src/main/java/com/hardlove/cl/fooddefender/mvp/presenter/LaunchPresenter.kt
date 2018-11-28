package com.hardlove.cl.fooddefender.mvp.presenter

import android.app.Application
import com.hardlove.cl.fooddefender.mvp.contract.LaunchContract
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@ActivityScope
class LaunchPresenter
@Inject
constructor(model: LaunchContract.Model, rootView: LaunchContract.View) :
        BasePresenter<LaunchContract.Model, LaunchContract.View>(model, rootView) {
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

    fun checkHasLogin() {
        if (mModel.hasLogined()) {
            mRootView.onHasLogined()
        } else {
            mRootView.onNotLogined()
        }
    }
}
