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
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zavedahmad.dnstoggle.data.DnsDomainEntry
import com.zavedahmad.dnstoggle.viewModels.MainActivityViewModel
@Composable
fun DNSAddDialogueUI(viewModel: MainActivityViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceBright)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Write Your DNS Domain",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            // FocusRequester for the TextField
            val focusRequester = remember { FocusRequester() }
            // Optional: Keyboard controller to ensure keyboard shows
            val keyboardController = LocalSoftwareKeyboardController.current

            OutlinedTextField(
                value = viewModel.inputText.value,
                onValueChange = { viewModel.inputText.value = it },
                modifier = Modifier
                    .fillMaxWidth() // Ensure TextField takes available width
                    .focusRequester(focusRequester), // Attach FocusRequester
                label = { Text("DNS Domain") } // Optional: Add a label for clarity
            )
            ElevatedButton(
                onClick = {
                    viewModel.addDomain(DnsDomainEntry(domain = viewModel.inputText.value))
                    viewModel.hideDNSDialogue()
                    viewModel.inputText.value = ""
                }
            ) {
                Text("Add DNS")
            }

            // Request focus and show keyboard when the dialog appears
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
                keyboardController?.show() // Explicitly show the keyboard
            }
        }
    }
}