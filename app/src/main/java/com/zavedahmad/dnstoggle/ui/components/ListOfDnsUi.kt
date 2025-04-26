package com.zavedahmad.dnstoggle.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.zavedahmad.dnstoggle.ui.activities.MainActivity
import com.zavedahmad.dnstoggle.ui.utilities.setPrivateDNS
import com.zavedahmad.dnstoggle.ui.utilities.turnOffPrivateDns
import com.zavedahmad.dnstoggle.viewModels.MainActivityViewModel

@Composable
fun ListOfDnsUi(viewModel: MainActivityViewModel) {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(viewModel.entries) { entry ->
            Row(
                modifier = Modifier.background(
                    MaterialTheme.colorScheme.primaryContainer
                ).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(entry.domain, color = MaterialTheme.colorScheme.onPrimaryContainer)
                Button(onClick = { setPrivateDNS(context, entry.domain) }) {
                    Text("Turn On Dns")
                }

            }
        }
    }

}

