package com.hardlove.cl.fooddefender.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import com.hardlove.cl.fooddefender.R
import com.hardlove.cl.fooddefender.di.component.DaggerLaunchComponent
import com.hardlove.cl.fooddefender.di.module.LaunchModule
import com.hardlove.cl.fooddefender.mvp.contract.LaunchContract
import com.hardlove.cl.fooddefender.mvp.presenter.LaunchPresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import io.reactivex.Observable
import me.jessyan.armscomponent.commonsdk.utils.RxUtil
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit

class LaunchActivity : BaseActivity<LaunchPresenter>(), LaunchContract.View {

    override fun onHasLogined() {
        startActivity<MainActivity>()
        finish()
    }

    override fun onNotLogined() {
        startActivity<LoginActivity>()
        finish()
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLaunchComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .launchModule(LaunchModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_launch //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        Observable.just("").delay(2, TimeUnit.SECONDS)
                .compose(RxUtil.io_main())
                .subscribe {
                    mPresenter?.checkHasLogin()
                }

    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }
}