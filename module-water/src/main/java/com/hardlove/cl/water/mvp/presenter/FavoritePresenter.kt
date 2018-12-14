package com.hardlove.cl.water.mvp.presenter

import android.app.Application
import com.hardlove.cl.water.mvp.contract.FavoriteContract
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
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
}
