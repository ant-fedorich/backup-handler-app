package com.antonfedorych.backuphandlerapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.antonfedorych.backuphandlerapp.data.local.AppDatabase
import com.antonfedorych.backuphandlerapp.data.repository.backup.BackupRepositoryImpl
import com.antonfedorych.backuphandlerapp.ui.backuplist.BackupListScreen
import com.antonfedorych.backuphandlerapp.ui.backuplist.BackupListViewModel
import com.antonfedorych.backuphandlerapp.ui.common.theme.BackupHandlerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "backup.db",
        ).build()

        setContent {
            val viewModel = viewModel {
                BackupListViewModel(
                    BackupRepositoryImpl(applicationContext, database.backupDao())
                )
            }

            BackupHandlerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BackupListScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
