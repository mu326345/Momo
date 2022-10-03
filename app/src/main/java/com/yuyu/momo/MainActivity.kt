package com.yuyu.momo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.yuyu.momo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel by viewModels<MainViewModel>()
    var canBackPress = false

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        supportActionBar?.hide()

        viewModel.barTitle.observe(this) {
            binding.titleTv.text = it
        }
        viewModel.barImage.observe(this) {
            binding.menuImg.setImageDrawable(getDrawable(it))
        }

        binding.menuImg.setOnClickListener {
            if (canBackPress) {
                onBackPressed()
            }
        }
    }
}