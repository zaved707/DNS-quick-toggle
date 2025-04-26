package com.zavedahmad.dnstoggle.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.Modifier
import com.zavedahmad.dnstoggle.ui.theme.DNSToggleTheme
import com.zavedahmad.dnstoggle.ui.utilities.setPrivateDNS
import com.zavedahmad.dnstoggle.viewModels.MainActivityViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zavedahmad.dnstoggle.ui.components.ListOfDnsUi
import com.zavedahmad.dnstoggle.ui.utilities.hasWriteSecureSettingsPermission
import com.zavedahmad.dnstoggle.ui.utilities.turnOffPrivateDns
import com.zavedahmad.dnstoggle.viewModels.DnsDomainEntry


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContent {
            val viewModel: MainActivityViewModel = viewModel()
            Log.d("DNS_Setting", "Current user ID: ")
            DNSToggleTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize(), topBar = {
                    TopAppBar(
                        colors = topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                        title = {
                            Text("PrivaeDnsToggle")
                        }
                    )
                }) { innerPadding ->
                    Column(
                        modifier = Modifier.Companion
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(innerPadding),

                        ) {
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Permission: " + hasWriteSecureSettingsPermission(this@MainActivity).toString())
                            Button(onClick = { turnOffPrivateDns(this@MainActivity) }) {
                                Text("Turn off dns")
                            }

                        }

                        Row {


                            TextField(
                                onValueChange = { viewModel.inputText.value = it },
                                value = viewModel.inputText.value
                            )
                            Button(onClick = {
                                viewModel.entries.add(DnsDomainEntry(viewModel.inputText.value))
                            }) {
                                Text("Add Dns")
                            }
                        }
                        ListOfDnsUi(viewModel)
                    }
                }
            }
        }
    }
}