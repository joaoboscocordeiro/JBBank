package com.example.jbbank.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jbbank.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by João Bosco on 02/11/2023.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}