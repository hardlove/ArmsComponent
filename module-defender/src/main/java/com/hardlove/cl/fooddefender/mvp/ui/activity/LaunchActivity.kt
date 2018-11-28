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
import org.jetbrains.anko.startActivity


/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
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
        mPresenter?.checkHasLogin()

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
