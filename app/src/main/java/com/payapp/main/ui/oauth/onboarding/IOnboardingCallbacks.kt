package com.payapp.main.ui.oauth.onboarding

import com.payapp.data.entities.LoggedInUser

/**
 * //TODO Later replace with nav controller
 */
interface IOnboardingCallbacks {
    fun openLogin()
    fun openSignup()
    fun openHome(loggedInUser: LoggedInUser)
    fun showOtpVerification()
}