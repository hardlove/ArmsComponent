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
import me.jessyan.autosize.internal.CustomAdapt
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit


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
class LaunchActivity : BaseActivity<LaunchPresenter>(), LaunchContract.View, CustomAdapt {
    /**
     * 是否按照宽度进行等比例适配 (为了保证在高宽比不同的屏幕上也能正常适配, 所以只能在宽度和高度之中选一个作为基准进行适配)
     *
     * @return `true` 为按照宽度适配, `false` 为按照高度适配
     */
    override fun isBaseOnWidth(): Boolean {
        return true
    }

    /**
     * 返回设计图上的设计尺寸, 单位 dp
     * [.getSizeInDp] 须配合 [.isBaseOnWidth] 使用, 规则如下:
     * 如果 [.isBaseOnWidth] 返回 `true`, [.getSizeInDp] 则应该返回设计图的总宽度
     * 如果 [.isBaseOnWidth] 返回 `false`, [.getSizeInDp] 则应该返回设计图的总高度
     * 如果您不需要自定义设计图上的设计尺寸, 想继续使用在 AndroidManifest 中填写的设计图尺寸, [.getSizeInDp] 则返回 `0`
     *
     * @return 设计图上的设计尺寸, 单位 dp
     */
    override fun getSizeInDp(): Float = 0f

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
