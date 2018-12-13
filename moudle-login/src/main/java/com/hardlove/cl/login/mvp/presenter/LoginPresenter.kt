package com.hardlove.cl.login.mvp.presenter

import android.app.Application
import com.hardlove.cl.login.mvp.contract.LoginContract
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
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
        super.onDestroy();
    }

    fun loginByPassword(userName: String, password: String) {
        when {
//            userName.isValidPhone() -> mRootView.onErrorTips(1,"用户名不合法")
//            password.isValidPassword() -> mRootView.onErrorTips(2, "密码不合法")
            else -> mModel.loginByPassword(userName, password)
                    .compose(RxUtil.io_main())
                    .doOnSubscribe {
                        mRootView.showLoading()
                    }.doFinally {
                        mRootView.hideLoading()
                    }.subscribeOn(AndroidSchedulers.mainThread())
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                    .subscribe({
                        if (it.errorCode == 0) {
                            mRootView.onSucceed()
                        } else {
                            mRootView.onFailed(it.errorCode,it.errorMsg)
                        }
                    }, {
                        mRootView.onFailed(-1,it.localizedMessage)
                    })
        }
    }
}
