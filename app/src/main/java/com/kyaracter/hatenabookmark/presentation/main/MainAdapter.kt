package com.kyaracter.hatenabookmark.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kyaracter.hatenabookmark.R
import com.kyaracter.hatenabookmark.databinding.RssItemTopBinding
import com.kyaracter.hatenabookmark.databinding.RssItemLineBinding
import com.kyaracter.hatenabookmark.presentation.main.entity.RssItem
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RssItem>() {
    override fun areItemsTheSame(p0: RssItem, p1: RssItem): Boolean {
        return p0 == p1
    }

    override fun areContentsTheSame(p0: RssItem, p1: RssItem): Boolean {
        return p0.name == p1.name && p0.description == p1.description
    }
}

class MainAdapter @Inject constructor(
    private val list: Observable<List<RssItem>>,
    private val scheduler: Scheduler,
    private val listener: MainAdapterInteractionListener
) : ListAdapter<RssItem, RecyclerView.ViewHolder>(
    DIFF_CALLBACK
) {

    private val compositeDisposable = CompositeDisposable()

    fun start() {
        list
            .observeOn(scheduler)
            .subscribeBy { setItems(it) }
            .addTo(compositeDisposable)
    }

    fun stop() {
        compositeDisposable.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FIRST -> {
                RssItemTopViewHolder.create(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            }
            NOT_FIRST -> {
                RssItemLineViewHolder.create(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RssItemTopViewHolder -> {
                holder.binding.item = this.getItem(position)
                holder.binding.listener = listener
            }
            is RssItemLineViewHolder -> {
                holder.binding.item = this.getItem(position)
                holder.binding.listener = listener
            }
        }
    }

    private fun setItems(list: List<RssItem>) {
        this.submitList(list)
    }

    class RssItemTopViewHolder(val binding: RssItemTopBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean): RssItemTopViewHolder {
                val binding = DataBindingUtil.inflate<RssItemTopBinding>(
                    inflater,
                    R.layout.rss_item_top, parent, attachToParent
                )
                return RssItemTopViewHolder(binding)
            }
        }
    }

    class RssItemLineViewHolder(val binding: RssItemLineBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean): RssItemLineViewHolder {
                val binding = DataBindingUtil.inflate<RssItemLineBinding>(
                    inflater,
                    R.layout.rss_item_line, parent, attachToParent
                )
                return RssItemLineViewHolder(binding)
            }
        }
    }

    interface MainAdapterInteractionListener {
        fun show(rssItem: RssItem)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> FIRST
            else -> NOT_FIRST
        }
    }
}


private const val FIRST = 0

private const val NOT_FIRST = 1