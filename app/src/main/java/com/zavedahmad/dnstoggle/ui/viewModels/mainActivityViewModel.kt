package com.zavedahmad.dnstoggle.ui.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class dnsDomainEntry(var domain: String)

class mainActivityViewModel : ViewModel() {
    val inputText= mutableStateOf("")
    val entries = mutableStateListOf<dnsDomainEntry>()

}