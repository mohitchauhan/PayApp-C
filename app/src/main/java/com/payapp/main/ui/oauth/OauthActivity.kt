package com.payapp.main.ui.oauth

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.payapp.data.entities.LoggedInUser
import com.payapp.main.R
import com.payapp.main.di.PayViewModelFactory
import com.payapp.main.ui.BaseActivity
import com.payapp.main.ui.home.HomeActivity
import com.payapp.main.ui.TitleChangeListener
import com.payapp.main.ui.oauth.login.SignInFragment
import com.payapp.main.ui.oauth.onboarding.*
import com.payapp.main.ui.oauth.onboarding.register.OtpVerificationFragment
import com.payapp.main.ui.oauth.onboarding.register.SignUpFragment
import kotlinx.android.synthetic.main.activity_oauth.*
import javax.inject.Inject

class OauthActivity : BaseActivity(),
    IOnboardingCallbacks, TitleChangeListener {

    @Inject
    internal lateinit var factory: PayViewModelFactory

    private lateinit var authViewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oauth)
        setSupportActionBar(toolbar)
        title = ""

        authViewModel =  ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        authViewModel.authStateLiveData.observe(this, Observer { data ->
            when(data){
                is ShowLogin -> supportFragmentManager.beginTransaction().replace(R.id.container, SignInFragment.newInstance()).commit()
                is ShowRegister -> supportFragmentManager.beginTransaction().replace(R.id.container, SignUpFragment.newInstance()).commit()
                is ShowHome -> {startActivity(Intent(this, HomeActivity::class.java)) } // take me to home
                is ShowOtpVerification -> supportFragmentManager.beginTransaction().replace(R.id.container, OtpVerificationFragment.newInstance()).addToBackStack(null).commit()

            }
        })
    }

    override fun openLogin() {
        authViewModel.showLogin();
    }

    override fun openSignup() {
        authViewModel.showRegister()
    }

    override fun openHome(loggedInUser: LoggedInUser) {
        authViewModel.showHome(loggedInUser)
    }

    override fun showOtpVerification() {
        authViewModel.showOtpVerification()
    }

    override fun onTitleChanged(title: String) {
        toolbarTitle.text = title
    }


}

