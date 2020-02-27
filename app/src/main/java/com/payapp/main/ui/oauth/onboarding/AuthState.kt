package com.payapp.main.ui.oauth.onboarding

import com.payapp.data.entities.LoggedInUser

sealed class AuthState
class ShowLogin: AuthState()
class ShowRegister: AuthState()
class ShowOtpVerification: AuthState()
class ShowHome(val loggedInUser: LoggedInUser): AuthState()


