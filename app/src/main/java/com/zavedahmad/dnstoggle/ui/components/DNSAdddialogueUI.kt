package com.zavedahmad.dnstoggle.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zavedahmad.dnstoggle.data.DnsDomainEntry
import com.zavedahmad.dnstoggle.viewModels.MainActivityViewModel

@Composable
fun DNSAddDialogueUI(viewModel: MainActivityViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            ,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceBright)
    ) {
        Column (modifier = Modifier.fillMaxSize().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween){
            Text("Write Your DNS Domain below")
            OutlinedTextField(
                onValueChange = { viewModel.inputText.value = it },
                value = viewModel.inputText.value

            )
            Button(onClick = {
                viewModel.addDomain(DnsDomainEntry(domain = viewModel.inputText.value))
                viewModel.hideDNSDialogue()
                viewModel.inputText.value=""
            }) {
                Text("Add Dns")
            }
        }
    }
}