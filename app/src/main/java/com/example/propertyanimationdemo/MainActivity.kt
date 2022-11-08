package com.example.propertyanimationdemo

import android.animation.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.propertyanimationdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRotate.setOnClickListener { rotate() }
        binding.btnTranslate.setOnClickListener { translate() }
        binding.btnScale.setOnClickListener { scale() }
        binding.btnFade.setOnClickListener { fade() }
        binding.btnSkyColor.setOnClickListener { changeSkyColor() }
        binding.btnShower.setOnClickListener { shower() }
    }

    private fun reset(){
        binding.imageViewStar.translationX = 0f
        binding.imageViewStar.rotation = 0f
        binding.imageViewStar.scaleX = 1f
        binding.imageViewStar.scaleY = 1f
        binding.imageViewStar.alpha = 1f
        binding.starContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
    }

    private fun rotate(){
        ObjectAnimator.ofFloat(binding.imageViewStar, "rotation", 360f).apply {
            duration = 1000
            start()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    reset()
                }
            })
        }
    }

    private fun translate() {
        ObjectAnimator.ofFloat(binding.imageViewStar, "translationX", 100f).apply {
            duration = 1000
            start()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    reset()
                }
            })
        }
    }

    private fun scale() {
        val scaleX = ObjectAnimator.ofFloat(binding.imageViewStar, "scaleX", 1f, 2f).apply {
            duration = 1000
        }
        val scaleY = ObjectAnimator.ofFloat(binding.imageViewStar, "scaleY", 1f, 2f).apply {
            duration = 1000
        }

        AnimatorSet().apply {
            play(scaleX).with(scaleY)
            start()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    reset()
                }
            })
        }
    }

    private fun fade() {
        ObjectAnimator.ofFloat(binding.imageViewStar, "alpha", 1f, 0f).apply {
            duration = 1000
            start()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    reset()
                }
            })
        }
    }

    private fun changeSkyColor() {
        val colorFrom = ContextCompat.getColor(this, R.color.black)
        val colorTo = ContextCompat.getColor(this, R.color.sky_blue)
        ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo).apply {
            duration = 1000 // milliseconds
            addUpdateListener { animator -> binding.starContainer.setBackgroundColor(animator.animatedValue as Int) }
            start()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    reset()
                }
            })
        }
    }

    private fun shower() {

        val changeY = ObjectAnimator.ofFloat(binding.imageViewStar, "translationY", -800f, 800f).apply {
            duration = 3000
            repeatCount = 5
            start()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationRepeat(animation: Animator?) {
                    binding.imageViewStar.translationX = (-300..300).random().toFloat()
                }

                override fun onAnimationEnd(animation: Animator) {
                    reset()
                }
            })
        }
    }
}