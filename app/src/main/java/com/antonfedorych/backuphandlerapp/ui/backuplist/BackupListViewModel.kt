package com.antonfedorych.backuphandlerapp.ui.backuplist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BackupListViewModel : ViewModel() {
    private val _state = MutableStateFlow(BackupListUiState(items = sampleBackupItems))
    val state: StateFlow<BackupListUiState> = _state.asStateFlow()

    fun onEvent(event: BackupListUiEvent) {
        when (event) {
            is BackupListUiEvent.FilesSelected -> Unit // Copying comes in a later step.
        }
    }
}
