package com.antonfedorych.backuphandlerapp.ui.backuplist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BackupListViewModel : ViewModel() {
    private val _state = MutableStateFlow(BackupListState(items = sampleBackupItems))
    val state: StateFlow<BackupListState> = _state.asStateFlow()

    fun onEvent(event: BackupListEvent) {
        when (event) {
            is BackupListEvent.FilesSelected -> Unit // Copying comes in a later step.
        }
    }
}
