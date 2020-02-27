package com.payapp.main.ui.oauth.onboarding.register

import androidx.annotation.StringRes

/**
 * Data validation state of the login form.
 */
data class SignupFormState ( @StringRes val usernameError: Int? = null,
                            @StringRes val passwordError: Int? = null,
                            @StringRes val emailError: Int? = null,
                            @StringRes val tncError: Int? = null,
                            val isDataValid: Boolean = false)
