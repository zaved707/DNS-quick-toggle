package com.zavedahmad.dnstoggle.ui.pages

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zavedahmad.dnstoggle.data.DnsDomainEntry
import com.zavedahmad.dnstoggle.ui.activities.MainActivity
import com.zavedahmad.dnstoggle.ui.components.ListOfDnsUi
import com.zavedahmad.dnstoggle.ui.utilities.hasWriteSecureSettingsPermission
import com.zavedahmad.dnstoggle.ui.utilities.turnOffPrivateDns
import com.zavedahmad.dnstoggle.viewModels.MainActivityViewModel

@Composable
fun MainPage(context: Context, viewModel: MainActivityViewModel, itemList: List<DnsDomainEntry>) {
    Column (modifier = Modifier.padding(20.dp)){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Permission: " + hasWriteSecureSettingsPermission(context).toString())
            Button(onClick = { turnOffPrivateDns(context) ; viewModel.offDNS() }) {
                Text("Turn off dns")
            }

        }
        Text("Your Domains")


        ListOfDnsUi(itemList,viewModel)
    }
}