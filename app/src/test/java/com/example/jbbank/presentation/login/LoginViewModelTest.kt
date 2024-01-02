package com.example.jbbank.presentation.login

import org.cristovolta.core.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by João Bosco on 08/12/2023.
 */
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {


    @ExperimentalCoroutinesApi
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var loginUseCase: LoginUseCase

    private lateinit var loginViewModel: LoginViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        loginViewModel = LoginViewModel(loginUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should validate the login data object values when calling login users`() =
        runBlockingTest {

            val result = loginViewModel.login("", "")
        }
}