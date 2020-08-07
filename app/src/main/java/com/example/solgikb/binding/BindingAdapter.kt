package com.example.solgikb.binding

import android.widget.ImageView
import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.solgikb.R
import com.example.solgikb.ui.base.BaseRecyclerView
import com.google.android.material.tabs.TabLayout


@Suppress("UNCHECKED_CAST")
@BindingAdapter("bind:recycler_items")
fun RecyclerView.replaceAll(list: List<Any>?) {
    (this.adapter as? BaseRecyclerView.Adapter<Any, *>)?.run {
        replaceAll(list)
        notifyDataSetChanged()
    }
}

@BindingAdapter("bind:recycler_adapter")
fun setRecyclerAdapter(recyclerView: RecyclerView, adapter: BaseRecyclerView.Adapter<*, *>) {
    recyclerView.adapter = adapter
}

@BindingAdapter("bind:view_pager_adapter")
fun setViewPagerAdapter(viewPager: ViewPager, adapter: FragmentStatePagerAdapter?) {
    if(adapter != null) viewPager.adapter = adapter
}

@BindingAdapter("bind:tab_select_listener")
fun TabLayout.OnTabSelectedListener(listener: TabLayout.OnTabSelectedListener?) {
    this.setOnTabSelectedListener(listener)
}

@BindingAdapter("bind:radio_check_listener")
fun RadioGroup.OnCheckedChangeListener(listener: RadioGroup.OnCheckedChangeListener?) {
    this.setOnCheckedChangeListener(listener)
}

@BindingAdapter("bind:image_src")
fun imageLoad(imageView: ImageView, url: String) {
    Glide.with(imageView.getContext())
            .load(url)
            .placeholder(R.color.gray)
            .fitCenter()
            .into(imageView);
}
