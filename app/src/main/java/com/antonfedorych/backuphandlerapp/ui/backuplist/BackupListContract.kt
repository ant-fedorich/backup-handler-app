package com.antonfedorych.backuphandlerapp.ui.backuplist

import android.net.Uri
import com.antonfedorych.backuphandlerapp.domain.model.BackupItem

data class BackupListUiState(
    val items: List<BackupItem> = emptyList(),
)

sealed interface BackupListUiEvent {
    data class FilesSelected(val uris: List<Uri>) : BackupListUiEvent
}
