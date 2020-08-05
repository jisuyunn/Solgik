package com.example.solgikb.binding

import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.solgikb.ui.base.BaseRecyclerView
import com.google.android.material.tabs.TabLayout


@Suppress("UNCHECKED_CAST")
@BindingAdapter("bind:recycler_adapter")
fun RecyclerView.replaceAll(list: List<Any>?) {
    (this.adapter as? BaseRecyclerView.Adapter<Any, *>)?.run {
        replaceAll(list)
        notifyDataSetChanged()
    }
}

@BindingAdapter("bind:view_pager_adapter")
fun setViewPagerAdapter(viewPager: ViewPager, adapter: FragmentStatePagerAdapter?) {
    if(adapter != null) viewPager.adapter = adapter
}

@BindingAdapter("bind:tab_select_adapter")
fun TabLayout.OnTabSelectedListener(listener: TabLayout.OnTabSelectedListener?) {
    this.addOnTabSelectedListener(listener)
}