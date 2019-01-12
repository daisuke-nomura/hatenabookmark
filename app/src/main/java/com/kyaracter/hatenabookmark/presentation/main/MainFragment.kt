package com.kyaracter.hatenabookmark.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import com.kyaracter.hatenabookmark.R
import com.kyaracter.hatenabookmark.data.repository.CATEGORY
import com.kyaracter.hatenabookmark.databinding.MainFragmentBinding
import com.kyaracter.hatenabookmark.presentation.main.entity.RssItem
import com.kyaracter.hatenabookmark.util.viewModelOf
import dagger.android.support.DaggerFragment
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.main_fragment.*
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class MainFragment : DaggerFragment(),
    androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener,
    MainAdapter.MainAdapterInteractionListener,
    View.OnClickListener {

    private lateinit var binding: MainFragmentBinding

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    @field:[Inject Named("ioScheduler")]
    lateinit var ioScheduler: Scheduler

    @field:[Inject Named("uiScheduler")]
    lateinit var uiScheduler: Scheduler

    private val relay: Relay<List<RssItem>> = PublishRelay.create()

    private var category = CATEGORY.GENERAL_HOT_ENTRY

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelOf(viewModelFactory).get(MainViewModel::class.java)

        arguments?.let {
            category = it.getSerializable(PARAM_CATEGORY) as CATEGORY
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.viewModel = viewModel
        binding.listener = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swipe.setOnRefreshListener(this)

        adapter = MainAdapter(
            relay,
            uiScheduler,
            this@MainFragment
        )

        list.apply {
            this.adapter = this@MainFragment.adapter
            this.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                this.context,
                RecyclerView.VERTICAL,
                false
            )
            this.addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    this.context,
                    androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    override fun onStart() {
        super.onStart()

        // Request/Responseを分離
        adapter.start()

        fetch()
    }

    private fun fetch() {
        viewModel
            .fetch(relay, category)
            .delay(2, TimeUnit.SECONDS)// 分かりやすくするため、わざとdelay
            .doFinally {
                swipe.isRefreshing = false
            }
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribeBy(
                onError = {
                    Timber.e(it)
                }
            )
            .addTo(compositeDisposable)
    }

    override fun onRefresh() {
        fetch()
    }

    override fun show(rssItem: RssItem) {
        (requireActivity() as MainActivity).show(rssItem)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.error) {
            fetch()
        }
    }

    override fun onStop() {
        adapter.stop()
        compositeDisposable.clear()
        super.onStop()
    }

    companion object {
        fun newInstance(category: CATEGORY): MainFragment {
            val fragment = MainFragment()
            fragment.arguments = Bundle()
                    .apply {
                        putSerializable(PARAM_CATEGORY, category)
                    }
            return fragment
        }
    }
}

const val PARAM_CATEGORY = "category"
