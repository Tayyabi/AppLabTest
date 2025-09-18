package com.example.applabtest.presentation.ui.home

import androidx.lifecycle.ViewModel

class HomeViewModel @Inject constructor(
    private val repository: LPGLedgerRepository,
    private  val implRepository: ImplRepository
) : ViewModel()  {
}