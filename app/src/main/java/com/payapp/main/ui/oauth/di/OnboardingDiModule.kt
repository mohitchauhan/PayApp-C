package com.payapp.main.ui.oauth.di

import androidx.lifecycle.ViewModel
import com.payapp.main.di.ViewModelBuilder
import com.payapp.main.di.ViewModelKey
import com.payapp.main.ui.oauth.OauthActivity
import com.payapp.main.ui.oauth.login.SignInFragment
import com.payapp.main.ui.oauth.login.SignInViewModel
import com.payapp.main.ui.oauth.onboarding.AuthViewModel
import com.payapp.main.ui.oauth.onboarding.OtpViewModel
import com.payapp.main.ui.oauth.onboarding.register.OtpVerificationFragment
import com.payapp.main.ui.oauth.onboarding.register.SignUpFragment
import com.payapp.main.ui.oauth.onboarding.register.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class OnboardingDiModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun contributeOauthActivity(): OauthActivity



    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun contributeSignupFragment(): SignUpFragment

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun contributeSignInFragment(): SignInFragment

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun contributeOtpVerificationFragment(): OtpVerificationFragment

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindHomeViewModel(viewModel: AuthViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun bindSignInViewModel(viewModel: SignInViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OtpViewModel::class)
    abstract fun bindOtpViewModel(viewModel: OtpViewModel): ViewModel

}