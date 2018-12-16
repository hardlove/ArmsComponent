package com.hardlove.cl.water.mvp.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.hardlove.cl.water.mvp.model.entity.Chapter
import com.hardlove.cl.water.mvp.ui.fragment.CategoryFragment

/**
 * Created by Chenlu on 2018/12/15
 * Email:chenlu@globalroam.com
 */
class HomeFragmentStateAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    var datas: List<Chapter> = ArrayList()
    override fun getItem(position: Int): Fragment = CategoryFragment.newInstance(datas[position])
    override fun getCount(): Int = datas.size
    override fun getPageTitle(position: Int): CharSequence? = datas[position].name

}