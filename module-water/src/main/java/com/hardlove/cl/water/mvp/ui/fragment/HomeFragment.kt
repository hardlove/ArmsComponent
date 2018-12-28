package com.hardlove.cl.water.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hardlove.cl.water.R
import com.hardlove.cl.water.di.component.DaggerHomeComponent
import com.hardlove.cl.water.di.module.HomeModule
import com.hardlove.cl.water.mvp.contract.HomeContract
import com.hardlove.cl.water.mvp.model.entity.Chapter
import com.hardlove.cl.water.mvp.presenter.HomePresenter
import com.hardlove.cl.water.mvp.ui.adapter.HomeFragmentStateAdapter
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.toast


/**
 * 如果没presenter
 * 你可以这样写
 *
 * @FragmentScope(請注意命名空間) class NullObjectPresenterByFragment
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class HomeFragment : BaseFragment<HomePresenter>(), HomeContract.View {
    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeModule(HomeModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    override fun initData(savedInstanceState: Bundle?) {
        initViewPager()
        mPresenter?.queryChapters()
    }

    lateinit var adapter: HomeFragmentStateAdapter
    private fun initViewPager() {
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        tabLayout.setupWithViewPager(viewPager)
        adapter = HomeFragmentStateAdapter(childFragmentManager)
        viewPager.adapter = adapter
    }

    lateinit var currentType: Chapter
    lateinit var allCategories: List<Chapter>

    override fun onQueryChaptersSucceed(results: List<Chapter>?) {
        //获取闲读的主分类成功时回调
        allCategories = results!!.sortedByDescending {
            it.name
        }
        if (allCategories.isNotEmpty()) {
            currentType = allCategories[0]
        }
        adapter.datas = allCategories
        adapter.notifyDataSetChanged()
        activity?.toast("获取公众号列表成功")
    }


    override fun setData(data: Any?) {

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

    }
}
