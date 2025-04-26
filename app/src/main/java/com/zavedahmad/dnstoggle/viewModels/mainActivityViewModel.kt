package com.zavedahmad.dnstoggle.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class DnsDomainEntry(var domain: String)

class MainActivityViewModel : ViewModel() {
    val inputText= mutableStateOf("")
    val entries = mutableStateListOf(DnsDomainEntry("dns.adguard.com"),DnsDomainEntry("dns.fuckbitches.com"))

}