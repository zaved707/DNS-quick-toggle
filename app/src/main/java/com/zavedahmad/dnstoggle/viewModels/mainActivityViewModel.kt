package com.zavedahmad.dnstoggle.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zavedahmad.dnstoggle.data.DnsDomainEntry
import com.zavedahmad.dnstoggle.data.DnsDomainEntryDao


class MainActivityViewModel(val dnsDomainEntryDao: DnsDomainEntryDao) : ViewModel() {
    val inputText= mutableStateOf("")
    var DNSAddDialogueState =  mutableStateOf(false)

    fun hideDNSDialogue(){
        DNSAddDialogueState.value=false
    }
    fun showDNSDialogue(){
        DNSAddDialogueState.value=true
    }
    fun addDomain(entry: DnsDomainEntry){
        dnsDomainEntryDao.insert(entry)
    }

}