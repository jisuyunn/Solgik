package com.example.solgikb.ui.base

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.solgikb.R


abstract class BaseRecyclerView {
    abstract class Adapter<ITEM : Any, B : ViewDataBinding>(
            @LayoutRes private val layoutResId: Int,
            private val bindingVariableId: Int? = null,
            val itemClick: (ITEM) -> Unit
    ) : RecyclerView.Adapter<ViewHolder<B>>() {

        private val VIEW_TYPE_ITEM = 0
        private val VIEW_TYPE_LOADING = 1
        private val loadMoreNum = 20

        private var previousTotalItemCount = 0
        private var visibleThreshold = 1
        private var lastVisibleItem: Int = 0
        private var totalItemCount: Int = 0
        private var isLoading = false      // loading중이면 true

        private var recyclerView : RecyclerView?= null
        private val items = mutableListOf<ITEM>()
        private val shows = mutableListOf<ITEM>()

        fun replaceAll(items: List<ITEM>?) {
            items?.let {
                this.items.run {
                    clear()
                    addAll(it)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                when(viewType) {
                    VIEW_TYPE_ITEM -> object : ViewHolder<B>(
                            layoutResId = layoutResId,
                            parent = parent,
                            bindingVariableId = bindingVariableId
                    ) {}
                    else -> object : ViewHolder<B>(
                            layoutResId = R.layout.item_progress_loading,
                            parent = parent
                    ) {}
                }

        override fun getItemCount() = shows.size

        override fun getItemViewType(position: Int) = if(shows.size == 0) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM

        override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
            Log.d("itemViewType : " , "" + holder.itemViewType)
            when(holder.itemViewType) {
                VIEW_TYPE_ITEM -> {
                    holder.onBindViewHolder(items[position])
                    holder.itemView.setOnClickListener{ itemClick(items.get(position)) }
                }
                else -> {}
            }
        }

        fun initScroll(recyclerView: RecyclerView) {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager : GridLayoutManager = recyclerView.layoutManager as GridLayoutManager
                    lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()
                    totalItemCount = layoutManager.itemCount
                    if (!isLoading && (lastVisibleItem + visibleThreshold ) >= totalItemCount) {
                        onLoadMore()
                        isLoading = true;
                    }

                }
            })
            this.recyclerView = recyclerView
        }

        fun onLoadMore() {
            if(items.size != shows.size) {
                shows.add(null!!)
                notifyItemInserted(shows.size - 1)
            }
        }
    }

    abstract class ViewHolder<B : ViewDataBinding>(
            @LayoutRes layoutResId: Int,
            parent: ViewGroup,
            private val bindingVariableId: Int? = null
    ) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
    ) {

        protected val binding: B = DataBindingUtil.bind(itemView)!!

        fun onBindViewHolder(item: Any?) {
            try {
                bindingVariableId?.let {
                    binding.setVariable(it, item)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}