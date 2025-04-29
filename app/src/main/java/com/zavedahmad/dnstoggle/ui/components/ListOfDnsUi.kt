package com.zavedahmad.dnstoggle.ui.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.zavedahmad.dnstoggle.data.DnsDomainEntry
import com.zavedahmad.dnstoggle.ui.activities.MainActivity
import com.zavedahmad.dnstoggle.ui.utilities.setPrivateDNS
import com.zavedahmad.dnstoggle.ui.utilities.turnOffPrivateDns
import com.zavedahmad.dnstoggle.viewModels.MainActivityViewModel

@Composable
fun ListOfDnsUi( list: List<DnsDomainEntry>) {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(list) { entry ->
            Column (
                modifier = Modifier.clip(shape = RoundedCornerShape(20.dp)).background(
                    MaterialTheme.colorScheme.primaryContainer
                ).padding(20.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(entry.domain, color = MaterialTheme.colorScheme.onPrimaryContainer, modifier = Modifier.fillMaxWidth().clip(
                    RoundedCornerShape(10.dp)).background(MaterialTheme.colorScheme.background).padding(10.dp))
                Button(onClick = { setPrivateDNS(context, entry.domain) }) {
                    Text("Turn On Dns")
                }

            }
        }
    }

}

