package com.eliseylobanov.acrosstheuniverse.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.ui.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

const val SPLASH_TIME = 3000L
const val ROTATION_TIME = 750f

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashImage.animate().rotationBy(ROTATION_TIME)
            .setInterpolator(AccelerateDecelerateInterpolator()).setDuration(SPLASH_TIME)
            .setListener(object : Animator.AnimatorListener {

                override fun onAnimationEnd(animation: Animator?) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }

                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationStart(animation: Animator?) {}
            })
    }
}