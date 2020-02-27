package com.payapp.main.ui.oauth.login

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
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
import com.payapp.main.R
import com.payapp.main.afterTextChanged
import com.payapp.main.di.PayViewModelFactory
import com.payapp.main.ui.BaseFragment
import com.payapp.main.ui.TitleChangeListener
import com.payapp.main.ui.oauth.onboarding.IOnboardingCallbacks
import kotlinx.android.synthetic.main.sign_in_fragment.*
import kotlinx.android.synthetic.main.sign_up_fragment.password
import kotlinx.android.synthetic.main.sign_up_fragment.passwordInputLayout
import kotlinx.android.synthetic.main.sign_up_fragment.username
import kotlinx.android.synthetic.main.sign_up_fragment.usernameInputLayout
import java.io.IOException
import javax.inject.Inject

class SignInFragment : BaseFragment() {

    @Inject
    internal lateinit var factory: PayViewModelFactory

    private lateinit var signInViewModel: SignInViewModel
    private lateinit var callbacks: IOnboardingCallbacks


    companion object {
        fun newInstance() = SignInFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callbacks = requireActivity() as IOnboardingCallbacks
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_in_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindProgressButton(loginButton)
        signInViewModel = ViewModelProvider(this, factory).get(SignInViewModel::class.java)
        signUpText.setOnClickListener { callbacks.openSignup() }
        observeViewState()
        observeData()

        username.afterTextChanged {
            onFormDataChanged()
        }

        password.apply {
            afterTextChanged {
                onFormDataChanged()
            }
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        signInViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }
        }

        loginButton.setOnClickListener {
            signInViewModel.login(username.text.toString(), password.text.toString())
        }
    }

    private fun observeData() {
        signInViewModel.loginState.observe(this@SignInFragment as LifecycleOwner, Observer {
            val loginResult = it ?: return@Observer

            when (loginResult) {
                is Success -> {
                   callbacks.openHome(loginResult.data.loggedInUser!!)
                }
                is ErrorResult -> {
                    loginButton.hideProgress(R.string.action_signin)
                    showLoginFailed(loginResult.exception)
                }
                is Loading -> {
                    loginButton.showProgress {
                        buttonTextRes = R.string.submitting
                        progressColor = Color.WHITE
                    }

                }
            }

            //Complete and destroy login activity once successful
        })
    }

    private fun observeViewState() {
        signInViewModel.loginFormState.observe(this@SignInFragment as LifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            loginButton.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                usernameInputLayout.error = getString(loginState.usernameError)
            } else {
                usernameInputLayout.error = null
            }

            if (loginState.passwordError != null) {
                passwordInputLayout.error = getString(loginState.passwordError)
            } else {
                passwordInputLayout.error = null
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as TitleChangeListener).onTitleChanged(getString(R.string.title_login))
    }

    private fun onFormDataChanged() {
        signInViewModel.loginDataChanged(
            username.text.toString(),
            password.text.toString()
        )
    }


    private fun showLoginFailed( e: Exception) {
        @StringRes val message : Int
        if (e is IOException){
            message = R.string.network_error
        }else{
            message = R.string.something_went_wrong
        }
        Toast.makeText(activity!!.applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}
