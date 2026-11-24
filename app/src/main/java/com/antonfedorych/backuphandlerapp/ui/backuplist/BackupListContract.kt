package com.antonfedorych.backuphandlerapp.ui.backuplist

import android.net.Uri
import com.antonfedorych.backuphandlerapp.domain.model.BackupItem

data class BackupListState(
    val items: List<BackupItem> = emptyList(),
)

sealed interface BackupListEvent {
    data class FilesSelected(val uris: List<Uri>) : BackupListEvent
}
