package com.mohamedtaha.imagine.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    //    @JvmField
//    @BindString(R.string.exit_app)
    @Inject
    lateinit var waitTimer: Timer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this, NavigationDrawaberActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val animation = AnimationUtils.loadAnimation(this, R.anim.aminmation_splash)
        binding.textShow.startAnimation(animation)
        waitTimer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    startActivity(intent)
                    finish()
                }
            }
        }, WAIT_TIME.toLong())
    }

    override fun onBackPressed() {
        //Do not do any thing
    }

    companion object {
        private const val WAIT_TIME = 2500
    }
}