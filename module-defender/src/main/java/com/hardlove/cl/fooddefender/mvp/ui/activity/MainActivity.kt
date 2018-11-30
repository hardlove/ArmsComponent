package com.hardlove.cl.fooddefender.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.hardlove.cl.fooddefender.R
import com.hardlove.cl.fooddefender.di.component.DaggerMainComponent
import com.hardlove.cl.fooddefender.di.module.MainModule
import com.hardlove.cl.fooddefender.mvp.contract.MainContract
import com.hardlove.cl.fooddefender.mvp.presenter.MainPresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils


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
class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(MainModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        initBottomNavigationBar()
    }

    private fun initBottomNavigationBar() {
        val bottomNavigationBar = findViewById<View>(R.id.bottom_navigation_bar) as BottomNavigationBar
        bottomNavigationBar
                .setActiveColor(R.color.color_3586DA)
                .setInActiveColor(R.color.color_C2C2C2)
                .setBarBackgroundColor(R.color.color_FFFFFF)
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
        bottomNavigationBar
                .addItem(BottomNavigationItem(R.mipmap.tab_home, "首页"))
                .addItem(BottomNavigationItem(R.mipmap.tab_method, "方法"))
                .addItem(BottomNavigationItem(R.mipmap.tab_histrory, "历史"))
                .addItem(BottomNavigationItem(R.mipmap.tab_statistical, "统计分析"))
                .initialise()

        bottomNavigationBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
//                curentModules(position)
//                checkToDisconnectFlashAir(position)
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabReselected(position: Int) {
            }
        })
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
