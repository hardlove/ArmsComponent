package com.hardlove.cl.im.mvp.ui.activity

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.hardlove.cl.im.di.component.DaggerIMActivityComponent
import com.hardlove.cl.im.di.module.IMActivityModule
import com.hardlove.cl.im.mvp.contract.IMActivityContract
import com.hardlove.cl.im.mvp.presenter.IMActivityPresenter

import com.hardlove.cl.im.R


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
class IMActivityActivity : BaseActivity<IMActivityPresenter>(), IMActivityContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerIMActivityComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .iMActivityModule(IMActivityModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_im //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {

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
