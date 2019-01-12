package com.kyaracter.hatenabookmark.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kyaracter.hatenabookmark.R
import com.kyaracter.hatenabookmark.databinding.DetailFragmentBinding
import com.kyaracter.hatenabookmark.util.viewModelOf
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.detail_fragment.*
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    private lateinit var binding: DetailFragmentBinding

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelOf(viewModelFactory).get(DetailViewModel::class.java)

        arguments?.let {
            viewModel.url = it.getString(PARAM_URL, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.webView.webViewClient = HatenaWebViewClient()
        binding.webView.settings.apply {
            javaScriptEnabled = true
        }

        toolbar.setNavigationOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                requireActivity().finish()
            }
        }

        webView.loadUrl(viewModel.url)
    }

    fun goBack() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            requireActivity().finish()
        }
    }

    companion object {
        fun newInstance(url: String): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = Bundle()
                .apply {
                    putString(PARAM_URL, url)
            }
            return fragment
        }
    }

    inner class HatenaWebViewClient: WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return false
        }
    }
}
