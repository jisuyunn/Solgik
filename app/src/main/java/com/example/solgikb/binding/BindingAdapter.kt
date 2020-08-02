package com.example.solgikb.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.solgikb.ui.base.BaseRecyclerView


@Suppress("UNCHECKED_CAST")
@BindingAdapter("bind:recycler_adapter")
fun RecyclerView.replaceAll(list: List<Any>?) {
    (this.adapter as? BaseRecyclerView.Adapter<Any, *>)?.run {
        replaceAll(list)
        notifyDataSetChanged()
    }
}