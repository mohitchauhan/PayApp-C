package com.payapp.main.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.payapp.main.R
import com.payapp.main.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
    }
}
