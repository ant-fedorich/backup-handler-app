package com.antonfedorych.backuphandlerapp.ui.backuplist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antonfedorych.backuphandlerapp.domain.repository.BackupRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BackupListViewModel(
    private val repository: BackupRepository,
) : ViewModel() {

    val state: StateFlow<BackupListState> = repository.observeItems()
        .map { items -> BackupListState(items = items) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), BackupListState())

    init {
        viewModelScope.launch { repository.markInterruptedBackup() }
    }

    fun onEvent(event: BackupListEvent) {
        when (event) {
            is BackupListEvent.FilesSelected -> viewModelScope.launch {
                repository.startBackup(event.uris)
            }
        }
    }
}
