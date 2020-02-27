package com.payapp.main.ui.oauth.onboarding.register


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.pay.core.data.entities.ErrorResult
import com.android.pay.core.data.entities.Loading
import com.android.pay.core.data.entities.Success
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.payapp.data.entities.LoginResponse

import com.payapp.main.R
import com.payapp.main.di.PayViewModelFactory
import com.payapp.main.ui.BaseFragment
import com.payapp.main.ui.TitleChangeListener
import com.payapp.main.ui.oauth.onboarding.IOnboardingCallbacks
import com.payapp.main.ui.oauth.onboarding.OtpViewModel
import kotlinx.android.synthetic.main.fragment_otp_verification.*
import kotlinx.android.synthetic.main.fragment_otp_verification.btnSubmit
import java.io.IOException
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class OtpVerificationFragment : BaseFragment() {

    private lateinit var callbacks: IOnboardingCallbacks

    @Inject
    internal lateinit var factory: PayViewModelFactory

    private lateinit var otpViewModel: OtpViewModel

    companion object {
        fun newInstance() = OtpVerificationFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callbacks = requireActivity() as IOnboardingCallbacks
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp_verification, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindProgressButton(btnSubmit)
        msgOtpSent.text = getString(R.string.msg_otp_sent, "+7990900000")
        otpViewModel = ViewModelProvider(this, factory).get(OtpViewModel::class.java)
        otpViewModel.otpState.observe(this as LifecycleOwner, Observer {state ->
            btnSubmit.isEnabled = state.isValidOtp
        })
        otpViewModel.validateOtpState.observe(this as LifecycleOwner, Observer {
            when(it) {
                is Success<LoginResponse> -> {
                    callbacks.openHome(it.data.loggedInUser!!)
                }
                is ErrorResult -> {
                    btnSubmit.hideProgress(R.string.action_submit)
                    showOtpVerificationFailed(it.exception)
                }
                is Loading -> {
                    (btnSubmit as TextView).showProgress {
                        buttonTextRes = R.string.submitting
                        progressColor = Color.WHITE
                    }

                }

            }
        })
        btnSubmit.setOnClickListener { otpViewModel.validateOtp(otpView.text.toString()) }
        otpView.setOtpCompletionListener { otpViewModel.onOtpInputComplete() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as TitleChangeListener).onTitleChanged(getString(R.string.title_verify_otp))
    }


    private fun showOtpVerificationFailed(e: Exception) {
        @StringRes val message : Int
        if (e is IOException){
            message = R.string.network_error
        }else{
            message = R.string.something_went_wrong
        }
        Toast.makeText(activity!!.applicationContext, message, Toast.LENGTH_SHORT).show()
    }


}
