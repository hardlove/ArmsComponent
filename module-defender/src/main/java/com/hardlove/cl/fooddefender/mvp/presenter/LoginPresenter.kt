package com.hardlove.cl.fooddefender.mvp.presenter

import android.app.Application
import com.hardlove.cl.fooddefender.mvp.contract.LoginContract
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.LogUtils
import me.jessyan.armscomponent.commonsdk.extensions.isValidEmail
import me.jessyan.armscomponent.commonsdk.extensions.isValidPassword
import me.jessyan.armscomponent.commonsdk.extensions.isValidUserName
import me.jessyan.armscomponent.commonsdk.utils.RxUtil
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@ActivityScope
class LoginPresenter
@Inject
constructor(model: LoginContract.Model, rootView: LoginContract.View) :
        BasePresenter<LoginContract.Model, LoginContract.View>(model, rootView) {
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

    fun login(custom: String, userName: String, password: String) {
        if (custom.isValidUserName()) {
            mRootView.onCustomValid()
        } else if (userName.isValidEmail()) {
            mRootView.onUserNameValid()
        } else if (password.isValidPassword()) {
            mRootView.onPasswordValid()
        } else {
            mRootView.showLoading()
            mModel.login(custom, userName, password)
                    .compose(RxUtil.io_main())
                    .doOnSubscribe {
                        mRootView.showLoading()
                    }
                    .doFinally {
                        mRootView.hideLoading()
                    }
                    .subscribe({ it ->
                        LogUtils.debugInfo(it.toString())
                        mRootView.onLoginSucceed()
                    },
                            { it ->
                                LogUtils.debugInfo(it.toString())
                                mRootView.onLoginFaild(it?.localizedMessage)
                            })
        }


    }
}
