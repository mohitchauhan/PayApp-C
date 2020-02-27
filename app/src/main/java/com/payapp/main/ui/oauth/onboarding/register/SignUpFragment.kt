package com.payapp.main.ui.oauth.onboarding.register

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.pay.core.data.entities.ErrorResult
import com.android.pay.core.data.entities.Loading
import com.android.pay.core.data.entities.Success
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress

import com.payapp.main.R
import com.payapp.main.di.PayViewModelFactory
import com.payapp.main.ui.BaseFragment
import com.payapp.main.ui.TitleChangeListener
import com.payapp.main.ui.oauth.onboarding.IOnboardingCallbacks
import kotlinx.android.synthetic.main.sign_up_fragment.*
import java.io.IOException
import javax.inject.Inject

class SignUpFragment : BaseFragment() {

    @Inject
    internal lateinit var factory: PayViewModelFactory

    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var callbacks: IOnboardingCallbacks



    companion object {
        fun newInstance() = SignUpFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callbacks = requireActivity() as IOnboardingCallbacks
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_up_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindProgressButton(btnSubmit)
        signUpViewModel = ViewModelProvider(this, factory).get(SignUpViewModel::class.java)
        signInText.setOnClickListener { callbacks.openSignup() }

        signUpViewModel.signupFormState.observe(this@SignUpFragment as LifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            btnSubmit.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                usernameInputLayout.error = getString(loginState.usernameError)
            }else{
                usernameInputLayout.error  = null
            }

            if (loginState.passwordError != null) {
                passwordInputLayout.error = getString(loginState.passwordError)
            }else{
                passwordInputLayout.error = null
            }

            if (loginState.emailError != null) {
                emailLayout.error = getString(loginState.emailError)
            }else{
                emailLayout.error = null
            }

            if (loginState.tncError != null) {
                tnc.setTextColor(ContextCompat.getColor(requireContext(), R.color.design_default_color_error))
            }else {
                tnc.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
            }

        })

        signUpViewModel.loginState.observe(this@SignUpFragment as LifecycleOwner, Observer {
            val loginResult = it ?: return@Observer

            when(loginResult){
                is Success -> {
                    (btnSubmit as TextView).hideProgress()
                    // show otp screen
                    callbacks.showOtpVerification()
                }
                is ErrorResult -> {
                    btnSubmit.hideProgress(R.string.action_continue)
                    showSignUpFailed(loginResult.exception)
                }
                is Loading -> {
                    (btnSubmit as TextView).showProgress {
                        buttonTextRes = R.string.submitting
                        progressColor = Color.WHITE
                    }
                    
                }
            }

            //Complete and destroy login activity once successful
        })

        tnc.setOnClickListener(View.OnClickListener { onFormDataChanged() })
        username.afterTextChanged {
            onFormDataChanged()
        }

        email.afterTextChanged {
            onFormDataChanged()
        }

        password.apply {
            afterTextChanged {
                onFormDataChanged()
            }


            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        signUpViewModel.register(
                            username.text.toString(),
                            password.text.toString(),
                            email.text.toString()
                        )
                }
                false
            }

            btnSubmit.setOnClickListener {
                signUpViewModel.register(username.text.toString(), password.text.toString(), email.text.toString())
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as TitleChangeListener).onTitleChanged(getString(R.string.title_signup))
    }

    private fun onFormDataChanged() {
        signUpViewModel.signUpDataChanged(
            username.text.toString(),
            password.text.toString(),
            email.text.toString(),
            tnc.isChecked
        )
    }



    private fun showSignUpFailed(e: Exception) {
        @StringRes val message : Int
        if (e is IOException){
            message = R.string.network_error
        }else{
            message = R.string.something_went_wrong
        }
        Toast.makeText(activity!!.applicationContext, message, Toast.LENGTH_SHORT).show()
    }



    /**
     * Extension function to simplify setting an afterTextChanged action to EditText components.
     */
    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }


}
