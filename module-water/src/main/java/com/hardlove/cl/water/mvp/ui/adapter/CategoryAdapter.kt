package com.hardlove.cl.water.mvp.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.hardlove.cl.water.R
import com.hardlove.cl.water.mvp.model.entity.ArticleResult
import com.jess.arms.base.BaseHolder
import com.jess.arms.base.DefaultAdapter
import com.jess.arms.base.DefaultAdapter.OnRecyclerViewItemClickListener
import com.jess.arms.utils.ArmsUtils
import me.jessyan.armscomponent.commonsdk.utils.TimeUtil
import org.jetbrains.anko.toast

/**
 * Created by Chenlu on 2018/12/15
 * Email:chenlu@globalroam.com
 */
class CategoryAdapter(infos: List<ArticleResult.Data.Article>?) : DefaultAdapter<ArticleResult.Data.Article>(infos), OnRecyclerViewItemClickListener<ArticleResult.Data.Article> {
    init {
        setOnItemClickListener(this)
    }

    override fun onItemClick(view: View, viewType: Int, data: ArticleResult.Data.Article?, position: Int) {
        view.context.toast("点击：$position")
    }

    override fun getLayoutId(viewType: Int): Int = R.layout.item_article

    override fun getHolder(v: View, viewType: Int): BaseHolder<ArticleResult.Data.Article> =
            CategoryAdapter.Companion.ViewHolder(v)


    companion object {
        class ViewHolder(view: View) : BaseHolder<ArticleResult.Data.Article>(view) {
            override fun setData(data: ArticleResult.Data.Article, position: Int) {
                itemView.findViewById<TextView>(R.id.tvTitle).text = data.title
                itemView.findViewById<TextView>(R.id.tvAuthor).text = data.author
                itemView.findViewById<TextView>(R.id.tvPublishTime).text = TimeUtil.getStringByFormat(data.publishTime, TimeUtil.dateFormat)
                itemView.findViewById<ImageView>(R.id.ivCollect).setImageResource(R.drawable.leak_canary_icon)
                var iv = itemView.findViewById<ImageView>(R.id.iv)
                //可以在任何可以拿到 Context 的地方,拿到 AppComponent,从而得到用 Dagger 管理的单例对象
                val mAppComponent = ArmsUtils.obtainAppComponentFromContext(itemView.getContext());
                val mImageLoader = mAppComponent.imageLoader()

            }


        }
    }
}