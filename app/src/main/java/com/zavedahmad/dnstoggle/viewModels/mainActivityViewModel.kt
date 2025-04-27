package com.zavedahmad.dnstoggle.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zavedahmad.dnstoggle.data.DnsDomainEntry
import com.zavedahmad.dnstoggle.data.DnsDomainEntryDao


class MainActivityViewModel(val dnsDomainEntryDao: DnsDomainEntryDao) : ViewModel() {
    val inputText= mutableStateOf("")

    fun addDomain(entry: DnsDomainEntry){
        dnsDomainEntryDao.insert(entry)
    }

}