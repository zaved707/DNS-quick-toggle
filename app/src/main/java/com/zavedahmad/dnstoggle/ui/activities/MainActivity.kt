package com.zavedahmad.dnstoggle.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.Modifier

import com.zavedahmad.dnstoggle.ui.theme.DNSToggleTheme

import com.zavedahmad.dnstoggle.viewModels.MainActivityViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.zavedahmad.dnstoggle.data.AppDatabase

import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.getValue

import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import com.zavedahmad.dnstoggle.R

import com.zavedahmad.dnstoggle.ui.components.DNSAddDialogueUI
import com.zavedahmad.dnstoggle.ui.pages.MainPage


@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        val db =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "dnsName_db")
                .allowMainThreadQueries().build()
        val dao = db.dnsDomainEntryDao()
        setContent {
            val viewModel: MainActivityViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        @Suppress("UNCHECKED_CAST")
                        return MainActivityViewModel(dao) as T
                    }
                }
            )
            val itemList by dao.getAllEntries().collectAsState(initial = emptyList())
            val uriHandler = LocalUriHandler.current
            Log.d("DNS_Setting", "Current user ID: ")
            val scrollBehaviour=TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
            DNSToggleTheme {
                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize().nestedScroll(scrollBehaviour.nestedScrollConnection),
                    floatingActionButton = {
                        FloatingActionButton(onClick = { viewModel.showDNSDialogue() }, modifier = Modifier.offset(x=(-20).dp,y=(-50).dp), containerColor = MaterialTheme.colorScheme.secondary , contentColor = MaterialTheme.colorScheme.onSecondary) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_add_24),
                                contentDescription = "add New DNS"
                            )
                        }
                    },
                    topBar = {
                        MediumTopAppBar(
                            scrollBehavior = scrollBehaviour,
                            colors = topAppBarColors(

                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            ),
                            title = {
                                Text("PrivateDnsToggle")
                            },
                            actions = {
                                IconButton(onClick = { uriHandler.openUri("http://www.github.com/zaved707/") }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.github_mark),
                                        contentDescription = "go to my github",
                                        modifier = Modifier.size(40.dp),
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
                            }
                        )
                    }) { innerPadding ->

                    if (viewModel.DNSAddDialogueState.value) {
                        Dialog(onDismissRequest = { viewModel.hideDNSDialogue() }) {
                            DNSAddDialogueUI(viewModel)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(innerPadding),

                        ) {
                        MainPage(this@MainActivity, viewModel, itemList)
                    }
                }
            }
        }
    }
}