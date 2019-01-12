package com.kyaracter.hatenabookmark.presentation.detail

import android.os.Bundle
import com.kyaracter.hatenabookmark.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(intent.getStringExtra(PARAM_URL)))
                .commitNow()
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager
                .findFragmentById(R.id.container)

        if (fragment != null) {
            (fragment as DetailFragment).goBack()
        } else {
            super.onBackPressed()
        }
    }
}

const val PARAM_URL = "url"