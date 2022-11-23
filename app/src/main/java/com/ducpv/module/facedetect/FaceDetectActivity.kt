package com.ducpv.module.facedetect

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.ducpv.R
import com.ducpv.base.BaseActivity
import com.ducpv.databinding.ActivityFaceDetectBinding
import com.ducpv.extension.gone
import com.ducpv.extension.show
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by pvduc9773 on 20/10/2022.
 */
@AndroidEntryPoint
class FaceDetectActivity : BaseActivity<FaceDetectViewModel, ActivityFaceDetectBinding>() {
    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
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

        navController.addOnDestinationChangedListener(destinationChangedListener)
    }

    private val destinationChangedListener = NavController.OnDestinationChangedListener { _, destination, _ ->
        if (destination.id == R.id.takeFacePhotoFragment) {
            binding.toolbar.gone()
        } else {
            binding.toolbar.show()
        }
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
