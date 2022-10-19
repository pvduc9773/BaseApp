package com.ducpv.module.facedetect

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.ducpv.R
import com.ducpv.base.BaseActivity
import com.ducpv.databinding.ActivityFaceDetectBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by pvduc9773 on 20/10/2022.
 */
@AndroidEntryPoint
class FaceDetectActivity : BaseActivity<FaceDetectViewModel, ActivityFaceDetectBinding>() {
    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFaceDetect) as NavHostFragment
        navHostFragment.navController
    }
    private val appBarConfiguration by lazy {
        AppBarConfiguration.Builder().build()
    }

    override val viewModel by viewModels<FaceDetectViewModel>()

    override fun getViewBinding() = ActivityFaceDetectBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!navController.navigateUp()) {
            finish()
        }
        return true
    }

    override fun finish() {
        super.finish()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }
}
